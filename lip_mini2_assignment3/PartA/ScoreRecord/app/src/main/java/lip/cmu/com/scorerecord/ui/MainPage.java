package lip.cmu.com.scorerecord.ui;
/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import lip.cmu.com.scorerecord.R;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import lip.cmu.com.scorerecord.database.DatabaseConnector;
import lip.cmu.com.scorerecord.util.OnFragmentInteractionListener;
import lip.cmu.com.scorerecord.model.Statistics;


public class MainPage extends Activity implements OnClickListener, OnFragmentInteractionListener {


    private Button button_add,button_statistic,button_clearone;// button for input
    private ListView quizscore_listview;// button for listview of scores
    private Intent i;                   // intent to jump
    private DatabaseConnector database; // database connector
    private Mainpage_Adapter adapter;   // list view adatper


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        // init view
        initView();
        // read every thing from database
        selectDB();
    }


    public void initView(){
        // clear button fragment init
        ClearButton fragment = new ClearButton();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment, "fragment");
        fragmentTransaction.commit();


        // add stat and deleteone button
        button_add  = (Button) findViewById(R.id.mainpage_add_button);
        button_statistic = (Button) findViewById(R.id.mainpage_statistic_button);
        button_clearone = (Button) findViewById(R.id.mainpage_deleteone_button);

        button_add.setOnClickListener(this);
        button_statistic.setOnClickListener(this);
        button_clearone.setOnClickListener(this);



        // set scores list view
        quizscore_listview = (ListView) findViewById(R.id.mainpage_listQuizScore);

        // connect to database
        database = new DatabaseConnector(MainPage.this);

    }

    @Override
    protected void onResume(){
        super.onResume();

        // refresh the list with database scores
        selectDB();

    }

    //this method is for fetching data from database
    public void selectDB(){
        database.open();
        Cursor cursor=database.getAllScore();
        adapter=new Mainpage_Adapter(this,cursor);//tie up cursor and adapter
        quizscore_listview.setAdapter(adapter);
        database.close();
    }


    @Override
    public void onClick(View v) {

            switch (v.getId()){

                // for add button
                case R.id.mainpage_add_button :
                    i = new Intent(this, AddCalculation.class);
                    break;

                // for statistics button
                case R.id.mainpage_statistic_button :
                    i = new Intent(this, Statistics.class);
                    break;

                // for delete one button
                case R.id.mainpage_deleteone_button :
                    i = new Intent(this,Deleteone.class);
                    break;
                default:
                    break;
            }
        // start activity
        startActivity(i);
    }



    // listen to the fragment action
    @Override
    public void onFragmentInteraction(View view) {
        selectDB();
    }
}
