/**
 * 
 */
package test;

import student.Student;
import util.Util;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class TestUtil {
	public static void main(String[] args) {
		Student lab[] = new Student[40];

		// Populate the student array
		// for normal situation
		// input 15 student
		// filename.txt
		System.out.println("Test for input 15 students");
		lab = Util.readFile("filename.txt", lab);

		// print each student here
		System.out.println("Stud\t" + "Q1\t" + "Q2\t" + "Q3\t" + "Q4\t" + "Q5");
		for (int i = 0; i < lab.length && lab[i] != null; i++) {
			lab[i].toPrintValue();
		}
		System.out.println();

		// Populate the student array
		// for normal situation
		// input 0 student
		// filename1.txt
		lab = new Student[40];
		System.out.println("Test for input 0 students");
		lab = Util.readFile("filename1.txt", lab);
		System.out.println("Stud\t" + "Q1\t" + "Q2\t" + "Q3\t" + "Q4\t" + "Q5");
		for (int i = 0; i < lab.length && lab[i] != null; i++) {
			lab[i].toPrintValue();
		}
		System.out.println();

		// Populate the student array
		// for normal situation
		// input 45 student
		// filename2.txt
		System.out.println();
		lab = new Student[40];
		System.out.println("Test for input 45 students");
		lab = Util.readFile("filename2.txt", lab);
		System.out.println("Stud\t" + "Q1\t" + "Q2\t" + "Q3\t" + "Q4\t" + "Q5");
		for (int i = 0; i < lab.length && lab[i] != null; i++) {
			lab[i].toPrintValue();
		}
		System.out.println();

	}
}
