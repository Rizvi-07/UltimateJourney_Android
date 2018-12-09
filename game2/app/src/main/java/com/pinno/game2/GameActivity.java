package com.pinno.game2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity {

    private TDView gameView;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        gameView = new TDView(this, size.x, size.y);


        setContentView(gameView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    public void onBackPressed() {


        prefs = getSharedPreferences("HiScores", MODE_PRIVATE);

        long fastestTime = prefs.getLong("fastestTime", 1000000);

        if (MainActivity.lastFastestTime > fastestTime)
        {
            Intent intent = new Intent(getApplicationContext(),
                    UserNameInputActivity.class);
            intent.putExtra("score", fastestTime);
            startActivity(intent);

        }

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}