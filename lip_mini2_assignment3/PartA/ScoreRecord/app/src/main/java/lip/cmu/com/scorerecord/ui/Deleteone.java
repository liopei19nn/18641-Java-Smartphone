package lip.cmu.com.scorerecord.ui;
/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import lip.cmu.com.scorerecord.R;
import lip.cmu.com.scorerecord.database.DatabaseConnector;
import lip.cmu.com.scorerecord.exception.AutoException;
import lip.cmu.com.scorerecord.model.Statistics;



public class Deleteone extends Activity {

    private EditText et_ID;// input id EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleteone_page);

        // get the EditText from UI
        et_ID = (EditText) findViewById(R.id.delete_IDEditText);

        // set event listener for the Delete Button
        Button delete_button = (Button) findViewById(R.id.deleteonepage_delete_button);
        delete_button.setOnClickListener(deleteButtonClicked);
    }


    // responds to event generated when user clicks the Delete Button
    View.OnClickListener deleteButtonClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            // test if the input is legal
            String testFlag = testInput();
            if (testFlag.equals("true"))// if legal input
            {
                // delete this score in LHM of Statistics
                deleteonescore();
                AsyncTask<Object, Object, Object> saveContactTask =
                        new AsyncTask<Object, Object, Object>()
                        {
                            @Override
                            protected Object doInBackground(Object... params)
                            {

                                deleteonefromdatabase(); // delete score from the database
                                return null;
                            } // end method doInBackground

                            @Override
                            protected void onPostExecute(Object result)
                            {
                                finish(); // return to the previous Activity
                            } // end method onPostExecute
                        }; // end AsyncTask

                saveContactTask.execute((Object[]) null);
            } // end if
            else
            {
                // create a new AlertDialog Builder
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(Deleteone.this);

                // set dialog title & message, and provide Button to dismiss
                new AutoException(testFlag,builder);
                builder.setPositiveButton(R.string.errorButton, null);
                builder.show(); // display the Dialog
            } // end else
        } // end method onClick
    }; // end deleteButtonClicked


    /*
    * testInput()
    *
    * test the input id
    *
    * if it is not 4 digit or not exist in the database, return false
    * */
    private String testInput(){
        int id;

        // if input is not 4 digits
        if (et_ID.getText().length() != 4 ){
            return "4 Digits ID please";
        } else{
            id = Integer.parseInt(et_ID.getText().toString());
        }

        // if the id is not in the LHM
        if (!Statistics.isIDExist(id)){
            return "ID : " + id + " Not Exist";
        }

        return "true";
    }

    // delete one score from database
    private void deleteonescore() {
        int id = Integer.parseInt(et_ID.getText().toString());
        if (!Statistics.isIDExist(id)){
            return;
        }
        Statistics.deleteonescore(id);
    }

    // delete one score from database
    private void deleteonefromdatabase()
    {
        // get DatabaseConnector to interact with the SQLite database
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        int id = Integer.parseInt(et_ID.getText().toString());
        databaseConnector.deleteOne(id);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
