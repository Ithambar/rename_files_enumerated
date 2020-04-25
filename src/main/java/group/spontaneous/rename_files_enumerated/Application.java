package group.spontaneous.rename_files_enumerated;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Application {

	@Value("${files.prefix}")
	private String prefix;

	private static final String GENERIC_ERROR = "An error occurred";

	@PostConstruct
	public void init() {
		File[] input = new File("./input").listFiles();
		File[] output = new File("./output").listFiles();

		try (Scanner terminalInput = new Scanner(System.in)) {
			prefix = terminalInput.nextLine();
		}

		for (var file : output) {
			try {
				Files.delete(Paths.get(file.getPath()));
			} catch (IOException e) {
				LoggerFactory.getLogger(getClass()).error(GENERIC_ERROR, e);
			}
		}

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
				LoggerFactory.getLogger(getClass()).error(GENERIC_ERROR, e);
			}
		}
	}
}
