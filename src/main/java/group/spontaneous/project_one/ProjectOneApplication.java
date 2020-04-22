package group.spontaneous.project_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import group.spontaneous.project_one.models.Dog;

@SpringBootApplication
public class ProjectOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOneApplication.class, args);

		String[] names = { "Bob", "Fluffy" };
		int[] ages = { 3, 5 };
		String[] colors = { "black", "brown" };

		var tollerName = new Dog(names[0], ages[0], colors[0]);
		var auchEinTollerName = new Dog(names[1], ages[1], colors[1]);

		tollerName.whoAmI();
		auchEinTollerName.whoAmI();
	}
}
