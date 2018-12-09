package com.pinno.game2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class HighScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        ScoreDatabase database =
                new ScoreDatabase(this);

        List<PlayerClass> playerClasses = database.getTopPlayers();

        if (playerClasses != null && playerClasses.size() > 0) {

            (findViewById(R.id.empty_hs)).setVisibility(View.GONE);

            TextView hs1 = (TextView) findViewById(R.id.hs_1);
            hs1.setText("1. " +
                    playerClasses.get(0).getPlayerName() + " -- " +
                    playerClasses.get(0).getPlayerScore() + "ms");

            if (playerClasses.size() > 1) {
                TextView hs2 = (TextView) findViewById(R.id.hs_2);
                hs2.setText("2. " +
                        playerClasses.get(1).getPlayerName() + " -- " +
                        playerClasses.get(1).getPlayerScore() + "ms");

                if (playerClasses.size() > 2) {
                    TextView hs3 = (TextView) findViewById(R.id.hs_3);
                    hs3.setText("3. " +
                            playerClasses.get(2).getPlayerName() + " -- " +
                            playerClasses.get(2).getPlayerScore() + "ms");
                }
            }
        } else {
            (findViewById(R.id.empty_hs)).setVisibility(View.VISIBLE);
        }
    }
}
