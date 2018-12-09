package com.pinno.game2;

import android.content.ContentValues;


public class PlayerClass {

    private String playerName;
    private String playerScore;

    public PlayerClass(String playerName, String playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerScore() {
        return playerScore;
    }

    public ContentValues getContentValues()
    {
        ContentValues values = new ContentValues(2);

        values.put(ScoreDatabase.COLUMN_NAME, playerName);
        values.put(ScoreDatabase.COLUMN_SCORE, playerScore);

        return values;
    }
}
