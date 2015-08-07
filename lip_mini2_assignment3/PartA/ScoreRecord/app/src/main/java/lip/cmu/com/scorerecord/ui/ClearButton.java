package lip.cmu.com.scorerecord.ui;
/*
*
* Assignment 3 Part A
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lip.cmu.com.scorerecord.R;
import lip.cmu.com.scorerecord.database.DatabaseConnector;
import lip.cmu.com.scorerecord.util.OnFragmentInteractionListener;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;


// Fragment Implementation for this project
public class ClearButton extends Fragment implements OnClickListener{

    // fragment listener for interact with main activity
    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // set the view of button
        View view =  inflater.inflate(R.layout.clear_button,container,false);
        Button b = (Button)view.findViewById(R.id.mainpage_clear_button);
        b.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v){
        // if you click the button, it will click the whole database
        // and LHM
        DatabaseConnector db = new DatabaseConnector(getActivity().getApplicationContext());
        db.deleteAll();

        // after delete, it will interact with main activity to
        // refresh the mainpage scores list
        mListener.onFragmentInteraction(v);
        Toast.makeText(getActivity(), "Data Deleted Successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {

        // attach this fragment to main activity
        super.onAttach(activity);
        try {
            mListener =
                    (OnFragmentInteractionListener)  activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
