package lip.cmu.com.scorerecord.ui;
/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import lip.cmu.com.scorerecord.R;
import lip.cmu.com.scorerecord.database.DatabaseConnector;
import lip.cmu.com.scorerecord.model.Statistics;

public class Mainpage_Adapter extends BaseAdapter{

    private Context context;//use context to connect
    private Cursor cursor;  //the data from database is a cursor object
    private LinearLayout layout; // set the layout


    public Mainpage_Adapter(Context context,Cursor cursor){
        this.context=context;
        this.cursor=cursor;
    }


    @Override
    public int getCount() {
        return cursor.getCount();//return the size of the cursor
    }
    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);//load layout
        //inflate the layout with data from database
        layout=(LinearLayout)inflater.inflate(R.layout.list_table,null);//the view that contain each database record

        //get the TextView for display
        TextView SItv=(TextView)layout.findViewById(R.id.list_SI);
        TextView Q1Stv=(TextView)layout.findViewById(R.id.list_Q1S);
        TextView Q2Stv=(TextView)layout.findViewById(R.id.list_Q2S);
        TextView Q3Stv=(TextView)layout.findViewById(R.id.list_Q3S);
        TextView Q4Stv=(TextView)layout.findViewById(R.id.list_Q4S);
        TextView Q5Stv=(TextView)layout.findViewById(R.id.list_Q5S);



        // read data from database
        cursor.moveToPosition(position);
        String SI=cursor.getString(cursor.getColumnIndex(DatabaseConnector.ID));
        String Q1S=cursor.getString(cursor.getColumnIndex(DatabaseConnector.Q1));
        String Q2S=cursor.getString(cursor.getColumnIndex(DatabaseConnector.Q2));
        String Q3S=cursor.getString(cursor.getColumnIndex(DatabaseConnector.Q3));
        String Q4S=cursor.getString(cursor.getColumnIndex(DatabaseConnector.Q4));
        String Q5S=cursor.getString(cursor.getColumnIndex(DatabaseConnector.Q5));


        // it is used for first time use app
        // it will read data from database to LHM in Statistics
        // in usual run time, this is no use
        int id = Integer.parseInt(SI);
        if (Statistics.isIDExist(id)){
            double[] score = {Double.parseDouble(Q1S),Double.parseDouble(Q2S),Double.parseDouble(Q3S)
                    ,Double.parseDouble(Q4S),Double.parseDouble(Q5S)};
            Statistics.addscore(id,score);
        }

        // display all scores in database and show it in screen
        // if you input id is 0XXX, then it will display 0XXX
        if (SI.length() < 4){
            SI = 0+SI;
        }
        SItv.setText(SI);
        Q1Stv.setText(Q1S);
        Q2Stv.setText(Q2S);
        Q3Stv.setText(Q3S);
        Q4Stv.setText(Q4S);
        Q5Stv.setText(Q5S);

        return layout;
    }
}
