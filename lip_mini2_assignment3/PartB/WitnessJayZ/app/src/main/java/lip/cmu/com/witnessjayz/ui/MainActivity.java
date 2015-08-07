package lip.cmu.com.witnessjayz.ui;
/*
*
* Assignment 3 Part B
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import lip.cmu.com.witnessjayz.R;


public class MainActivity extends Activity implements View.OnClickListener{

    // Button for choose activity
    private Button button_music,button_video,button_pics,button_maillist;
    // intent to jump
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init TextView
        TextView mainpage_Link = (TextView) findViewById(R.id.mainpage_mainpage_Textview);
        TextView twitter_Link = (TextView)findViewById(R.id.mainpage_twitter_Textview);
        TextView fb_Link = (TextView)findViewById(R.id.mainpage_facebook_Textview);


        // init link
        mainpage_Link.setMovementMethod(LinkMovementMethod.getInstance());
        twitter_Link.setMovementMethod(LinkMovementMethod.getInstance());
        fb_Link.setMovementMethod(LinkMovementMethod.getInstance());


        // init button and listener
        button_music = (Button)findViewById(R.id.mainpage_music_button);
        button_video = (Button)findViewById(R.id.mainpage_video_button);
        button_pics = (Button)findViewById(R.id.mainpage_pics_button);
        button_maillist = (Button)findViewById(R.id.mainpage_mail_button);

        button_music.setOnClickListener(this);
        button_video.setOnClickListener(this);
        button_pics.setOnClickListener(this);
        button_maillist.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            // music button
            case R.id.mainpage_music_button:
                i = new Intent(this, MusicActivity.class);
                break;

            // video button
            case R.id.mainpage_video_button :
                i = new Intent(this, VideoActivity.class);
                break;

            // pics button
            case R.id.mainpage_pics_button :
                i = new Intent(this,PictureActivity.class);
                break;

            // mail button
            case R.id.mainpage_mail_button :
                i = new Intent(this,MailListActivity.class);
                break;

            default:
                return;
        }

        // start activity
        startActivity(i);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
