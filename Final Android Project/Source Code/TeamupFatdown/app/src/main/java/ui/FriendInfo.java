package ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import communication.proxyClient;
import model.person.Friend;
import model.person.Person;
import tongyunl.teamupfatdown.R;

/**
 * This class represents the user interface which could
 * show the information of friend.
 */
public class FriendInfo extends Activity {
    private long rowID;
    private Friend friend;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView genderTextView;
    private TextView weightTextView;
    private TextView heightTextView;
    private TextView goalTextView;
    private TextView calTextView;
    private TextView emailTextView;
    private Button messageButton;
    private Button duelButton;
    private String nickName;
    private int age;
    private String gender;
    private int weight;
    private int height;
    private int calDone;
    private int calGoal;
    private String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_friendinfo);
        Bundle extras = getIntent().getExtras();
        rowID = extras.getLong(Team.ROW_ID);
        nameTextView = (TextView) findViewById(R.id.friend_nameTextView);
        ageTextView = (TextView) findViewById(R.id.friend_AgeTextView);
        genderTextView = (TextView) findViewById(R.id.friend_GenderTextView);
        weightTextView = (TextView) findViewById(R.id.friend_WeightTextView);
        heightTextView = (TextView) findViewById(R.id.friend_HeightTextView);
        calTextView = (TextView) findViewById(R.id.friend_CalTextView);
        goalTextView = (TextView) findViewById(R.id.friend_GoalTextView);
        emailTextView = (TextView) findViewById(R.id.friend_EmailTextView);
        messageButton = (Button) findViewById(R.id.friend_writeMessageButton);
        duelButton = (Button) findViewById(R.id.friend_duelButton);
        TextView nameLabel = (TextView) findViewById(R.id.friend_nameLabelTextView);
        TextView ageLabel = (TextView) findViewById(R.id.friend_AgeLabelTextView);
        TextView genderLabel = (TextView) findViewById(R.id.friend_GenderLabelTextView);
        TextView weightLabel = (TextView) findViewById(R.id.friend_WeightLabelTextView);
        TextView heightLabel = (TextView) findViewById(R.id.friend_HeightLabelTextView);
        TextView calLabel = (TextView) findViewById(R.id.friend_CalLabelTextView);
        TextView goalLabel = (TextView) findViewById(R.id.friend_GoalLabelTextView);
        TextView emailLabel = (TextView) findViewById(R.id.friend_EmailLabelTextView);

        String fontPath1 = "roboto/Roboto-Light.ttf";
        String fontPath2 = "roboto/Roboto-LightItalic.ttf";
        String fontPath3 = "droid-sans/DroidSans.ttf";
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), fontPath3);

        nameTextView.setTypeface(tf1);
        ageTextView.setTypeface(tf1);
        genderTextView.setTypeface(tf1);
        weightTextView.setTypeface(tf1);
        heightTextView.setTypeface(tf1);
        calTextView.setTypeface(tf1);
        goalTextView.setTypeface(tf1);
        emailTextView.setTypeface(tf1);
        nameLabel.setTypeface(tf2);
        ageLabel.setTypeface(tf2);
        genderLabel.setTypeface(tf2);
        weightLabel.setTypeface(tf2);
        heightLabel.setTypeface(tf2);
        calLabel.setTypeface(tf2);
        goalLabel.setTypeface(tf2);
        emailLabel.setTypeface(tf2);
        duelButton.setTypeface(tf3);
        messageButton.setTypeface(tf3);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mailAddress = new String[1];
                mailAddress[0] = emailTextView.getText().toString();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, mailAddress);
                startActivity(emailIntent);
            }
        });
        duelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnect()) {
                    Intent intent = new Intent(FriendInfo.this, Duel.class);
                    setRival();
                    sendToast("Set rival successfully");
                    startActivity(intent);
                }
            }
        });
        ArrayList<Friend> team = Person.get(this).getTeam();
        friend = team.get((int) rowID);
        nickName = friend.getNickName();
        System.out.println("FriendInfo: " + nickName);
        age = friend.getAge();
        gender = friend.getGender();
        weight = friend.getWeight();
        height = friend.getHeight();
        calDone = friend.getCalConsumption();
        calGoal = friend.getCalGoal();
        emailAddress = friend.getUserName();
        nameTextView.setText(nickName);
        ageTextView.setText(String.valueOf(age));
        genderTextView.setText(gender);
        weightTextView.setText(String.valueOf(weight));
        heightTextView.setText(String.valueOf(height));
        calTextView.setText(String.valueOf(calDone));
        goalTextView.setText(String.valueOf(calGoal));
        emailTextView.setText(emailAddress);
    }

    /**
     * Set the rival of user.
     */
    private void setRival() {
        Person p = Person.get(this);
        p.setRival(friend);
        p.setRivalName(emailAddress);
        proxyClient proxy = new proxyClient(p);
        proxy.addOneToDB();
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
     * Send the toast.
     *
     * @param s
     */
    private void sendToast(String s) {
        Toast.makeText(FriendInfo.this, s, Toast.LENGTH_SHORT).show();
    }
}
