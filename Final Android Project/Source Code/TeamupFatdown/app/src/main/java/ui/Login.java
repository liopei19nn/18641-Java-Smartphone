package ui;

import android.app.Activity;
import android.content.Intent;
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
import service.AudioService;

/**
 * This class represents the login user interface.
 */
public class Login extends Activity {
    private TextView login;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signinButton;
    private Button signupButton;
    private String username;
    private String password;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);
        login = (TextView) findViewById(R.id.login_TextView1);
        usernameEditText = (EditText) findViewById(R.id.login_userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.login_passwordEditText);
        signinButton = (Button) findViewById(R.id.login_signinbutton);
        signupButton = (Button) findViewById(R.id.login_signupbutton);

        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        String fontPath1 = "roboto/Roboto-LightItalic.ttf";
        String fontPath2 = "droid-sans/DroidSans.ttf";

        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);

        login.setTypeface(tf0);
        usernameEditText.setTypeface(tf1);
        passwordEditText.setTypeface(tf1);
        signinButton.setTypeface(tf2);
        signupButton.setTypeface(tf2);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(Login.this, Registration.class);
                startActivity(t);
            }
        });
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if (username.length() == 0 || password.length() == 0) {
                    sendToast("Missing information");
                } else if (!isValidEmail(username)) {
                    sendToast("Invalid email address");
                } else {
                    AsyncTask<Object, Object, Object> saveUserTask =
                            new AsyncTask<Object, Object, Object>() {
                                @Override
                                protected Object doInBackground(Object... params) {
                                    return null;
                                }
                                @Override
                                protected void onPostExecute(Object result) {
                                    if (getUser()) {
                                        Intent t = new Intent(Login.this, Mainpage.class);
                                        startActivity(t);
                                    }
                                }
                            };
                    saveUserTask.execute((Object[]) null);
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        stopService(new Intent(Login.this, AudioService.class));
    }

    /**
     * Get the user from database.
     * @return
     */
    private boolean getUser() {
        Person p = new Person(getApplicationContext());
        p.setUserName(username);
        p.setPassWord(Integer.parseInt(password));
        proxyClient proxy = new proxyClient(p);
        String result = proxy.getOneFromDB();
        if (result.equals("No Internet Access")) {
            sendToast(result);
            return false;
        } else if (result.equals("Username not exist")) {
            sendToast(result);
            return false;
        } else if (Person.get(this).getPassWord() != Integer.parseInt(password)) {
            sendToast("The password is incorrect");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Send the toast.
     * @param s
     */
    private void sendToast(String s) {
        // if there is a current toast, set new text on it
        // or rebuild a new one
        if (toast == null){
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        }

        toast.setText(s);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
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
