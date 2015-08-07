/**
 * 
 */
package run;

import util.Util;
import statistics.Statistics;
import student.Student;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class MainRunning {
	public static void main(String[] args) {
		Student lab2[] = new Student[40];

		// Populate the student array
		lab2 = Util.readFile("filename.txt", lab2);
		Statistics statlab2 = new Statistics();
		statlab2.findlow(lab2);
		statlab2.findhigh(lab2);
		statlab2.findavg(lab2);

		// print each student here
		System.out.println("Stud\t" + "Q1\t" + "Q2\t" + "Q3\t" + "Q4\t" + "Q5");
		for (int i = 0; i < lab2.length && lab2[i] != null; i++) {
			lab2[i].toPrintValue();
		}
		System.out.println();

		// print statics here
		statlab2.toPrintValue();

	}
}
