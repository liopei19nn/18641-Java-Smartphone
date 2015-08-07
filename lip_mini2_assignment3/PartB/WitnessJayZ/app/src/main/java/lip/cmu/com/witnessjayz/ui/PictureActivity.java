package lip.cmu.com.witnessjayz.ui;
/*
*
* Assignment 3 Part B
* Name: Li Pei
* Andrew ID : lip
*
* */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import lip.cmu.com.witnessjayz.R;

public class PictureActivity extends Activity {


    private ImageSwitcher switcher; // image switcher
    private static int count = 1; // save current photo id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_page);


        Toast.makeText(PictureActivity.this, "Touch Screen to View Images!", Toast.LENGTH_SHORT).show();

        switcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                return new ImageView(PictureActivity.this);
            }
        });
        switcher.setInAnimation(AnimationUtils.loadAnimation(PictureActivity.this, android.R.anim.slide_in_left));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(PictureActivity.this, android.R.anim.slide_out_right));

        switcher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showCurrentImage();
            }
        });

        // show one page at set view
        showCurrentImage();

    }

    // A cycle for showing photos
    private void showCurrentImage() {

        switch (count) {
            case 1:
                switcher.setImageResource(R.drawable.image1);
                break;
            case 2:
                switcher.setImageResource(R.drawable.image2);
                break;
            case 3:
                switcher.setImageResource(R.drawable.image3);
                break;
            case 4:
                switcher.setImageResource(R.drawable.image4);
                break;
            case 5:
                switcher.setImageResource(R.drawable.image5);
                count = 1;
                return;
            default:
                break;
        }
        count++;

    }
}