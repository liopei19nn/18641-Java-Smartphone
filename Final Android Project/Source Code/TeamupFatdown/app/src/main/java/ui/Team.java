package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.person.Friend;
import model.person.Person;
import tongyunl.teamupfatdown.R;

/**
 * This class represents the user interface for showing the list of user's friend.
 */
public class Team extends Activity {
    public static final String ROW_ID = "row_id"; // Intent extra key
    private ListView friendListView; // the ListActivity's ListView
    private FriendAdapter friendAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_team);
        TextView titleView = (TextView) findViewById(R.id.team_titleTextView);
        friendListView = (ListView) findViewById(R.id.team_listview);

        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        titleView.setTypeface(tf0);

        friendListView.setOnItemClickListener(viewMemberListener);
        friendAdapter = new FriendAdapter(this, R.layout.ui_listitem);
        ArrayList<Friend> mFriends = Person.get(this).getTeam();
        for (int i = 0; i < mFriends.size(); i++) {
            friendAdapter.add(mFriends.get(i));
        }
        friendListView.setAdapter(friendAdapter);
    }

    /**
     * Inner class: Friend Adapter
     */
    class FriendAdapter extends ArrayAdapter<Friend> {
        private int mResourceId;

        public FriendAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            this.mResourceId = textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String fontPath1 = "roboto/Roboto-Light.ttf";
            Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
            Friend newFriend = getItem(position);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(mResourceId, null);
            TextView nameText = (TextView) view.findViewById(R.id.name);
            nameText.setTypeface(tf1);
            nameText.setText(newFriend.getUserName());
            return view;
        }
    }

    AdapterView.OnItemClickListener viewMemberListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Intent teamMember =
                    new Intent(Team.this, FriendInfo.class);
            teamMember.putExtra(ROW_ID, arg3);
            startActivity(teamMember);
        }
    };
}
