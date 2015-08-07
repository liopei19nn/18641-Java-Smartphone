/**
 * 
 */
package exception;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class StudentScoreNumberException extends Exception {
	/*
	 * StudentScoreNumberException() Constructor passing error message to
	 * Exception constructor
	 */
	private static final long serialVersionUID = 1L;

	public StudentScoreNumberException(String msg) {
		super(msg);
	}
}
