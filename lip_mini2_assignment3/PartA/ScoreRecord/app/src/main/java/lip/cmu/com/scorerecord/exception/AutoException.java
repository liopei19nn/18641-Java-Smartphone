package lip.cmu.com.scorerecord.exception;
/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.AlertDialog;
import android.util.Log;

public class AutoException {

    private final String ERROR_TITLE = "ERROR";// Exception Dialog Title

    // Constructor
    public AutoException(String s, AlertDialog.Builder b){
        Log.e(null,s);// log the exception in System.out of terminal
        buildErrorDialog(s,b);
    }

    /*
    * buildErrorDialog()
    *
    * build the AlertDialog with information and error title
    *
    * */
    public void buildErrorDialog(String s, AlertDialog.Builder b){
                // set dialog title & message, and provide Button to dismiss
                b.setTitle(ERROR_TITLE);
                b.setMessage(s);
    }

}
