package newton.magic8ball;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ball_screen);
        rollEvent();

        //Setup for the phone listening for shake events.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                reShakeEvent();
            }
        });
    }

    //Stop listening for shake events, saves battery.
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    //Resume listening for shake events.
    @Override
    protected void onResume() {
        mSensorManager.registerListener(mShakeDetector,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    //User clicks the return button
    public void returnButtonClick(View v) {
        finish();
    }

    //Method for loading the answer from a question.
    public void rollEvent() {
        //imageview setup
        ImageView fadeImage = (ImageView)findViewById(R.id.imageView);
        //Roll random between 0-20
        int random = new Random().nextInt(21) + 0;
        //Setup for altering the image of the answer.
        Context c = getApplicationContext();
        //Change the image based on what the random number was
        int id = c.getResources().getIdentifier("drawable/"+"t"+String.valueOf(random),null,c.getPackageName());
        //Start fade animation
        fadeImage.setImageResource(id);
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeImage.startAnimation(fadeAnimation);
        //vibrate phone while activity resets
        shakeVibrationEvent();
    }

    //Method for vibrating the phone when the user shakes it.
    public void shakeVibrationEvent() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
    }

    //Method for if the user shakes the phone on the same question.
    public void reShakeEvent() {
        ImageView fadeImage = (ImageView)findViewById(R.id.imageView);
        fadeImage.setImageResource(R.drawable.blank);
        rollEvent();
        shakeVibrationEvent();
    }
}
