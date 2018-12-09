package com.pinno.game2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public class ScoreDatabase extends SQLiteOpenHelper {

    private static final String TAG = "ScoreDatabase";

    private static String DB_NAME = "game_score.db";
    private static int DB_VERSION = 1;

    public static String TABLE_NAME = "scores";
    public static String COLUMN_ID = "_id";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_SCORE = "score";
    private String[] COLUMN_NAMES = {COLUMN_ID, COLUMN_NAME, COLUMN_SCORE};

    public ScoreDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SCORE + " TEXT " +
                ")";

        db.execSQL(CREATE_TABLE);

        Log.d(TAG, "onCreate: database created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void insetScore(PlayerClass player) {
        SQLiteDatabase database =
                this.getWritableDatabase();

        ContentValues values = player.getContentValues();

        database.insert(TABLE_NAME, null, values);

        database.close();
    }

    public List<PlayerClass> getTopPlayers() {
        SQLiteDatabase database =
                this.getWritableDatabase();

        List<PlayerClass> players = new ArrayList<>();

        Cursor cursor = database.query(
                TABLE_NAME,
                COLUMN_NAMES,
                null,
                null,
                null,
                null,
                COLUMN_SCORE + " DESC", ///getting the scores in descending order
                "3" ///taking only top 3
        );

        while (cursor.moveToNext()) {
            PlayerClass playerClass = new PlayerClass(
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_SCORE))
            );
            players.add(playerClass);
        }

        cursor.close();
        database.close();
        return players;
    }
}
