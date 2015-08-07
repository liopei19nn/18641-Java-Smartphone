package ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import model.person.Person;
import tongyunl.teamupfatdown.R;
import service.AudioService;

/**
 * This class represents the UI which is the main page of the application.
 */
public class Mainpage extends Activity{
    private static final String serviceToast = "<---- Stop |Swipe Rock&Roll| Start ---->";
    private TextView dateTextView;
    private TextView goalTextView;
    private TextView todoTextView;
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
        setContentView(R.layout.ui_main_page);
        View view = findViewById(R.id.mainpage_ScorllView);

        view.setOnTouchListener(new CustomTouchListener() {

        });
        // send a toast to remind music service
        sendToast(serviceToast);
        dateTextView = (TextView) findViewById(R.id.mainpage_dateTextView);
        goalTextView = (TextView) findViewById(R.id.mainpage_energyTextView);
        todoTextView = (TextView) findViewById(R.id.mainpage_energytogoTextView);
        update();
    }

    @Override
    public void onResume() {
        super.onResume();
        // send a toast to remind music service
        sendToast(serviceToast);
        update();
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("EXIT");
        dialog.setMessage("Are you sure to exit?");
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
                        finish();
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


    /**
     * Update the view of this page
     */
    private void update() {
        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        String fontPath1 = "roboto/Roboto-MediumItalic.ttf";
        String fontPath2 = "roboto/Roboto-LightItalic.ttf";

        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);

        dateTextView.setTypeface(tf0);
        goalTextView.setTypeface(tf1);
        todoTextView.setTypeface(tf2);

        String calendar = Calendar.getInstance().getTime().toString();
        String weekday = calendar.split(" ")[0];
        String date = calendar.split(" ")[1] + " " + calendar.split(" ")[2];
        dateTextView.setText(date + "\n" + weekday);
        int goal = Person.get(this).getCalGoal();
        String s = String.valueOf(goal);
        goalTextView.setText(s);
        int calories = Person.get(this).getCalConsumption();
        if (goal == 0) {
            todoTextView.setText("Please set up your goal.");
        } else {
            double percent = ((double) (goal - calories) / goal) * 100;
            if (percent <= 0) {
                todoTextView.setText("Goal Achieved!");
            } else if (percent > 100) {
                todoTextView.setText("100% to Go");
            } else {
                String text = (int) percent + "% to Go";
                todoTextView.setText(text);
            }
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
        if (toast == null){
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
    private class CustomTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x0 = event.getX();
                    y0 = event.getY();

                    actionFlag = true;
                    break;
                case MotionEvent.ACTION_UP:
                    if (actionFlag == true){
                        x1 = event.getX();
                        y1 = event.getY();

                        actionFlag = false;

                        if (x0 - x1 > horizontal_limit){
                            sendToast(serviceToast);
                            stopService(new Intent(Mainpage.this, AudioService.class));
                        } else if (x1 - x0 > horizontal_limit){
                            sendToast(serviceToast);
                            startService(new Intent(Mainpage.this, AudioService.class));

                        } else{
                            Intent intent = new Intent(Mainpage.this, Mainmenu.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            //conceal the toast when you enter other activity
                            toast.cancel();
                            break;
                        }
                    }
                    break;

            }
            return false;
        }
    }
}
