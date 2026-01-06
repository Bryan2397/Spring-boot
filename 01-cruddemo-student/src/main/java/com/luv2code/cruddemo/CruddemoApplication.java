package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.beans.BeanProperty;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return run -> {
			//createStudent(studentDAO);
			
			createMultipleStudents(studentDAO);
			
			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAll(studentDAO);

		};
	}

	private void deleteAll(StudentDAO studentDAO) {
		System.out.println("Deleting all");
		int numRowsDeleted = studentDAO.deleteAllStudents();
		System.out.println("Number of rows deleted: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("System student id: " + studentId);
		studentDAO.delete(3);
	}


	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to Scooby
		System.out.println("Updating student ...");
		myStudent.setFirstName("Bryan");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Giglio");
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> allStudents = studentDAO.findAll();

		// display list of students
		for(Student tempStudent : allStudents){
			System.out.println(tempStudent);
		}
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

	}

	public void createMultipleStudents(StudentDAO studentDAO){
		//create multiple students
		Student tempStudent1 = new Student("Bryan", "Mirtil", "bryan@luv2code.com");
		Student tempStudent2 = new Student("Jaidan", "Giglio", "jaidan@luv2code.com");
		Student tempStudent3 = new Student("Matteo", "Di Berto", "matteo@luv2code.com");


		//save the student objects
		System.out.println("Saving students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

}
