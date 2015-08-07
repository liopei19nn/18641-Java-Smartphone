package ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import communication.proxyClient;
import model.activity.Baseball;
import model.activity.Basketball;
import model.activity.Cycling;
import model.activity.Football;
import model.activity.Running;
import model.activity.Soccer;
import model.activity.Sports;
import model.activity.Swimming;
import model.activity.Tennis;
import model.person.Person;
import tongyunl.teamupfatdown.R;

/**
 * This class represents the User Interface in which user could
 * add activity.
 */
public class ChooseActivity extends Activity {
    private Spinner activitySpinner;
    private EditText durationEditText;
    private Button submitButton;
    private String activityName;
    private String duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chooseactivity);
        TextView title1 = (TextView) findViewById(R.id.ChooseActivity_textView0_1);
        TextView title2 = (TextView) findViewById(R.id.ChooseActivity_textView1_1);
        TextView title3 = (TextView) findViewById(R.id.ChooseActivity_textView2_1);
        activitySpinner = (Spinner) findViewById(R.id.ChooseActivity_ActivityName);
        durationEditText = (EditText) findViewById(R.id.ChooseActivity_durationEditText);
        submitButton = (Button) findViewById(R.id.ChooseActivity_button);

        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        String fontPath1 = "roboto/Roboto-LightItalic.ttf";
        String fontPath2 = "roboto/Roboto-Light.ttf";
        String fontPath3 = "droid-sans/DroidSans.ttf";

        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), fontPath3);

        title1.setTypeface(tf0);
        title2.setTypeface(tf1);
        title3.setTypeface(tf1);
        durationEditText.setTypeface(tf2);
        submitButton.setTypeface(tf3);

        MySpinnerAdapter adapter = new MySpinnerAdapter(getApplicationContext(),
                R.layout.ui_dropdownitem,
                Arrays.asList(getResources().getStringArray(R.array.ChooseActivity_Activity)));
        activitySpinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = activitySpinner.getSelectedItem().toString();
                duration = durationEditText.getText().toString();
                if (duration.length() == 0) {
                    sendToast("Missing duration");
                }
                else if(Integer.parseInt(duration) == 0){
                    sendToast("Duration can not be 0");
                }
                else if (isConnect()) {
                    AsyncTask<Object, Object, Object> saveActivityTask =
                            new AsyncTask<Object, Object, Object>() {
                                @Override
                                protected Object doInBackground(Object... params) {
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Object result) {
                                    if (updateActivity()) {
                                        durationEditText.setText("");
                                        activitySpinner.setSelection(0);
                                        sendToast("Add activity successfully");
                                    }
                                }
                            };
                    saveActivityTask.execute((Object[]) null);

                }
            }
        });
    }

    /**
     * Update the activity of user.
     */
    private boolean updateActivity() {
        int time = Integer.parseInt(duration);
        Sports sport = null;
        if (activityName.equals("Swimming")) {
            sport = new Swimming(activityName, time);
        } else if (activityName.equals("Soccer")) {
            sport = new Soccer(activityName, time);
        } else if (activityName.equals("Basketball")) {
            sport = new Basketball(activityName, time);
        } else if (activityName.equals("Football")) {
            sport = new Football(activityName, time);
        } else if (activityName.equals("Cycling")) {
            sport = new Cycling(activityName, time);
        } else if (activityName.equals("Running")) {
            sport = new Running(activityName, time);
        } else if (activityName.equals("Baseball")) {
            sport = new Baseball(activityName, time);
        } else {
            sport = new Tennis(activityName, time);
        }
        Person.get(this).updateCal(sport);
        proxyClient proxy = new proxyClient(Person.get(this));
        String result = proxy.addOneToDB();
        if (result.equals("No Internet Access")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the device connect to the Internet.
     * @return
     */
    private boolean isConnect() {
        proxyClient proxy = new proxyClient(new Person());
        String result = proxy.getOneFromDB();
        if (result.equals("No Internet Access")) {
            sendToast(result);
            return false;
        }
        return true;
    }

    /**
     * Show the toast.
     *
     * @param s
     */
    private void sendToast(String s) {
        Toast.makeText(ChooseActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Inner class: Spinner Adapter
     */
    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        String fontPath = "roboto/Roboto-Light.ttf";
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(tf);
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(tf);
            return view;
        }
    }

}
