package edu.bu.domain.CourseProject;

public class Student extends Person {

	private int grade;

	public Student(String name, int age, Address address, int grade) {
		super(name, age, address);
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return super.toString() + "\nGrade: " + this.grade;
	}
}
