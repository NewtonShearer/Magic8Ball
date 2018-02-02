package newton.magic8ball;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Newton on 1/27/2018.
 */

public class BallScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ball_screen);
        rollEvent();



    }

    public void returnButtonClick(View v) {
        finish();
    }


    public void rollEvent() {
        ImageView fadeImage = (ImageView)findViewById(R.id.imageView);



        //Roll random between 0-19
        int random = new Random().nextInt(20) + 0;
        int testRandom = 5;

        Context c = getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/"+"test"+String.valueOf(testRandom),null,c.getPackageName());

        fadeImage.setImageResource(id);
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeImage.startAnimation(fadeAnimation);



        //vibrate phone while activity resets
        shakeVibrationEvent();


    }

    public void shakeVibrationEvent() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
    }


}
