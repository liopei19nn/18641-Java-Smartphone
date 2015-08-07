package ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import communication.proxyClient;
import model.person.Person;
import tongyunl.teamupfatdown.R;
import util.DatabaseConnector;

/**
 * This class represents the user interface for registration.
 */
public class Registration extends Activity {
    private TextView registration;
    private EditText userNameText;
    private EditText passwordText;
    private EditText reenterText;
    private Button cancelButton;
    private Button submitButton;
    private String userName;
    private String password;
    private String reenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_registration);
        registration = (TextView) findViewById(R.id.registration_TextView1);
        userNameText = (EditText) findViewById(R.id.registration_UserNameText);
        passwordText = (EditText) findViewById(R.id.registration_passWordText);
        reenterText = (EditText) findViewById(R.id.registration_reEnterText);
        cancelButton = (Button) findViewById(R.id.registration_cancelButton);
        submitButton = (Button) findViewById(R.id.registration_submitButton);

        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        String fontPath1 = "roboto/Roboto-LightItalic.ttf";
        String fontPath2 = "droid-sans/DroidSans.ttf";

        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);

        registration.setTypeface(tf0);
        userNameText.setTypeface(tf1);
        passwordText.setTypeface(tf1);
        reenterText.setTypeface(tf1);
        cancelButton.setTypeface(tf2);
        submitButton.setTypeface(tf2);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameText.getText().toString();
                password = passwordText.getText().toString();
                reenter = reenterText.getText().toString();
                if (userName.length() == 0 || password.length() == 0 || reenter.length() == 0) {
                    sendToast("Missing information");
                } else if (!isValidEmail(userName)) {
                    sendToast("The username is not an email address");
                } else if (!password.equals(reenter)) {
                    sendToast("Two passwords are not matched");
                } else if (password.length() != 4 || reenter.length() != 4) {
                    sendToast("Password must be 4 digits");
                } else if (!isExist()) {
                    DatabaseConnector databaseConnector = new DatabaseConnector(Registration.this);
                    databaseConnector.insertchange2(userName, "NULL", "NULL");
                    databaseConnector.insertchange(userName, "NULL", 0, 0, 0, "NULL", 0, 0, 0);
                    AsyncTask<Object, Object, Object> saveUserTask =
                            new AsyncTask<Object, Object, Object>() {
                                @Override
                                protected Object doInBackground(Object... params) {
                                    saveUserInformation();
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Object result) {
                                    sendToast("Sign up successfully");
                                    finish();
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
        Toast.makeText(Registration.this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Save the username and password.
     */
    private void saveUserInformation() {
        Person p = new Person(this.getApplicationContext());
        p.setUserName(userName);
        p.setPassWord(Integer.parseInt(password));
        proxyClient proxy = new proxyClient(p);
        proxy.addOneToDB();
    }

    /**
     * Check if the username has been signed up.
     *
     * @return
     */
    private boolean isExist() {
        Person p = new Person(this.getApplicationContext());
        p.setUserName(userName);
        p.setPassWord(Integer.parseInt(password));
        proxyClient proxyClient = new proxyClient(p);
        String result = proxyClient.getOneFromDB();
        if (result.equals("success")) {
            sendToast("This email address has been signed up.");
            return true;
        } else if (result.equals("Username not exist")) {
            return false;
        } else {
            sendToast(result);
            return true;
        }
    }

    /**
     * Check if the input is valid email address.
     *
     * @param target
     * @return
     */
    private boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
