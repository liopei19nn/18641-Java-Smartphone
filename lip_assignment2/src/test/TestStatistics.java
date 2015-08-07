/**
 * 
 */
package test;

import statistics.Statistics;
import student.Student;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class TestStatistics {
	public static void main(String[] args) {
		// the length of st is 10, but we only initialize 3 students
		// which in case of the actual students is less than the list
		Student[] st = new Student[10];
		final int STUDENTNUMBER = 3;
		for (int i = 0; i < STUDENTNUMBER; i++) {
			st[i] = new Student();
		}
		st[0].setSID(1234);
		int[] score1 = { 78, 83, 87, 91, 86 };
		st[0].setScore(score1);

		st[1].setSID(2134);
		int[] score2 = { 67, 77, 84, 82, 79 };
		st[1].setScore(score2);

		st[2].setSID(1852);
		int[] score3 = { 77, 89, 93, 87, 71 };
		st[2].setScore(score3);
		System.out.println("Stud\t" + "Q1\t" + "Q2\t" + "Q3\t" + "Q4\t" + "Q5");
		for (int i = 0; i < STUDENTNUMBER; i++) {
			st[i].toPrintValue();
		}
		System.out.println();

		/* TEST STATISTICS */
		Statistics statistics = new Statistics();
		statistics.findlow(st);
		statistics.findhigh(st);
		statistics.findavg(st);
		statistics.toPrintValue();
	}
}
