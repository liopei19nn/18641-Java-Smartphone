package lip.cmu.com.mortgage.ui;
/**
 * Assigment 1
 *
 * Name : Li Pei
 * Andrew ID : lip
 *
 */
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.os.AsyncTask;
import lip.cmu.com.mortgage.calculator.AddMortgage;
import lip.cmu.com.mortgage.database.DatabaseConnector;
import lip.cmu.com.mortgage.R;


public class MainPage extends ListActivity {
    public static final String ROW_ID = "row_id"; // row id for main page layout row number
    private ListView MortgageListView;  // list view
    private CursorAdapter MortgageAdapter;  // adapter




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the list view
        MortgageListView = getListView();
        MortgageListView.setOnItemClickListener(viewMortgageListener);

        // get the array of entry by "name" from database
        String[] from = new String[] {"name"};
        int[] to  = new int[] {R.id.mainPageTextView};
        MortgageAdapter = new SimpleCursorAdapter(MainPage.this, R.layout.activity_main_page,null,from,to);
        setListAdapter(MortgageAdapter);

    }
    @Override
    protected void onResume()
    {
        super.onResume(); // call super's onResume method

        // create new GetContactsTask and execute it
        new GetMortgageTask().execute((Object[]) null);
    } // end method onResume


    // performs database query outside GUI thread
    private class GetMortgageTask extends AsyncTask<Object, Object, Cursor>
    {
        DatabaseConnector databaseConnector =
                new DatabaseConnector(MainPage.this);

        // perform the database access
        @Override
        protected Cursor doInBackground(Object... params)
        {
            databaseConnector.open();

            // get a cursor containing all mortgage
            return databaseConnector.getAllMortgage();
        } // end method doInBackground

        // use the Cursor returned from the doInBackground method
        @Override
        protected void onPostExecute(Cursor result)
        {
            MortgageAdapter.changeCursor(result); // set the adapter's Cursor
            databaseConnector.close();
        } // end method onPostExecute
    } // end class GetMortgageTask

    @Override
    protected void onStop()
    {
        Cursor cursor = MortgageAdapter.getCursor(); // get current Cursor

        if (cursor != null)
            cursor.deactivate(); // deactivate it

        MortgageAdapter.changeCursor(null); // adapted now has no Cursor
        super.onStop();
    } // end method onStop



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        // Jump to add mortgage page
        Intent addNewMortgage = new Intent(MainPage.this,AddMortgage.class);
        startActivity(addNewMortgage);
        return super.onOptionsItemSelected(item);
    }

    OnItemClickListener viewMortgageListener = new OnItemClickListener() {

        // Click on each entry, jump to page with corresponding information
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // jumpt to ViewMortgage
            Intent viewMortgage = new Intent(MainPage.this,ViewMortgage.class);

            viewMortgage.putExtra(ROW_ID,id);
            startActivity(viewMortgage);
        }
    };
}
