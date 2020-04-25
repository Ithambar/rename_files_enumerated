package group.spontaneous.rename_files_enumerated;

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
	private void init() {
		System.out.println(prefix);
	}
}
