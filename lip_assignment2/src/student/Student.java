/**
 * 
 */
package student;

import prototype.*;
import exception.StudentScoreNumberException;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class Student extends People implements Printer {
	private int SID;
	private int scores[] = new int[5];

	/*
	 * getSID() getter for SID
	 * 
	 * @param SID
	 */

	public int getSID() {
		return SID;
	}

	/*
	 * setSID() setter for SID
	 * 
	 * @param SID
	 */
	public void setSID(int studentID) {
		this.SID = studentID;
	}

	/*
	 * getScore() getter for scores
	 * 
	 * @param scores
	 */
	public int[] getScore() {
		return scores;
	}

	/*
	 * setScore() setter for scores
	 * 
	 * @param scores
	 * 
	 * Exception : more than 5 input in score value Handle : if more than 5
	 * inputs, take first 5, if less than 5, leave the rest 0;
	 */
	public void setScore(int[] scoreValue) {
		try {
			if (scoreValue.length != scores.length) {
				throw new StudentScoreNumberException("Student ID : "
						+ String.format("%04d", this.getSID()));
			}
			this.scores = scoreValue;

		} catch (StudentScoreNumberException s) {
			System.out.println("Error -- " + s.toString());
			if (scoreValue.length < 5) {
				for (int i = 0; i < scoreValue.length; i++) {
					this.scores[i] = scoreValue[i];
				}
			} else {
				for (int i = 0; i < scores.length; i++) {
					this.scores[i] = scoreValue[i];
				}
			}
		}

	}

	/*
	 * toPrintValue() print student ID and scores
	 */
	public void toPrintValue() {
		// padding for SID less than 4 digits in decimal
		System.out.print(String.format("%04d", SID));

		for (int i = 0; i < scores.length; i++) {
			System.out.print("\t" + String.format("%03d", scores[i]));
		}
		System.out.println();
	}
}
