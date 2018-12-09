package com.pinno.game2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
        implements View.OnClickListener {

    public static long lastFastestTime;
    SharedPreferences prefs;
    TextView textFastestTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button buttonPlay =
                (Button) findViewById(R.id.buttonPlay);

        Button hsButton =
                (Button) findViewById(R.id.buttonHighScore);
        hsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HighScoreActivity.class));
            }
        });

        buttonPlay.setOnClickListener(this);

        prefs = getSharedPreferences("HiScores", MODE_PRIVATE);

        textFastestTime = (TextView) findViewById(R.id.textHighScore);

    }

    @Override
    protected void onResume() {
        super.onResume();


        long fastestTime = prefs.getLong("fastestTime", 1000000);


        textFastestTime.setText("Fastest Time:" + fastestTime);
    }

    @Override
    public void onClick(View v) {

        lastFastestTime = prefs.getLong("fastestTime", 1000000);

        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);


    }
}
