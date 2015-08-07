package exception;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class CustomIOException extends AutoException {

	private static final long serialVersionUID = -1766348172930812730L;
	
	// send the exception to AutoException
	// this is handled in the FileIO by the fixHelper
	public CustomIOException(CustomExceptionEnum exception) {
		super(exception);
	}

}
