package lip.cmu.com.scorerecord.database;
/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import lip.cmu.com.scorerecord.model.Statistics;

public class DatabaseConnector {

    public static final String TABLE_NAME = "ScoreRecord"; // set the table name for app in SQLite

    // set the column name for each column
    public static final String ID = "ID";
    public static final String Q1 = "Q1";
    public static final String Q2 = "Q2";
    public static final String Q3 = "Q3";
    public static final String Q4 = "Q4";
    public static final String Q5 = "Q5";


    // set the database and writer
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper


    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context) {
        // create a new DatabaseOpenHelper
        databaseOpenHelper = new DatabaseOpenHelper(context, TABLE_NAME, null, 1);
    } // end DatabaseConnector constructor


    // open the database connection
    public void open() throws SQLException {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close() {
        if (database != null)
            database.close(); // close the database connection
    } // end method close


    // Used for open database
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        // public constructor
        public DatabaseOpenHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the mortgage table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to create a new table named "Mortgage"
            String createQuery = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY,"
                    + Q1 + " TEXT,"
                    + Q2 + " TEXT,"
                    + Q3 + " TEXT,"
                    + Q4 + " TEXT,"
                    + Q5 + " TEXT)";

            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
        } // end method onUpgrade
    } // end class DatabaseOpenHelper


    // return a Cursor with all score information in the database
    public Cursor getAllScore() {
        return database.query(TABLE_NAME, new String[]{ID, Q1, Q2, Q3, Q4, Q5},
                null, null, null, null, null);
    } // end method getAllScore


    // inserts a new score in the database
    public void insertScore(int id, double score1, double score2, double score3, double score4, double score5) {
        // get the content value container and set the entry information
        // in the container
        ContentValues newScore = new ContentValues();
        newScore.put(ID, id);
        newScore.put(Q1, score1);
        newScore.put(Q2, score2);
        newScore.put(Q3, score3);
        newScore.put(Q4, score4);
        newScore.put(Q5, score5);

        open(); // open the database
        // write entry in database
        try {
            database.insert(TABLE_NAME, null, newScore);
        } catch (Exception e) {
            Log.e(null,"Insert Score Error");
        }

        close(); // close the database
    } // end method insertScore



    // delete one score in the database
    public void deleteOne(long id) {
        open(); // open the database
        database.delete(TABLE_NAME, ID + "=" + id, null);
        close(); // close the database
    } // end method deleteOne



    // delete all scores in the database
    public void deleteAll() {
        open(); // open the database
        database.execSQL("DELETE FROM " + TABLE_NAME + ";");
        Statistics.deleteallscore();
        close(); // close the database
    } // end method deleteAll


}
