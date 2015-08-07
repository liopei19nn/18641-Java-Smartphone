package lip.cmu.com.smslocation.ui;
/*
* Assignment 4
*
* Name : Li Pei
*
* Andrew ID : lip
* */
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import lip.cmu.com.smslocation.R;
import lip.cmu.com.smslocation.exception.ExceptionHandler;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button button; // send button

    // Text view for longitude, latitude and altitude
    private TextView longitude_TV,latitude_TV,altitude_TV;

    // location manager and location
    private LocationManager locationManager;
    private Location location;

    private String msg; // send message through SMS

    private static final String TELENUM  = "4124194227"; // hardcode phone number


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set button and button listener
        button = (Button)findViewById(R.id.mainpage_button);
        button.setOnClickListener(MainActivity.this);


        // set text view for location
        longitude_TV = (TextView)findViewById(R.id.longtitude_TextView);
        latitude_TV = (TextView)findViewById(R.id.latitute_TextView);
        altitude_TV = (TextView)findViewById(R.id.altitude_TextView);

        // set location manager and location
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);


        // update once to show the current location
        updateLocation(location);


        // set location manager listener for updata position
        // in real time
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }


            // this is used when the location is changed, update the display of location
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                updateLocation(locationManager.getLastKnownLocation(provider));
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        SmsManager smsManager = SmsManager.getDefault();
        //if location is null, display a fail toast
        // or send the location to the TELNUM and display success toast

        if (location == null){
            Toast.makeText(MainActivity.this, "No Location Service!", Toast.LENGTH_LONG).show();
        } else{
            smsManager.sendTextMessage(TELENUM,null,msg,null,null);
            Toast.makeText(MainActivity.this, "Sent to " + TELENUM, Toast.LENGTH_LONG).show();
        }
    }


    private void updateLocation(Location loc) {

        // if location is null, get the location
        // build the textview and message string

        // or log the exception
        if(loc != null){
            StringBuilder longitude_sb = new StringBuilder();
            StringBuilder latitude_sb = new StringBuilder();
            StringBuilder altitude_sb = new StringBuilder();
            longitude_sb.append(location.getLongitude());
            latitude_sb.append(location.getLatitude());
            altitude_sb.append(location.getAltitude());
            longitude_TV.setText(longitude_sb.toString());
            latitude_TV.setText(latitude_sb.toString());
            altitude_TV.setText(altitude_sb.toString());
            msg = longitude_sb.toString() + "||" +latitude_sb.toString()+ "||" + altitude_sb.toString();
        }else{
            new ExceptionHandler("No Location Service!");
            Toast.makeText(this, "No Location Service!", Toast.LENGTH_SHORT).show();
        }

    }


}
