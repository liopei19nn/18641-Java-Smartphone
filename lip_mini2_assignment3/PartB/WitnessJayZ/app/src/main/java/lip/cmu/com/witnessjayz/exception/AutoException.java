package lip.cmu.com.witnessjayz.exception;
/*
*
* Assignment 3 Part B
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.AlertDialog;
import android.util.Log;


public class AutoException {
    // Error title for AlertDialog
    private static final String ERROR_TITLE = "Error";

    // Constructor
    public AutoException(String s,AlertDialog.Builder b){
        Log.e(null, s);
        getErrorDialog(s,b);
    }

    /*
    * buildErrorDialog()
    *
    * build the AlertDialog with information and error title
    *
    * */
    public void getErrorDialog(String s,AlertDialog.Builder b){
        // set dialog title & message, and provide Button to dismiss
        b.setTitle(ERROR_TITLE);
        b.setMessage(s);
    }
}
