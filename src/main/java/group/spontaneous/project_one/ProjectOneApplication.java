package group.spontaneous.project_one;

import java.util.ArrayList;

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

		var dogs = new ArrayList<Dog>();

		for (var i = 0; i < names.length; i++) {
			var newDog = new Dog(names[i], ages[i], colors[i]);
			dogs.add(newDog);
		}

		for (var dogInList : dogs) {
			while (dogInList.getAge() < 10) {
				dogInList.setAge(dogInList.getAge() + 1);
				dogInList.whoAmI();
			}
			if (dogInList.getName().equals("Bob")) {
				System.out.println("Woof!");
			}
		}
	}
}
