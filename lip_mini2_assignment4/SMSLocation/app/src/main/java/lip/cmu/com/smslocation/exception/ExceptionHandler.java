package lip.cmu.com.smslocation.exception;

import android.app.AlertDialog;
import android.util.Log;

/**
 * Created by Leo on 7/15/15.
 */
public class ExceptionHandler {

    private final String ERROR_TITLE = "ERROR ";// Exception Dialog Title

    // Constructor
    public ExceptionHandler(String s){
        Log.e(null, ERROR_TITLE + s);// log the exception in System.out of terminal
    }

}
