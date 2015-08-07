package exception;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class AutoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2137441439449991446L;

	private int errno;
	private String name;

	public AutoException(CustomExceptionEnum exception) {
		this.errno = exception.getValue();
		this.name = exception.toString();
		log();

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.errno);
		sb.append(" ");
		sb.append(this.name);
		return sb.toString();
	}

	public int getErrorNumber() {
		return errno;
	}

	public void log() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		StringBuffer output = new StringBuffer();
		output.append(ts.toString());
		output.append("\t");
		output.append("errno ");
		output.append(this.errno);
		output.append(":");
		output.append(this.name);
		output.append("\n");
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("exception_log.txt", true)));
			bw.write(output.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}

	}

}
