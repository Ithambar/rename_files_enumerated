package group.spontaneous.project_one.models;

public class Dog {

	private String name = "";
	private int age;
	private String color = "";

	public Dog(String name, int age, String color) {
		this.name = name;
		this.age = age;
		this.color = color;
	}

	public void whoAmI() {
		System.out.println("Hello, I am " + this.name + ". I am " + this.age + " years old and my fur is " + this.color
				+ ". And my name is " + this.name.length() + " symbols long.");
	}
}
