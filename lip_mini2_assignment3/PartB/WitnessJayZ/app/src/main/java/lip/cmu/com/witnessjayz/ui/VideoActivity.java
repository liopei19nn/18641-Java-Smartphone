package lip.cmu.com.witnessjayz.ui;
/*
*
* Assignment 3 Part B
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import lip.cmu.com.witnessjayz.R;
import lip.cmu.com.witnessjayz.util.OnFragmentInteractionListener;
import lip.cmu.com.witnessjayz.util.Video_fragment;

// a video view and interact with Video_fragment in it
public class VideoActivity extends Activity implements OnFragmentInteractionListener {

    private VideoView videoView;// get video view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_page);

        // get fragment part
        Video_fragment fragment = new Video_fragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment, "fragment");
        fragmentTransaction.commit();

        // set video view
        videoView = (VideoView)this.findViewById(R.id.videoView);

    }

    public void playVideo(int id){
        MediaController mc = new MediaController(this);

        // play video by the id of fragment button
        if (id == R.id.fragment_right_button){
            Toast.makeText(VideoActivity.this, "Crazy In Love feat. Beyonce", Toast.LENGTH_SHORT).show();
            videoView.setMediaController(mc);
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video2);
            videoView.requestFocus();
            videoView.start();
        }else{
            Toast.makeText(VideoActivity.this, "Empire State of Mind feat. Alicia Keys", Toast.LENGTH_SHORT).show();
            videoView.setMediaController(mc);
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video1);
            videoView.requestFocus();
            videoView.start();
        }

    }

    @Override
    public void onFragmentInteraction(View view) {
        // interact with the fragment button
        playVideo(view.getId());
    }
}
