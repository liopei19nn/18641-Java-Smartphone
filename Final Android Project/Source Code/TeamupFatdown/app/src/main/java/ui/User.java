package ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import communication.proxyClient;
import model.activity.Sports;
import model.nutrition.Nutrition;
import model.person.Person;
import tongyunl.teamupfatdown.R;

/**
 * This class represents the user interface which shows the information of user.
 */
public class User extends Activity {
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView genderTextView;
    private TextView weightTextView;
    private TextView heightTextView;
    private TextView calTextView;
    private TextView goalTextView;
    private TextView emailTextView;
    private Button editButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_user);
        TextView nameLabelTextView = (TextView) findViewById(R.id.user_nameLabelTextView);
        TextView ageLabelTextView = (TextView) findViewById(R.id.user_AgeLabelTextView);
        TextView genderLabelTextView = (TextView) findViewById(R.id.user_GenderLabelTextView);
        TextView weightLabelTextView = (TextView) findViewById(R.id.user_WeightLabelTextView);
        TextView heightLabelTextView = (TextView) findViewById(R.id.user_HeightLabelTextView);
        TextView calLabelTextView = (TextView) findViewById(R.id.user_CalLabelTextView);
        TextView goalLabelTextView = (TextView) findViewById(R.id.user_GoalLabelTextView);
        TextView emailLabelTextView = (TextView) findViewById(R.id.user_EmailLabelTextView);
        resetButton = (Button)findViewById(R.id.user_resetButton);
        editButton = (Button) findViewById(R.id.user_editButton);
        nameTextView = (TextView) findViewById(R.id.user_nameTextView);
        ageTextView = (TextView) findViewById(R.id.user_AgeTextView);
        genderTextView = (TextView) findViewById(R.id.user_GenderTextView);
        weightTextView = (TextView) findViewById(R.id.user_WeightTextView);
        heightTextView = (TextView) findViewById(R.id.user_HeightTextView);
        calTextView = (TextView) findViewById(R.id.user_CalTextView);
        goalTextView = (TextView) findViewById(R.id.user_GoalTextView);
        emailTextView = (TextView) findViewById(R.id.user_EmailTextView);
        String fontPath1 = "roboto/Roboto-LightItalic.ttf";
        String fontPath2 = "roboto/Roboto-Light.ttf";
        String fontPath3 = "droid-sans/DroidSans.ttf";

        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), fontPath3);

        nameLabelTextView.setTypeface(tf1);
        ageLabelTextView.setTypeface(tf1);
        genderLabelTextView.setTypeface(tf1);
        weightLabelTextView.setTypeface(tf1);
        heightLabelTextView.setTypeface(tf1);
        calLabelTextView.setTypeface(tf1);
        goalLabelTextView.setTypeface(tf1);
        emailLabelTextView.setTypeface(tf1);
        nameTextView.setTypeface(tf2);
        ageTextView.setTypeface(tf2);
        genderTextView.setTypeface(tf2);
        weightTextView.setTypeface(tf2);
        heightTextView.setTypeface(tf2);
        calTextView.setTypeface(tf2);
        goalTextView.setTypeface(tf2);
        emailTextView.setTypeface(tf2);
        editButton.setTypeface(tf3);
        resetButton.setTypeface(tf3);

        update();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editUser = new Intent(User.this, EditUser.class);
                startActivity(editUser);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(User.this).create();
                dialog.setTitle("Reset");
                dialog.setMessage("Are you sure to reset your goal?");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AsyncTask<Object, Object, Object> exitTask = new AsyncTask<Object, Object, Object>() {
                            @Override
                            protected Object doInBackground(Object... params) {
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Object result) {
                                if (isConnect()) {
                                    resetCal();
                                    sendToast("Reset successfully");
                                    update();
                                }

                            }
                        };
                        exitTask.execute((Object[]) null);
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editUser = new Intent(User.this, EditUser.class);
                startActivity(editUser);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    /**
     * Update the information of this page.
     */
    private void update() {
        nameTextView.setText(Person.get(this).getNickName());
        ageTextView.setText(String.valueOf(Person.get(this).getAge()));
        genderTextView.setText(Person.get(this).getGender());
        weightTextView.setText(String.valueOf(Person.get(this).getWeight()));
        heightTextView.setText(String.valueOf(Person.get(this).getHeight()));
        calTextView.setText(String.valueOf(Person.get(this).getCalConsumption()));
        goalTextView.setText(String.valueOf(Person.get(this).getCalGoal()));
        emailTextView.setText(Person.get(this).getUserName());
    }

    /**
     * Check if the device connect to the Internet.
     *
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
        Toast.makeText(User.this, s, Toast.LENGTH_SHORT).show();
    }

    private void resetCal() {
        Person p = Person.get(getApplicationContext());
        p.setCalConsumption(0);
        p.setNutritionList(new ArrayList<Nutrition>());
        p.setSportsList(new ArrayList<Sports>());
        proxyClient proxy = new proxyClient(p);
        proxy.addOneToDB();
    }
}
