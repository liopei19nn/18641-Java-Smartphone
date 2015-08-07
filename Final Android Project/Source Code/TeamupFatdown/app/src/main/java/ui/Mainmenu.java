package ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import communication.proxyClient;
import model.person.Person;
import tongyunl.teamupfatdown.R;
import service.AudioService;

/**
 * This class represents the main menu user interface.
 */
public class Mainmenu extends Activity {
    private static final String serviceToast = "<---- Stop |Swipe Rock&Roll| Start ---->";
    private TextView titleView;
    private Button userButton;
    private Button changeButton;
    private Button activityButton;
    private Button nutritionButton;
    private Button teamButton;
    private Button duelButton;
    private Toast toast;
    private float x0 = -1;
    private float x1 = -1;
    private float y0 = -1;
    private float y1 = -1;
    private float horizontal_limit = 400;
    private boolean actionFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_menu);
        titleView = (TextView) findViewById(R.id.menupage_titleTextView);
        userButton = (Button) findViewById(R.id.menupage_userButton);
        changeButton = (Button) findViewById(R.id.menupage_changeButton);
        activityButton = (Button) findViewById(R.id.menupage_activityButton);
        nutritionButton = (Button) findViewById(R.id.menupage_nutritionButton);
        teamButton = (Button) findViewById(R.id.menupage_teamButton);
        duelButton = (Button) findViewById(R.id.menupage_duelButton);

        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        String fontPath1 = "droid-sans/DroidSans.ttf";

        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);

        titleView.setTypeface(tf0);
        userButton.setTypeface(tf1);
        changeButton.setTypeface(tf1);
        activityButton.setTypeface(tf1);
        nutritionButton.setTypeface(tf1);
        teamButton.setTypeface(tf1);
        duelButton.setTypeface(tf1);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(Mainmenu.this, User.class);
                startActivity(user);
                toast.cancel();//lip: conceal the toast when you enter other activity
            }
        });
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(Mainmenu.this, ChooseActivity.class);
                startActivity(activity);
                toast.cancel();//lip: conceal the toast when you enter other activity
            }
        });
        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nutrition = new Intent(Mainmenu.this, ChooseNutrition.class);
                startActivity(nutrition);
                toast.cancel();//lip: conceal the toast when you enter other activity
            }
        });
        duelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFriend()) {
                    Intent duel = new Intent(Mainmenu.this, Duel.class);
                    startActivity(duel);
                    toast.cancel();//lip: conceal the toast when you enter other activity
                }
            }
        });
        teamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFriend()) {
                    Intent team = new Intent(Mainmenu.this, Team.class);
                    startActivity(team);
                    toast.cancel();//lip: conceal the toast when you enter other activity
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(Mainmenu.this, Change.class);
                startActivity(change);
                toast.cancel();//lip: conceal the toast when you enter other activity
            }
        });


        View v = findViewById(R.id.ScorllView);
        v.setOnTouchListener(new CustomTouchListener() {
        });

        // send a toast to remind music service
        sendToast(serviceToast);
    }

    @Override
    public void onResume() {
        super.onResume();
        // send a toast to remind music service
        sendToast(serviceToast);
    }

    /**
     * Get the friends of user
     *
     * @return
     */
    private boolean getFriend() {
        Person p = Person.get(this);
        proxyClient proxy = new proxyClient(p);
        String result = proxy.getAllFromDB();
        if (result.equals("No Internet Access")) {
            sendToast(result);
            return false;
        } else {
            return true;
        }
    }


    /**
     * Send the toast.
     *
     * @param s
     */
    private void sendToast(String s) {
        // if there is a current toast, set new text on it
        // or rebuild a new one
        if (toast == null) {
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        }
        toast.setText(s);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    // if you touch the screen, and move left or right for a
    // distance long enough, it would start or stop the music service

    /**
     * This part is a MotionEvent Handler for
     * Custom Gesture for App
     */
    private class CustomTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x0 = event.getX();
                    y0 = event.getY();
                    actionFlag = true;
                    break;
                case MotionEvent.ACTION_UP:
                    if (actionFlag == true) {
                        x1 = event.getX();
                        y1 = event.getY();
                        actionFlag = false;

                        if (x0 - x1 > horizontal_limit) {
                            sendToast(serviceToast);
                            stopService(new Intent(Mainmenu.this, AudioService.class));
                        } else if (x1 - x0 > horizontal_limit) {
                            sendToast(serviceToast);
                            startService(new Intent(Mainmenu.this, AudioService.class));

                        } else {

                        }
                    }
                    break;
            }
            return false;
        }
    } // End of gesture
}
