package ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import model.person.Friend;
import model.person.Person;
import tongyunl.teamupfatdown.R;

/**
 * This class represents the user interface which shows the information of
 * user and rival.
 */
public class Duel extends Activity {
    private TextView usernameTextView;
    private TextView userNicknameTextView;
    private TextView userDoneTextView;
    private TextView userGoalTextView;
    private TextView rivalTextView;
    private TextView rivalNicknameTextView;
    private TextView rivalDoneTextView;
    private TextView rivalGoalTextView;
    private TextView vsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_duel);
        TextView usernameLabel = (TextView) findViewById(R.id.duel_nameLabelTextView1);
        TextView userNicknameLabel = (TextView) findViewById(R.id.duel_nicknameLabelTextView1);
        TextView userdoneLabel = (TextView) findViewById(R.id.duel_DoneLabelTextView1);
        TextView usergoalLabel = (TextView) findViewById(R.id.duel_GoalLabelTextView1);
        TextView rivalnameLabel = (TextView) findViewById(R.id.duel_nameLabelTextView2);
        TextView rivalNicknameLabel = (TextView) findViewById(R.id.duel_nicknameLabelTextView2);
        TextView rivalDoneLabel = (TextView) findViewById(R.id.duel_DoneLabelTextView2);
        TextView rivalGoalLabel = (TextView) findViewById(R.id.duel_GoalLabelTextView2);
        usernameTextView = (TextView) findViewById(R.id.duel_nameTextView1);
        userNicknameTextView = (TextView) findViewById(R.id.duel_nicknameTextView1);
        userDoneTextView = (TextView) findViewById(R.id.duel_DoneTextView1);
        userGoalTextView = (TextView) findViewById(R.id.duel_GoalTextView1);
        rivalTextView = (TextView) findViewById(R.id.duel_nameTextView2);
        rivalNicknameTextView = (TextView) findViewById(R.id.duel_nicknameTextView2);
        rivalDoneTextView = (TextView) findViewById(R.id.duel_DoneTextView2);
        rivalGoalTextView = (TextView) findViewById(R.id.duel_GoalTextView2);
        vsTextView = (TextView) findViewById(R.id.VSLabelTextView);

        String fontPath1 = "roboto/Roboto-Light.ttf";
        String fontPath2 = "roboto/Roboto-LightItalic.ttf";
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
        usernameLabel.setTypeface(tf2);
        userdoneLabel.setTypeface(tf2);
        usergoalLabel.setTypeface(tf2);
        userNicknameLabel.setTypeface(tf2);
        rivalnameLabel.setTypeface(tf2);
        rivalDoneLabel.setTypeface(tf2);
        rivalGoalLabel.setTypeface(tf2);
        rivalNicknameLabel.setTypeface(tf2);
        usernameTextView.setTypeface(tf1);
        userNicknameTextView.setTypeface(tf1);
        userDoneTextView.setTypeface(tf1);
        userGoalTextView.setTypeface(tf1);
        rivalTextView.setTypeface(tf1);
        rivalNicknameTextView.setTypeface(tf1);
        rivalDoneTextView.setTypeface(tf1);
        rivalGoalTextView.setTypeface(tf1);

        String fontPath = "great-vibes/GreatVibes-Regular.otf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        vsTextView.setTypeface(tf);
        update();
    }

    /**
     * Update the information of user and rival.
     */
    private void update() {
        Person p = Person.get(this);
        usernameTextView.setText(p.getUserName());
        userNicknameTextView.setText(p.getNickName());
        userDoneTextView.setText(String.valueOf(p.getCalConsumption()));
        userGoalTextView.setText(String.valueOf(p.getCalGoal()));
        ArrayList<Friend> friendList = p.getTeam();
        for (Friend f : friendList) {
            if (f.getUserName().equals(p.getRivalName())) {
                p.setRival(f);
            }
        }
        Friend rival = p.getRival();
        if (rival != null) {
            rivalTextView.setText(p.getRival().getUserName());
            rivalNicknameTextView.setText(p.getRival().getNickName());
            rivalDoneTextView.setText(String.valueOf(p.getRival().getCalConsumption()));
            rivalGoalTextView.setText(String.valueOf(p.getRival().getCalGoal()));
        }
    }
}
