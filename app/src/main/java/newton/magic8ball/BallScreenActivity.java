package newton.magic8ball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Newton on 1/27/2018.
 */

public class BallScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ball_screen);

    }

    public void returnButtonClick(View v) {
        finish();
    }

}
