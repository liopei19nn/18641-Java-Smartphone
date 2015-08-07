package ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.person.Person;
import tongyunl.teamupfatdown.R;
import util.DatabaseConnector;
import util.LaunchCamera;

/**
 * This class represents the UI which shows height, weight, goal and photo between past and nowadays
 */
public class Change extends Activity {
    protected static final int REQCAMERA = 11;
    private static final String TAG = "MainActivity";
    private final int IMAGE_MAX_SIZE = 1024;
    private static String path1 = null;
    private static String path2 = null;
    private String path = null;
    private TextView titleTextView;
    private TextView dateTextView1;
    private TextView weightTextView1;
    private TextView heightTextView1;
    private TextView goalTextView1;
    private TextView dateTextView2;
    private TextView weightTextView2;
    private TextView heightTextView2;
    private TextView goalTextView2;
    private String formattedDate;
    private View buttonImageView1;
    private View buttonImageView2;
    private ImageView picture1ImageView;
    private ImageView picture2ImageView;
    private ContentResolver mContentResolver;
    private int flagleftpicture = 0;
    private int flagrightpicture = 0;
    private DatabaseConnector databaseConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.ui_change);
        mContentResolver = getContentResolver();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        /*date format update*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c.getTime());
        super.onCreate(savedInstanceState);
        titleTextView = (TextView) findViewById(R.id.change_changesloganTextView);
        dateTextView1 = (TextView) findViewById(R.id.change_dateleft);
        dateTextView2 = (TextView) findViewById(R.id.change_dateright);
        weightTextView1 = (TextView) findViewById(R.id.change_weightleft);
        weightTextView2 = (TextView) findViewById(R.id.change_weightright);
        heightTextView1 = (TextView) findViewById(R.id.change_heightleft);
        heightTextView2 = (TextView) findViewById(R.id.change_heightright);
        goalTextView1 = (TextView) findViewById(R.id.change_goalleft);
        goalTextView2 = (TextView) findViewById(R.id.change_goalright);
        buttonImageView1 = findViewById(R.id.change_leftbutton);
        picture1ImageView = (ImageView) findViewById(R.id.change_imageView1);
        picture2ImageView = (ImageView) findViewById(R.id.change_imageView2);

        String fontPath0 = "roboto/Roboto-ThinItalic.ttf";
        String fontPath1 = "roboto/Roboto-LightItalic.ttf";

        Typeface tf0 = Typeface.createFromAsset(getAssets(), fontPath0);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);

        titleTextView.setTypeface(tf0);
        dateTextView1.setTypeface(tf1);
        dateTextView2.setTypeface(tf1);
        weightTextView1.setTypeface(tf1);
        weightTextView2.setTypeface(tf1);
        heightTextView1.setTypeface(tf1);
        heightTextView2.setTypeface(tf1);
        goalTextView1.setTypeface(tf1);
        goalTextView2.setTypeface(tf1);

        databaseConnector = new DatabaseConnector(Change.this);

        setleftInfo();
        setRightinfo();

        /*
         * Get the path from database if exits and then display the picture
         */
        if (!path1.equals("NULL")) {
            Log.i("123", path1);
            Bitmap bitmap = getBitmap(path1);
            picture1ImageView.setImageBitmap(bitmap);
        }

        if (!path2.equals("NULL")) {
            Log.i("123", path2);
            Bitmap bitmap = getBitmap(path2);
            picture1ImageView.setImageBitmap(bitmap);
        }

        /*
         * Button clicked to update Info
         */
        buttonImageView1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                flagleftpicture = 1;
                Intent intent = new Intent(Change.this,
                        LaunchCamera.class);
                startActivityForResult(intent, REQCAMERA);
            }
        });
        buttonImageView2 = findViewById(R.id.change_rightbutton);
        buttonImageView2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                flagrightpicture = 1;
                Intent intent = new Intent(Change.this,
                        LaunchCamera.class);
                startActivityForResult(intent, REQCAMERA);
            }
        });
    }

    /**
     * Set the left text information
     */
    public void setleftInfo() {
        Cursor result1 = databaseConnector.getOnechange(Person.get(this).getUserName());
        result1.moveToFirst();
        int date1Index = result1.getColumnIndex("date1");
        int weight1Index = result1.getColumnIndex("weight1");
        int height1Index = result1.getColumnIndex("height1");
        int goal1Index = result1.getColumnIndex("goal1");
        try {
            dateTextView1.setText(result1.getString(date1Index));
        } catch (RuntimeException runE) {
            databaseConnector.insertchange(Person.get(this).getUserName(), "NULL", 0, 0, 0, "NULL", 0, 0, 0);
            result1 = databaseConnector.getOnechange(Person.get(this).getUserName());
            result1.moveToFirst();
            dateTextView1.setText(result1.getString(date1Index));
        }
        weightTextView1.setText(result1.getString(weight1Index) + " kg");
        heightTextView1.setText(result1.getString(height1Index) + " cm");
        goalTextView1.setText(result1.getString(goal1Index) + " kcal");
        result1.close();
        databaseConnector.close();
        Cursor result1_2 = databaseConnector.getOnechange2(Person.get(this).getUserName());
        if (result1_2 == null) {
            databaseConnector.insertchange2(Person.get(this).getUserName(), "NULL", "NULL");
        }
        result1_2.moveToFirst();
        int path1Index = result1_2.getColumnIndex("path1");
        try {
            path1 = result1_2.getString(path1Index);
        } catch (RuntimeException runE) {
            databaseConnector.insertchange2(Person.get(this).getUserName(), "NULL", "NULL");
            result1_2 = databaseConnector.getOnechange2(Person.get(this).getUserName());
            result1_2.moveToFirst();
            path1 = result1_2.getString(path1Index);
        }
        result1_2.close();
        databaseConnector.close();
    }

    /**
     * Set the right text information
     */
    public void setRightinfo() {
        Cursor result1 = databaseConnector.getOnechange(Person.get(this).getUserName());
        result1.moveToFirst();
        int date2Index = result1.getColumnIndex("date2");
        int weight2Index = result1.getColumnIndex("weight2");
        int height2Index = result1.getColumnIndex("height2");
        int goal2Index = result1.getColumnIndex("goal2");
        dateTextView2.setText(result1.getString(date2Index));
        weightTextView2.setText(result1.getString(weight2Index) + " kg");
        heightTextView2.setText(result1.getString(height2Index) + " cm");
        goalTextView2.setText(result1.getString(goal2Index) + " kcal");
        result1.close();
        databaseConnector.close();
        Cursor result2_2 = databaseConnector.getOnechange2(Person.get(this).getUserName());
        result2_2.moveToFirst();
        int path2Index = result2_2.getColumnIndex("path2");
        path2 = result2_2.getString(path2Index);
        System.out.println("path2************************************" + path2);
        result2_2.close();
        databaseConnector.close();
    }

    /**
     * Update left information
     */
    public void updateleftInfo() {
        dateTextView1.setText(String.valueOf(formattedDate));
        weightTextView1.setText(String.valueOf(Person.get(this).getWeight()) + " kg");
        heightTextView1.setText(String.valueOf(Person.get(this).getHeight()) + " cm");
        goalTextView1.setText(String.valueOf(Person.get(this).getCalGoal()) + " kcal");
    }

    /**
     * Update right information
     */
    public void updaterightInfo() {
        dateTextView2.setText(String.valueOf(formattedDate));
        weightTextView2.setText(String.valueOf(Person.get(this).getWeight()) + " kg");
        heightTextView2.setText(String.valueOf(Person.get(this).getHeight()) + " cm");
        goalTextView2.setText(String.valueOf(Person.get(this).getCalGoal()) + " kcal");
    }

    @Override
    public void onResume() {
        if (path1 != null) {//display left picture
            Log.i("123", path1);
            Bitmap bitmap = getBitmap(path1);
            picture1ImageView.setImageBitmap(bitmap);
        }
        if (path2 != null) {//display right picture
            Log.i("123", path2);
            Bitmap bitmap = getBitmap(path2);
            picture2ImageView.setImageBitmap(bitmap);

        }
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQCAMERA:
                if (data != null) {
                    path = data.getStringExtra(LaunchCamera.IMAGE_PATH);
                    Log.i("123", path);
                    Bitmap bitmap = getBitmap(path);
                    if (flagleftpicture == 1) {
                        databaseConnector.updatechangeLeft(Person.get(this).getUserName(),
                                formattedDate, Person.get(this).getWeight(), Person.get(this).getHeight(),
                                Person.get(this).getCalGoal());
                        databaseConnector.updatechange2_path1(Person.get(this).getUserName(), path);
                        flagleftpicture = 0;
                        picture1ImageView.setImageBitmap(bitmap);
                        path1 = path;
                        updateleftInfo();
                    } else if (flagrightpicture == 1) {
                        databaseConnector.updatechangeRight(Person.get(this).getUserName(), formattedDate,
                                Person.get(this).getWeight(), Person.get(this).getHeight(),
                                Person.get(this).getCalGoal());
                        databaseConnector.updatechange2_path2(Person.get(this).getUserName(), path);
                        flagrightpicture = 0;
                        picture2ImageView.setImageBitmap(bitmap);
                        path2 = path;
                        updaterightInfo();
                    }
                } else {
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }

    /**
     * Get the imageUri from the path
     *
     * @param path
     * @return
     */
    private Uri getImageUri(String path) {
        return Uri.fromFile(new File(path));
    }

    /**
     * Use path to create the Bitmap, used for displaying the picture
     *
     * @param path
     * @return
     */
    private Bitmap getBitmap(String path) {

        Uri uri = getImageUri(path);
        InputStream in = null;
        try {
            in = mContentResolver.openInputStream(uri);

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(in, null, o);
            in.close();

            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(
                        2,
                        (int) Math.round(Math.log(IMAGE_MAX_SIZE
                                / (double) Math.max(o.outHeight, o.outWidth))
                                / Math.log(0.5)));
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            in = mContentResolver.openInputStream(uri);
            Bitmap b = BitmapFactory.decodeStream(in, null, o2);
            in.close();
            return b;
        } catch (FileNotFoundException e) {
            Log.e(TAG, "file " + path + " not found");
        } catch (IOException e) {
            Log.e(TAG, "file " + path + " not found");
        }
        return null;
    }
}