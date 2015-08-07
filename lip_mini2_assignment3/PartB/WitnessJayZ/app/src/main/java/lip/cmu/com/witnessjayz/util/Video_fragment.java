package lip.cmu.com.witnessjayz.util;
/*
*
* Assignment 3 Part B
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
import android.widget.Button;
import lip.cmu.com.witnessjayz.R;


public class Video_fragment extends Fragment {

    // get buttons
    Button button_left;
    Button button_right;

    // fragment listener to interact with Video Activity
    private OnFragmentInteractionListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_button,container,false);

        // initialize button
        button_left = (Button)view.findViewById(R.id.fragment_left_button);
        button_right = (Button)view.findViewById(R.id.fragment_right_button);

        button_left.setOnClickListener(new Video_fragment.onPlayVideoListener());
        button_right.setOnClickListener(new Video_fragment.onPlayVideoListener());

        return view;
    }


    private class onPlayVideoListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mListener.onFragmentInteraction(v);
        }
    }


    // Attach fragment to activity
    @Override
    public void onAttach(Activity activity) {
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
