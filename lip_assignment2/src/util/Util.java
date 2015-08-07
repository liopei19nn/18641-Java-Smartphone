/**
 * 
 */
package util;

import java.io.*;
import java.util.StringTokenizer;
import student.Student;
import exception.StudentNumerOutOfBound;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class Util {
	/*
	 * readFile()
	 * 
	 * Reads the file and builds student array. Open the file using FileReader
	 * Object. In a loop read a line using readLine method. Tokenize each line
	 * using StringTokenizer Object. Each token is converted from String to
	 * Integer using parseInt method. Value is then saved in the right property
	 * of Student Object. ￼
	 */
	private static final int MAX_STUDENT_NUMBER = 40;

	public static Student[] readFile(String filename, Student[] stu) {
		BufferedReader br = null;
		String line;
		int stuIndex = 0;// count of number of input students
		try {
			// Reads the file and builds student array. Open the file using
			// FileReader Object.
			br = new BufferedReader(new FileReader(new File(filename)));
			boolean eof = false;
			// skip the first line as student chart title
			line = br.readLine();
			if (line == null) { // if first title line is empty
				eof = true;
			}

			while (!eof) {
				line = br.readLine();

				if (line == null) {
					eof = true;
					break;
				} else {
					if (stuIndex < MAX_STUDENT_NUMBER) {
						StringTokenizer str = new StringTokenizer(line);
						int SID = 0;
						int[] storeScore = new int[5];
						int storeCount = 0;

						// get SID from a line
						SID = Integer.parseInt(str.nextToken());

						// get scores from a line
						while (str.hasMoreTokens()) {
							storeScore[storeCount] = Integer.parseInt(str
									.nextToken());
							storeCount++;
						}

						// instantiate a new student for this line
						stu[stuIndex] = new Student();
						stu[stuIndex].setSID(SID); // set SID
						stu[stuIndex].setScore(storeScore); // set scores

						// next run
						stuIndex++;

					} else {
						// throw Input Student Number Out Of Bound Exception
						// when input line numbers > MAX_STUDENT_NUMBER
						if (line != null) {
							throw new StudentNumerOutOfBound(
									"Input Student Number Above 40");
						}
					}
				}
			}

		} catch (StudentNumerOutOfBound s) {
			// catch too many input students exception
			System.out.println("Error -- " + s.toString());
		} catch (IOException e) {
			// catch IOException
			System.out.println("Error -- " + e.toString());
		} finally {
			try {
				br.close();
			} catch (IOException brCloseException) {
				// catch IOExcetion for br.close()
				System.out.println("Error -- " + brCloseException.toString());
			}
		}
		// return the value
		return stu;

	}
}
