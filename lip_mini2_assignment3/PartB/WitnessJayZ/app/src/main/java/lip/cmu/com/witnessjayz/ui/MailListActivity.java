package lip.cmu.com.witnessjayz.ui;
/*
*
* Assignment 3 Part B
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import lip.cmu.com.witnessjayz.R;
import lip.cmu.com.witnessjayz.exception.AutoException;


public class MailListActivity extends Activity {

    // UI input EditText
    private EditText et_nickname, et_emailaddr, et_subject, et_content;


    // Submit button
    private Button submit_button;

    // The Subscriber you send mail to AKA the Li Pei @ CMU
    private static final String[] aEmailList = {"liopei19nn@gmail.com", "lip@andrew.cmu.edu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_page);

        // get Edit Text
        et_nickname = (EditText) findViewById(R.id.mailpage_nickname);
        et_emailaddr = (EditText) findViewById(R.id.mailpage_email);
        et_subject = (EditText) findViewById(R.id.mailpage_subject);
        et_content = (EditText) findViewById(R.id.mailpage_content);

        // Get Save Button
        submit_button = (Button) findViewById(R.id.mailpage_save_button);
        // Set the Listener
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = et_nickname.getText().toString();
                String emailaddr = et_emailaddr.getText().toString();
                String subject = et_subject.getText().toString();
                String content = et_content.getText().toString();

                // if the input is legal , then sent the email to subscribe
                if (checkinputlegal(nickname,emailaddr,subject,content)) {
                    // enable default Gmail activity to send the mail
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From Email : " + emailaddr + " \n  Content: \n" +
                            content + " || \n" + "    from : " + nickname + "--");
                    startActivity(emailIntent);
                }
            }
        });
    }


    private boolean checkinputlegal(String nickname, String emailaddr,String subject,String content) {

        // if empty input
        if (nickname.length() == 0 || emailaddr.length() == 0 || subject.length() == 0|| content.length() == 0){
                // create a new AlertDialog Builder
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MailListActivity.this);

                // set dialog title & message, and provide Button to dismiss
                new AutoException("Empty Input",builder);
                builder.setPositiveButton(R.string.errorButton, null);
                builder.show(); // display the Dialog
            return false;
        }

        // if the input email addr is not legal
        if (emailaddr.indexOf("@") == -1) {
            // create a new AlertDialog Builder
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MailListActivity.this);

            // set dialog title & message, and provide Button to dismiss
            new AutoException("Illegal Email Address",builder);
            builder.setPositiveButton(R.string.errorButton, null);
            builder.show(); // display the Dialog

            return false;
        }

        return true;
    }

}
