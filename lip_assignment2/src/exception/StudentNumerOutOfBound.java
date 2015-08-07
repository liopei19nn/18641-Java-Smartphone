/**
 * 
 */
package exception;

import java.io.IOException;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class StudentNumerOutOfBound extends IOException {
	private static final long serialVersionUID = 1L;

	/*
	 * StudentNumerOutOfBound() Constructor passing error message to IOException
	 * constructor
	 */
	public StudentNumerOutOfBound(String msg) {
		super(msg);
	}
}
