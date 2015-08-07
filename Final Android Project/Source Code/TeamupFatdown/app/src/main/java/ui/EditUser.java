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
import model.person.Person;
import tongyunl.teamupfatdown.R;

/**
 * This class represents the user interface in which user could edit
 * personal information.
 */
public class EditUser extends Activity {
    private EditText nameEditText;
    private EditText ageEditText;
    private Spinner genderSpinner;
    private EditText weightEditText;
    private EditText heightEditText;
    private EditText goalEditText;
    private Button saveButton;
    private String nickname;
    private String age;
    private String gender;
    private String weight;
    private String height;
    private String goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_edituser);
        TextView nameTextView = (TextView) findViewById(R.id.EditUser_nickNameTextView);
        TextView ageTextView = (TextView) findViewById(R.id.EditUser_ageTextView);
        TextView genderTextView = (TextView) findViewById(R.id.EditUser_genderTextView);
        TextView weightTextView = (TextView) findViewById(R.id.EditUser_weightTextView);
        TextView heightTextView = (TextView) findViewById(R.id.EditUser_heightTextView);
        TextView goalTextView = (TextView) findViewById(R.id.EditUser_goalTextView);
        nameEditText = (EditText) findViewById(R.id.edituser_nickNameEditText);
        ageEditText = (EditText) findViewById(R.id.edituser_ageEditText);
        genderSpinner = (Spinner) findViewById(R.id.edituser_genderSpinner);
        weightEditText = (EditText) findViewById(R.id.edituser_weightEditText);
        heightEditText = (EditText) findViewById(R.id.edituser_heightEditText);
        goalEditText = (EditText) findViewById(R.id.edituser_goalEditText);
        nameEditText.setText(Person.get(this).getNickName());
        ageEditText.setText(String.valueOf(Person.get(this).getAge()));
        weightEditText.setText(String.valueOf(Person.get(this).getWeight()));
        heightEditText.setText(String.valueOf(Person.get(this).getHeight()));
        goalEditText.setText(String.valueOf(Person.get(this).getCalGoal()));
        saveButton = (Button) findViewById(R.id.edituser_saveButton);

        String fontPath1 = "roboto/Roboto-Light.ttf";
        String fontPath2 = "roboto/Roboto-LightItalic.ttf";
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
        nameTextView.setTypeface(tf2);
        ageTextView.setTypeface(tf2);
        genderTextView.setTypeface(tf2);
        weightTextView.setTypeface(tf2);
        heightTextView.setTypeface(tf2);
        goalTextView.setTypeface(tf2);
        nameEditText.setTypeface(tf1);
        ageEditText.setTypeface(tf1);
        weightEditText.setTypeface(tf1);
        heightEditText.setTypeface(tf1);
        goalEditText.setTypeface(tf1);

        MySpinnerAdapter adapter = new MySpinnerAdapter(getApplicationContext(),
                R.layout.ui_dropdownitem,
                Arrays.asList(getResources().getStringArray(R.array.edituser_gender)));
        genderSpinner.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = nameEditText.getText().toString();
                age = ageEditText.getText().toString();
                gender = genderSpinner.getSelectedItem().toString();
                weight = weightEditText.getText().toString();
                height = heightEditText.getText().toString();
                goal = goalEditText.getText().toString();
                System.out.println("EditUser: Gender" + gender);
                if (nickname.length() == 0 || age.length() == 0 || gender.length() == 0
                        || weight.length() == 0 || height.length() == 0 || goal.length() == 0) {
                    sendToast("Missing information");
                } else if (Integer.parseInt(age) < 10 || Integer.parseInt(age) > 100) {
                    sendToast("Age should be in the range of 10 to 100");
                } else if (Integer.parseInt(weight) < 30 || Integer.parseInt(weight) > 200) {
                    sendToast("Weight should be in the range of 30 to 200");
                } else if (Integer.parseInt(height) < 140 || Integer.parseInt(height) > 220) {
                    sendToast("Height should be in the range of 140 to 220");
                } else {
                    AsyncTask<Object, Object, Object> saveUserTask =
                            new AsyncTask<Object, Object, Object>() {
                                @Override
                                protected Object doInBackground(Object... params) {
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Object result) {
                                    if (updateRemote()) {
                                        finish(); // return to the previous Activity
                                    }
                                }
                            };
                    saveUserTask.execute((Object[]) null);
                }
            }
        });
    }

    /**
     * Send the toast.
     *
     * @param s
     */
    private void sendToast(String s) {
        Toast.makeText(EditUser.this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Update the user information locally.
     */
    private void updateLocal() {
        Person.get(this).setNickName(nickname);
        Person.get(this).setAge(Integer.parseInt(age));
        Person.get(this).setGender(gender);
        Person.get(this).setWeight(Integer.parseInt(weight));
        Person.get(this).setHeight(Integer.parseInt(height));
        Person.get(this).setCalGoal(Integer.parseInt(goal));
    }

    /**
     * Send the updated user information to database.
     */
    private boolean updateRemote() {
        proxyClient proxy = new proxyClient(Person.get(this));
        String result = proxy.getOneFromDB();
        if (result.equals("No Internet Access")) {
            sendToast(result);
            return false;
        } else {
            sendToast(result);
            updateLocal();
            proxy.addOneToDB();
            return true;
        }
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
