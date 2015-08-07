package lip.cmu.com.witnessjayz.ui;
/*
*
* Assignment 3 Part B
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import lip.cmu.com.witnessjayz.R;

// Thread like music player activity
// three music track but only
// 1 media player to sovle conflict issue
public class MusicActivity extends Activity{

    // media player
    private MediaPlayer mediaPlayer;

    // start button group
    private Button[] startButton;

    // stop button group
    private Button[] pauseButton;

    // restart button group
    private Button[] restartButton;

    // save the play back position
    private int[] playbackPosition;

    // save the current play track number
    private int currentplaytracknumber;

    // button id group
    private static final int[] startbuttonid = {R.id.start_btn1,R.id.start_btn2,R.id.start_btn3};
    private static final int[] pausebuttonid = {R.id.pause_btn1,R.id.pause_btn2,R.id.pause_btn3};
    private static final int[] restartbuttonid = {R.id.restart_btn1,R.id.restart_btn2,R.id.restart_btn3};
    private static final int[] rawsourceid = {R.raw.sample1_hardknock,R.raw.sample2_heartofcity,R.raw.sample3_99problems};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_page);


        // button group initialized
        startButton = new Button[3];
        pauseButton = new Button[3];
        restartButton = new Button[3];
        playbackPosition = new int[3];

        for (int j = 0; j < pauseButton.length;j++){
            playbackPosition[j] = 0;
            pauseButton[j] = (Button) findViewById(pausebuttonid[j]);
            pauseButton[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pauseAction(v.getId());
                }
            });
        }

        for (int k = 0; k < restartButton.length;k++){
            restartButton[k] = (Button) findViewById(restartbuttonid[k]);
            restartButton[k].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restartAction(v.getId());
                }
            });
        }


        for (int i = 0; i < startButton.length; i++){
            startButton[i] = (Button)findViewById(startbuttonid[i]);
            startButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{

                        playLocalAudio_UsingDescriptor(v.getId());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    /*
    *
    * restartAction()
    *
    * This method works like :
    *
    * Each restart action are set for each track
    *
    * 1. If you press the button from the track not playing, no effect
    * 2. If you press the button after pause, it will resume the corresponding track
    * 3. If no song is playing and you press the restart button, the song will play
    *       from start
    *
    * */
    public void restartAction(int id){

        // if no song is playing, play this id track from start
        if (mediaPlayer == null){
            playLocalAudio_UsingDescriptor(id);
            return;
        }

        // if some song is playing, alert you to pause it first
        if (mediaPlayer.isPlaying()){
            Toast.makeText(MusicActivity.this, "Pause First!", Toast.LENGTH_SHORT).show();
            return;
        }



        // restart a song
        AssetFileDescriptor fileDesc;
        switch (id){
            case R.id.restart_btn1:
                try{
                    Toast.makeText(MusicActivity.this, "Hard Knock Life (From Greatest Hits)", Toast.LENGTH_SHORT).show();

                    // set the current play id
                    currentplaytracknumber = R.id.start_btn1;

                    // get the file source
                    fileDesc = getResources().openRawResourceFd(rawsourceid[0]);
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc.getStartOffset(), fileDesc.getLength());
                    fileDesc.close();
                    mediaPlayer.prepare();

                    // get the start position
                    mediaPlayer.seekTo(playbackPosition[0]);

                    // start
                    mediaPlayer.start();

                } catch (Exception e){
                    Log.e(null,"Restart Error");
                }
                break;

            case R.id.restart_btn2 :
                try{
                    Toast.makeText(MusicActivity.this, "Heart Of City (From Blueprint)", Toast.LENGTH_SHORT).show();

                    // set the current play id
                    currentplaytracknumber = R.id.start_btn2;

                    // get the file source
                    fileDesc = getResources().openRawResourceFd(rawsourceid[1]);
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc.getStartOffset(), fileDesc.getLength());
                    fileDesc.close();
                    mediaPlayer.prepare();


                    // get the start position
                    mediaPlayer.seekTo(playbackPosition[1]);

                    // start player
                    mediaPlayer.start();
                } catch (Exception e){
                    Log.e(null,"Restart Error");
                }

                break;



            case R.id.restart_btn3 :

                try{
                    Toast.makeText(MusicActivity.this, "99 Problems (From Black Album)", Toast.LENGTH_SHORT).show();

                    // set the current play id
                    currentplaytracknumber = R.id.start_btn3;

                    // get the file source
                    fileDesc = getResources().openRawResourceFd(rawsourceid[2]);
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc.getStartOffset(), fileDesc.getLength());
                    fileDesc.close();
                    mediaPlayer.prepare();


                    // get the start position
                    mediaPlayer.seekTo(playbackPosition[2]);


                    // start player
                    mediaPlayer.start();
                } catch (Exception e){
                    Log.e(null,"Restart Error");
                }

                break;

            default:
                return;
        }

    }

    /*
    *
    * pauseAction()
    *
    *
    * You could only stop corresponding track
    *
    * */
    public void pauseAction(int id){
        if (mediaPlayer == null){
            Toast.makeText(MusicActivity.this, "Start A Song First", Toast.LENGTH_SHORT).show();
            return;
        }

        // save the corresponding play position and pause the media player

        switch (id){
            case R.id.pause_btn1 :
                if (currentplaytracknumber == R.id.start_btn1){
                    playbackPosition[0] = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                } else{
                    Toast.makeText(MusicActivity.this, "This Song is Not Playing", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pause_btn2 :
                if (currentplaytracknumber == R.id.start_btn2){
                    playbackPosition[1] = mediaPlayer.getCurrentPosition();

                    mediaPlayer.pause();
                }else{
                    Toast.makeText(MusicActivity.this, "This Song is Not Playing", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.pause_btn3 :
                if (currentplaytracknumber == R.id.start_btn3){
                    playbackPosition[2] = mediaPlayer.getCurrentPosition();

                    mediaPlayer.pause();
                }else{
                    Toast.makeText(MusicActivity.this, "This Song is Not Playing", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return;
        }
    }




    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        killMediaPlayer();
    }


    // stop the player and release
    private void killMediaPlayer()
    {
        if(mediaPlayer!=null)
        {
            try
            {
                mediaPlayer.release();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    // play the audio with file descriptor
    private void playLocalAudio_UsingDescriptor(int id)
    {
        // if there is a song playing
        // stop it
        if (mediaPlayer != null){
            killMediaPlayer();
        }


        // start a song when you touch corresponding
        // restart


        // Note when there is no song play, you could
        // use it to start a play with pressing restart button
        AssetFileDescriptor fileDesc;
        switch (id){
            case R.id.start_btn1:
            case R.id.restart_btn1:
                currentplaytracknumber = R.id.start_btn1;
                fileDesc = getResources().openRawResourceFd(rawsourceid[0]);
                Toast.makeText(MusicActivity.this, "Hard Knock Life (From Greatest Hits)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.start_btn2:
            case R.id.restart_btn2:
                currentplaytracknumber = R.id.start_btn2;
                fileDesc = getResources().openRawResourceFd(rawsourceid[1]);
                Toast.makeText(MusicActivity.this, "Heart Of City (From Blueprint)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.start_btn3:
            case R.id.restart_btn3:
                currentplaytracknumber = R.id.start_btn3;
                fileDesc = getResources().openRawResourceFd(rawsourceid[2]);
                Toast.makeText(MusicActivity.this, "99 Problems (From Black Album)", Toast.LENGTH_SHORT).show();
                break;
            default:
                fileDesc = null;
        }

        if (fileDesc != null)
        {
            try{
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc.getStartOffset(), fileDesc.getLength());
                fileDesc.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e){
                Log.e(null,"Restart Error");
            }
        }
    }






}

