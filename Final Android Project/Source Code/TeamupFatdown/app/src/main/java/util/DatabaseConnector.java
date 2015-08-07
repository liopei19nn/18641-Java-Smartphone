package util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class provides easy connection and creation of TeamUpFatDownDatabase database,
 * which is used in the Change class.
 */
public class DatabaseConnector {

    private static final String DATABASE_NAME = "TeamUpFatDownDatabase";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseConnector(Context context) {
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    /**
     * Open the database connection
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = databaseOpenHelper.getWritableDatabase();
    }

    /**
     * Close the database connection
     */
    public void close() {
        if (database != null)
            database.close(); // close the database connection
    } // end method close

    /**
     * Insert information into the table named changes
     *
     * @param Username
     * @param date1
     * @param weight1
     * @param height1
     * @param goal1
     * @param date2
     * @param weight2
     * @param height2
     * @param goal2
     */
    public void insertchange(String Username, String date1, int weight1, int height1, int goal1,
                             String date2, int weight2, int height2, int goal2) {
        ContentValues newchange = new ContentValues();
        newchange.put("Username", Username);
        newchange.put("date1", date1);
        newchange.put("weight1", weight1);
        newchange.put("height1", height1);
        newchange.put("goal1", goal1);

        newchange.put("date2", date2);
        newchange.put("weight2", weight2);
        newchange.put("height2", height2);
        newchange.put("goal2", goal2);
        open(); // open the database
        database.insert("changes", null, newchange);
        close(); // close the database
    }

    /**
     * insert Useranme, path1 and path2 into the table named changes2
     *
     * @param Username
     * @param path1
     * @param path2
     */
    public void insertchange2(String Username, String path1, String path2) {
        ContentValues newchange2 = new ContentValues();
        newchange2.put("Username", Username);
        newchange2.put("path1", path1);
        newchange2.put("path2", path2);
        open(); // open the database
        database.insert("changes2", null, newchange2);
        close(); // close the database
    }

    /**
     * update partial information of the tabled named changes by Username
     *
     * @param Username
     * @param date1
     * @param weight1
     * @param height1
     * @param goal1
     */
    public void updatechangeLeft(String Username, String date1, int weight1, int height1, int goal1) {
        ContentValues editchange = new ContentValues();
        editchange.put("date1", date1);
        editchange.put("weight1", weight1);
        editchange.put("height1", height1);
        editchange.put("goal1", goal1);

        open(); // open the database
        database.update("changes", editchange, "Username=" + "'" + Username + "'", null);
        close(); // close the database
    }

    /**
     * update partial information of the tabled named changes by Username
     *
     * @param Username
     * @param date2
     * @param weight2
     * @param height2
     * @param goal2
     */
    public void updatechangeRight(String Username, String date2, int weight2, int height2, int goal2) {
        ContentValues editchange = new ContentValues();
        editchange.put("date2", date2);
        editchange.put("weight2", weight2);
        editchange.put("height2", height2);
        editchange.put("goal2", goal2);

        open(); // open the database
        database.update("changes", editchange, "Username=" + "'" + Username + "'", null);
        close(); // close the database
    } // end method updatechange

    /**
     * update the path1 of the table named changes2 by Username
     *
     * @param Username
     * @param path1
     */
    public void updatechange2_path1(String Username, String path1) {
        ContentValues editchange = new ContentValues();
        editchange.put("path1", path1);
        open(); // open the database
        database.update("changes2", editchange, "Username=" + "'" + Username + "'", null);
        close(); // close the database
    }

    /**
     * update the path2 of the table named changes2 by Username
     *
     * @param Username
     * @param path2
     */
    public void updatechange2_path2(String Username, String path2) {
        ContentValues editchange = new ContentValues();
        editchange.put("path2", path2);
        open(); // open the database
        database.update("changes2", editchange, "Username=" + "'" + Username + "'", null);
        close(); // close the database
    }

    /**
     * Get a Cursor containing all information about the change specified
     * by the given id
     *
     * @param Username
     * @return
     */
    public Cursor getOnechange(String Username) {
        open();
        return database.query(
                "changes", null, "Username=" + "'" + Username + "'", null, null, null, null);

    }

    /**
     * get one from the table named changes2 by Username
     *
     * @param UserName
     * @return
     */
    public Cursor getOnechange2(String UserName) {
        open();
        return database.query(
                "changes2", null, "Username=" + "'" + UserName + "'", null, null, null, null);

    }

    /**
     * Delete the change specified by the given String name
     *
     * @param id
     */
    public void deletechange(long id) {
        open();
        database.delete("changes", "_id=" + id, null);
        close();
    }


    /**
     * Inner class: Database helper
     */
    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        /**
         * Constructor
         *
         * @param context
         * @param name
         * @param factory
         * @param version
         */
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to create a new table named changes
            String createQuery = "CREATE TABLE changes" +
                    "(_id integer primary key autoincrement," +
                    "Username String, date1 String, weight1 Integer, height1 Integer,goal1 Integer,"
                    + "date2 String, weight2 Integer, height2 Integer, goal2 Integer);";

            db.execSQL(createQuery); // execute the query

            // query to create a new table named changes2
            String createQuery2 = "CREATE TABLE changes2" +
                    "( Username String primary key, path1 String, path2 String);";

            db.execSQL(createQuery2); // execute the query
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
        }
    }
}
