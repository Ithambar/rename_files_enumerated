package group.spontaneous.rename_files_enumerated;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Application {

	private static final String GENERIC_ERROR = "An error occurred";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {

		var prefix = getPrefix();

		cleanOutput();
		renameFiles(prefix);

	}

	private String getPrefix() {
		var result = "file";
		System.out.println("Please enter Prefix...\nDefault: 'file'");

		try (Scanner terminalInput = new Scanner(System.in)) {
			var inputText = terminalInput.nextLine();

			while (!inputText.isBlank()) {
				if (inputText.matches("^[\\w\\d-_\\ \\.\\(\\){}\\[\\]]+$")) {
					result = inputText;
					break;
				} else {

					System.out.println(
							"The Prefix was invalid! You FOOL!!!1!einself...\nOnly use \"A-Z a-z 0-9 . _ - [] () {} .\" and try again :)");
					inputText = terminalInput.nextLine();

				}
			}
		}
		return result;
	}

	private void cleanOutput() {
		File[] output = new File("./output").listFiles();
		for (var file : output) {
			try {
				Files.delete(Paths.get(file.getPath()));
			} catch (IOException e) {
				logger.error(GENERIC_ERROR, e);
			}
		}
	}

	private void renameFiles(String prefix) {
		File[] input = new File("./input").listFiles();
		var errorFileContent = "";

		for (var i = 0; i < input.length; i++) {
			var currentFile = input[i];
			var fileNameParts = currentFile.getName().split("\\.");
			var extension = "";
			if (fileNameParts.length != 1) {
				extension = fileNameParts[fileNameParts.length - 1];
			}
			var targetPath = "./output/" + prefix + String.format("%05d", i) + "." + extension;
			try {
				Files.copy(Paths.get(currentFile.getPath()), Paths.get(targetPath), StandardCopyOption.COPY_ATTRIBUTES);
			} catch (IOException e) {
				errorFileContent = new StringBuilder(errorFileContent).append(currentFile.getName()).append("\n")
						.toString();
			}
		}
		if (!errorFileContent.isBlank()) {
			try {
				Files.write(Paths.get("./output/#errors.txt"), errorFileContent.getBytes());
			} catch (IOException e) {
				logger.error(GENERIC_ERROR, e);
			}
		}
	}
}
