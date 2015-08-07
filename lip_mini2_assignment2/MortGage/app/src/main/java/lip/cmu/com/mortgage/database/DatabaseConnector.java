package lip.cmu.com.mortgage.database;
/**
 * Assigment 1
 *
 * Name : Li Pei
 * Andrew ID : lip
 *
 */
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseConnector {

    public static final String TABLE_NAME = "Mortgage"; // set the table name for app in SQLite

    // set the column name for each column
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PP = "PurchasedPrice";
    public static final String MT = "MortgageTerm";
    public static final String IR = "InterestRate";
    public static final String TMP = "TotalMonthlyPayment";
    public static final String TP = "TotalPayment";
    public static final String FPD = "FirstPaymentDate";
    public static final String POD = "PayOffDate";
    public static final String TIME = "LogTimeStamp";


    // set the database and writer
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper


    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, TABLE_NAME, null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException
    {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    } // end method close


    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the mortgage table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named "Mortgage"
            String createQuery = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NAME + " TEXT,"
                    + PP + " TEXT,"
                    + MT + " TEXT,"
                    + IR + " TEXT,"
                    + TMP + " TEXT,"
                    + TP + " TEXT,"
                    + FPD + " TEXT,"
                    + POD + " TEXT,"
                    + TIME + " TEXT)";

            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        } // end method onUpgrade
    } // end class DatabaseOpenHelper

    // return a Cursor with all mortgage information in the database
    public Cursor getAllMortgage()
    {
        return database.query(TABLE_NAME, new String[] {"_id,name"},
                null, null, null, null, "name");
    } // end method getAllMortgage


    // inserts a new mortgage in the database
    public void insertMortgage(String name, String pp, String mt,
                              String ir, double tmp,double tp, String ftp,String pod,String time)
    {

        // get the content value container and set the entry information
        // in the container
        ContentValues newMortgage = new ContentValues();
        newMortgage.put(NAME, name);
        newMortgage.put(PP, pp);
        newMortgage.put(MT, mt);
        newMortgage.put(IR, ir);
        newMortgage.put(TMP, tmp);
        newMortgage.put(TP, tp);
        newMortgage.put(FPD, ftp);
        newMortgage.put(POD, pod);
        newMortgage.put(TIME, time);
        open(); // open the database
        // write entry in database
        database.insert(TABLE_NAME, null, newMortgage);
        close(); // close the database
    } // end method insertContact

    // get a Cursor containing all information about the mortgage specified
    // by the given id
    public Cursor getOneMortgage(long id)
    {
        return database.query(
                TABLE_NAME, null, "_id=" + id, null, null, null, null);
    } // end method getOneMortgage



}
