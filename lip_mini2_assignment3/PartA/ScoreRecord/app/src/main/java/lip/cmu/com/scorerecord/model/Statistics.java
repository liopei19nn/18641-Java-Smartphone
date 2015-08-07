package lip.cmu.com.scorerecord.model;

/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import lip.cmu.com.scorerecord.R;
import java.util.LinkedHashMap;


public class Statistics extends Activity{

    // use linkedhash map instead of arraylist because it is easy to
    // do the delete and insert operation
    private static LinkedHashMap<Integer,double[]> savescore;

    // array for storing statistics result
    public double[] high_score;
    public double[] low_score;
    public double[] avg_score;


    // get low scores for UI
    private TextView low_q1;
    private TextView low_q2;
    private TextView low_q3;
    private TextView low_q4;
    private TextView low_q5;

    // get high scores for UI
    private TextView high_q1;
    private TextView high_q2;
    private TextView high_q3;
    private TextView high_q4;
    private TextView high_q5;



    // get average scores for UI
    private TextView avg_q1;
    private TextView avg_q2;
    private TextView avg_q3;
    private TextView avg_q4;
    private TextView avg_q5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_page);

        // calculate the high/low/AVG scores
        calculate_stat();

        // print high score
        high_q1 = (TextView) findViewById(R.id.statpage_high_q1);
        high_q1.setText(String.format("%.2f", high_score[0]));

        high_q2 = (TextView) findViewById(R.id.statpage_high_q2);
        high_q2.setText(String.format("%.2f", high_score[1]));

        high_q3 = (TextView) findViewById(R.id.statpage_high_q3);
        high_q3.setText(String.format("%.2f", high_score[2]));

        high_q4 = (TextView) findViewById(R.id.statpage_high_q4);
        high_q4.setText(String.format("%.2f", high_score[3]));

        high_q5 = (TextView) findViewById(R.id.statpage_high_q5);
        high_q5.setText(String.format("%.2f", high_score[4]));



        //print low score
        low_q1 = (TextView) findViewById(R.id.statpage_low_q1);
        low_q1.setText(String.format("%.2f", low_score[0]));

        low_q2 = (TextView) findViewById(R.id.statpage_low_q2);
        low_q2.setText(String.format("%.2f", low_score[1]));

        low_q3 = (TextView) findViewById(R.id.statpage_low_q3);
        low_q3.setText(String.format("%.2f", low_score[2]));

        low_q4 = (TextView) findViewById(R.id.statpage_low_q4);
        low_q4.setText(String.format("%.2f", low_score[3]));

        low_q5 = (TextView) findViewById(R.id.statpage_low_q5);
        low_q5.setText(String.format("%.2f", low_score[4]));




        //print average score
        avg_q1 = (TextView) findViewById(R.id.statpage_avg_q1);
        avg_q1.setText(String.format("%.2f", avg_score[0]));

        avg_q2 = (TextView) findViewById(R.id.statpage_avg_q2);
        avg_q2.setText(String.format("%.2f", avg_score[1]));

        avg_q3 = (TextView) findViewById(R.id.statpage_avg_q3);
        avg_q3.setText(String.format("%.2f", avg_score[2]));

        avg_q4 = (TextView) findViewById(R.id.statpage_avg_q4);
        avg_q4.setText(String.format("%.2f", avg_score[3]));

        avg_q5 = (TextView) findViewById(R.id.statpage_avg_q5);
        avg_q5.setText(String.format("%.2f", avg_score[4]));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // insert a score in the save score list map
    // if the id is existing, return false and refuse
    // to add it in LHM and database
    public static boolean addscore(int id, double[] score){

        // if it is the first time for insert, build a new
        // one first
        if (savescore == null){
            savescore = new LinkedHashMap<>();
            savescore.put(id,score);
        }else{
            // if there is already a same id in the LHM
            // return false
            if (savescore.get(id) != null){
                return false;
            }
            // then save the unique id with score
            savescore.put(id,score);
        }
        return true;
    }

    // delete one id from LHM
    public static void deleteonescore(int id){
        // if it is empty
        if (savescore == null){
            return;
        }

        // remove the id
        savescore.remove(id);

    }


    // delete all score in LHM
    public static void deleteallscore(){
        if (savescore == null){
            return;
        }
        savescore = new LinkedHashMap<>();

    }


    // check if a id exist in LHM
    public static boolean isIDExist(int id){
        if (savescore== null){
            return false;
        }

        if (savescore.get(id) == null){
            return false;
        }
        return true;
    }



    // calculate the statistics
    private void calculate_stat(){
        high_score= new double[5];
        low_score = new double[5];
        avg_score = new double[5];
        // if there is no score record, return and
        // keep all score array all zero
        if (savescore == null || savescore.size() == 0){
            return;
        }
        findhigh();
        findlow();
        findavg();
    }

    // build the high score array
    private void findhigh(){
        for (int scoreIndex = 0; scoreIndex < high_score.length; scoreIndex++) {
            high_score[scoreIndex] = 0;
        }

        for (int index = 0; index < high_score.length; index ++){
            for (Integer id : savescore.keySet()){
                if (savescore.get(id)[index] > high_score[index]){
                    high_score[index] =  savescore.get(id)[index];
                }
            }
        }
    }

    // build the low score array
    private void findlow(){
        for (int scoreIndex = 0; scoreIndex <low_score.length; scoreIndex++) {
            low_score[scoreIndex] = 100;
        }


        for (int index = 0; index < low_score.length; index ++){
            for (Integer id : savescore.keySet()){
                if (savescore.get(id)[index] < low_score[index]){
                    low_score[index] =  savescore.get(id)[index];
                }
            }
        }
    }


    // build the average score array
    private void findavg(){
        for (int index = 0; index < avg_score.length; index ++){
            for (Integer id : savescore.keySet()){
                avg_score[index] += savescore.get(id)[index];

            }
        }
        for (int index = 0; index < avg_score.length; index ++){

                avg_score[index] /= savescore.size();

        }



    }


}
