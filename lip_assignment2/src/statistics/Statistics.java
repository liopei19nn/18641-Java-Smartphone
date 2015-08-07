/**
 * 
 */
package statistics;

import prototype.*;
import student.Student;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class Statistics extends AbstractStatistics<Student> implements Printer {
	private int[] lowscores = new int[5];
	private int[] highscores = new int[5];
	private float[] avgscores = new float[5];

	/*
	 * findhigh() find the highest score for each quiz between all students
	 * 
	 * @param highscores[]
	 */
	public void findhigh(Student[] a) {
		// if there is no student input, return
		if (a[0] == null) {
			return;
		}
		int[] storeScore = new int[highscores.length];
		// initialize for finding
		for (int scoreIndex = 0; scoreIndex < highscores.length; scoreIndex++) {
			highscores[scoreIndex] = a[0].getScore()[scoreIndex];
			storeScore[scoreIndex] = a[0].getScore()[scoreIndex];
		}
		// finding the highest
		for (int st = 1; st < a.length; st++) {
			if (a[st] == null) {
				return;
			}
			for (int scoreIndex = 0; scoreIndex < highscores.length; scoreIndex++) {
				storeScore[scoreIndex] = a[st].getScore()[scoreIndex];
				if (storeScore[scoreIndex] > highscores[scoreIndex]) {
					highscores[scoreIndex] = storeScore[scoreIndex];
				}
			}
		}
	}

	/*
	 * findlow() find the lowest score for each quiz between all students
	 * 
	 * @param lowscores[]
	 */
	public void findlow(Student[] a) {
		// if there is no student input, return
		if (a[0] == null) {
			return;
		}
		int[] storeScore = new int[lowscores.length];
		// initialize for finding
		for (int scoreIndex = 0; scoreIndex < lowscores.length; scoreIndex++) {
			lowscores[scoreIndex] = a[0].getScore()[scoreIndex];
			storeScore[scoreIndex] = a[0].getScore()[scoreIndex];
		}

		for (int st = 1; st < a.length; st++) {
			if (a[st] == null) {
				return;
			}
			// finding the highest
			for (int scoreIndex = 0; scoreIndex < lowscores.length; scoreIndex++) {
				storeScore[scoreIndex] = a[st].getScore()[scoreIndex];
				if (storeScore[scoreIndex] < lowscores[scoreIndex]) {
					lowscores[scoreIndex] = storeScore[scoreIndex];
				}
			}
		}
	}

	/*
	 * findavg() find the average score for each quiz between all students
	 * 
	 * @param avgscores[]
	 */
	public void findavg(Student[] a) {
		if (a[0] == null) {
			return;
		}
		int stuCount = 0;
		for (Student st : a) {
			if (st == null) {
				break;
			}
			for (int scoreIndex = 0; scoreIndex < avgscores.length; scoreIndex++) {
				avgscores[scoreIndex] += st.getScore()[scoreIndex];
			}
			stuCount++;
		}
		for (int scoreIndex = 0; scoreIndex < avgscores.length; scoreIndex++) {
			avgscores[scoreIndex] /= stuCount;
		}
	}

	/*
	 * toPrintValue()
	 * 
	 * print the high low and average scores
	 */
	public void toPrintValue() {
		System.out.print("High Score");
		for (int i = 0; i < highscores.length; i++) {
			System.out.print("\t" + highscores[i]);
		}
		System.out.println();

		System.out.print("Low Score");
		for (int i = 0; i < lowscores.length; i++) {
			System.out.print("\t" + lowscores[i]);
		}
		System.out.println();

		System.out.print("Average\t");
		for (int i = 0; i < avgscores.length; i++) {
			System.out.print("\t" + String.format("%.1f", avgscores[i]));
		}
		System.out.println();

	}

}
