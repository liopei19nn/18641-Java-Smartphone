package lip.cmu.com.scorerecord.ui;
/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.app.Activity;
import lip.cmu.com.scorerecord.R;
import lip.cmu.com.scorerecord.database.*;
import lip.cmu.com.scorerecord.exception.AutoException;
import lip.cmu.com.scorerecord.model.Statistics;


public class AddCalculation extends Activity{


    // EditText for ID and Q1 to Q5 score input
    private EditText et_ID,et_Q1,et_Q2,et_Q3,et_Q4,et_Q5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);


        // get the EditText from UI
        et_ID = (EditText) findViewById(R.id.addpage_IDEditText);
        et_Q1 = (EditText) findViewById(R.id.addpage_Q1EditText);
        et_Q2 = (EditText) findViewById(R.id.addpage_Q2EditText);
        et_Q3 = (EditText) findViewById(R.id.addpage_Q3EditText);
        et_Q4 = (EditText) findViewById(R.id.addpage_Q4EditText);
        et_Q5 = (EditText) findViewById(R.id.addpage_Q5EditText);


        // set event listener for the Save Contact Button
        Button save_button =
                (Button) findViewById(R.id.addpage_save_button);
        save_button.setOnClickListener(saveButtonClicked);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    // responds to event generated when user clicks the Save Button
    View.OnClickListener saveButtonClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            // test if the input is legal
            String testFlag = testInput();
            if (testFlag.equals("true")  && savescore())// if legal input
            {
                AsyncTask<Object, Object, Object> saveContactTask =
                        new AsyncTask<Object, Object, Object>()
                        {
                            @Override
                            protected Object doInBackground(Object... params)
                            {
                                    //add data to database
                                    addToDataBase();

                                return null;
                            } // end method doInBackground

                            @Override
                            protected void onPostExecute(Object result)
                            {
                                finish(); // return to the previous Activity
                            } // end method onPostExecute
                        }; // end AsyncTask

                // save the mortgage to the database using a separate thread
                saveContactTask.execute((Object[]) null);

            } // end if
            // else : input is not legal
            else
            {
                // if the id input exist, input is legal but
                // input for database and LHM is no legal
                if (testFlag.equals("true")){
                    testFlag = "ID Exist : " + et_ID.getText().toString();
                }

                // create a new AlertDialog Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(AddCalculation.this);

                // build dialog with AutoException
                new AutoException(testFlag,builder);
                builder.setPositiveButton(R.string.errorButton, null);
                builder.show(); // display the Dialog

            } // end else
        } // end method onClick
    }; // end OnClickListener saveButtonClicked


    /*
    * savescore()
    *
    * save the score to LHM
    * if LHM already have the same id, return false
    *
    * */
    private boolean savescore() {
        // read in id and Q1-Q5 score
        int id = Integer.parseInt(et_ID.getText().toString());
        double[] score = new double[5];

        score[0] = Double.parseDouble(et_Q1.getText().toString());
        score[1] = Double.parseDouble(et_Q2.getText().toString());
        score[2] = Double.parseDouble(et_Q3.getText().toString());
        score[3] = Double.parseDouble(et_Q4.getText().toString());
        score[4] = Double.parseDouble(et_Q5.getText().toString());

        return Statistics.addscore(id, score);

    }

    /*
    * testInput()
    *
    * handle every illegal input
    *
    * */
    private String testInput(){

        // save all input Quiz score to test
        // the range of input
        double q1;
        double q2;
        double q3;
        double q4;
        double q5;

        // if each input row is empty
        // it is illegal
        if (et_ID.getText().length() == 0 || et_Q1.getText().toString().length() == 0 || et_Q2.getText().toString().length() == 0 ||
                et_Q3.getText().toString().length() == 0||et_Q4.getText().toString().length() == 0||et_Q5.getText().toString().length() == 0){
            return "Empty Input";
        } else {
            q1 = Double.parseDouble(et_Q1.getText().toString());
            q2 = Double.parseDouble(et_Q2.getText().toString());
            q3 = Double.parseDouble(et_Q3.getText().toString());
            q4 = Double.parseDouble(et_Q4.getText().toString());
            q5 = Double.parseDouble(et_Q5.getText().toString());
        }

        // id must be 4 digits
        if(et_ID.getText().length() != 4){
            return "ID Length Must Be 4";
        }

        // quiz score must be between 0 to 100
        if (q1 < 0 || q1 > 100){
            return "Q1 Input Must Be Within 0 to 100";
        }
        if (q2 < 0 || q2 > 100){
            return "Q2 Input Must Be Within 0 to 100";
        }

        if (q3 < 0 || q3 > 100){
            return "Q3 Input Must Be Within 0 to 100";
        }
        if (q4 < 0 || q4 > 100){
            return "Q4 Input Must Be Within 0 to 100";
        }
        if (q5 < 0 || q5 > 100){
            return "Q5 Input Must Be Within 0 to 100";
        }

        return "true";
    }


    private void addToDataBase()
    {
        // get DatabaseConnector to interact with the SQLite database
        DatabaseConnector databaseConnector = new DatabaseConnector(this);


        int id = Integer.parseInt(et_ID.getText().toString());
        double[] score = new double[5];
        score[0] = Double.parseDouble(et_Q1.getText().toString());
        score[1] = Double.parseDouble(et_Q2.getText().toString());
        score[2] = Double.parseDouble(et_Q3.getText().toString());
        score[3] = Double.parseDouble(et_Q4.getText().toString());
        score[4] = Double.parseDouble(et_Q5.getText().toString());

        // insert score into database
        databaseConnector.insertScore(id, score[0], score[1], score[2], score[3], score[4]);

    }





}
