package group.spontaneous.rename_files_enumerated;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Application {

	@Value("${files.prefix}")
	private String prefix;

	@PostConstruct
	public void init() {
		File[] files = new File(".").listFiles();

		for (var i = 0; i < files.length; i++) {
			files[i].renameTo(new File(prefix + String.valueOf(i)));
		}
	}
}
