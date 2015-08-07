/**
 * 
 */
package test;

import student.Student;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class TestStudentClass {
	public static void main(String[] args) {
		Student st = new Student();
		/* test normal work */
		// test SID setter and getter
		st.setSID(1234);
		System.out.println("Test SID getter and setter : Student ID "
				+ st.getSID());
		System.out.println();

		int[] scores = { 12, 23, 34, 45, 56 };
		// test scores setter and getter
		System.out.println("Test scores setter and getter : ");
		st.setScore(scores);
		int[] getScores = st.getScore();
		for (int i = 0; i < getScores.length; i++) {
			System.out.println("score of Q" + (i + 1) + " " + getScores[i]);
		}
		System.out.println();

		// test print student information
		System.out.println("Test print value method : ");
		st.toPrintValue();
		System.out.println();

		/* corner test */
		// 1 -- test input SID below 4 digits
		System.out.println("Test input SID below 4 digits, test input is 1");
		System.out.println("Test output : ");
		st.setSID(1);
		st.toPrintValue();
		System.out.println();

		// 2 -- test input scores more than 5
		int[] scores1 = { 70, 60, 50, 40, 30, 20, 10 };
		System.out.println("Test input input scores more than 5");
		System.out
				.println("Test input is : scores1 = { 70, 60, 50, 40, 30, 20, 10 };");
		System.out.println("Test output : ");
		st.setScore(scores1);
		st.toPrintValue();
		System.out.println();

		// 3 -- test input scores less than 5
		Student st1 = new Student();
		st1.setSID(1);
		int[] scores2 = { 70, 60 };
		System.out.println("Test input input scores more than 5");
		System.out.println("Test input is : scores1 = { 70, 60};");
		System.out.println("Test output : ");
		st1.setScore(scores2);
		st1.toPrintValue();

	}
}
