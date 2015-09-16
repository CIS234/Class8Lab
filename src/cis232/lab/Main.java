package cis232.lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	private static final String STUDENTS_FILE = "students.csv";
	private Random random = new Random();
	private ArrayList<Student> unpickedStudents;
	private ArrayList<Student> allStudents;
	private ArrayList<Student> pickedStudents = new ArrayList<>();
	private Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.run();

		System.out.println("Hope everyone enjoys their bonus points!");
	}
	
	public Main() throws IOException{
		loadStudentsFromFile();
	}
	
	public void run() throws IOException{
		while(askToPickStudent()){
			Student student = pickRandomStudent();
			System.out.println(student);
			System.out.printf("Did %s get it right? (y/n)%n", student);
			if(keyboard.nextLine().equalsIgnoreCase("y")){
				student.addPoint();
				System.out.printf("Great Job +1 point. %s has %d points.%n",
						student, student.getPoints());
				saveStudentsToFile();
			}else{
				System.out.printf("Better luck next time! %s has %d points.%n",
						student, student.getPoints());
			}
		}
	}

	private Student pickRandomStudent() {
		if(unpickedStudents.isEmpty()){
			ArrayList<Student> temp = unpickedStudents;
			unpickedStudents = pickedStudents;
			pickedStudents = temp;
		}
		
		Student student = unpickedStudents.remove(random.nextInt(unpickedStudents.size()));
		pickedStudents.add(student);
		return student;
	}

	private boolean askToPickStudent() {
		System.out.println("Pick a student? (y/n)");
		String pickInput = keyboard.nextLine();
		return pickInput.equalsIgnoreCase("y");
	}

	private void loadStudentsFromFile() throws FileNotFoundException {
		File originalFile = new File(STUDENTS_FILE);
		Scanner input = new Scanner(originalFile);
		unpickedStudents = new ArrayList<>();
		allStudents = new ArrayList<>();
		
		while(input.hasNextLine()){
			StringTokenizer tokens = new StringTokenizer(input.nextLine(), ",");
			Student student = new Student(tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
			unpickedStudents.add(student);
			allStudents.add(student);
		}
		
		input.close();
	}
		
	private void saveStudentsToFile() throws IOException{
		PrintWriter output = new PrintWriter(STUDENTS_FILE);
		for(Student s : allStudents){
			output.println(s.toCsvString());
		}
		output.close();
	}

}
