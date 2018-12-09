package com.pinno.game2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class UserNameInputActivity extends AppCompatActivity {

    EditText userText;
    long score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_input);

        userText = (EditText) findViewById(R.id.user_input_et);
        score = getIntent().getLongExtra("score", 1000000);

    }

    public void insertScore(View view)
    {
        String name = userText.getText().toString();
        if (TextUtils.isEmpty(name))
        {
            userText.setError("Please Enter Your Name:");
        }
        else {
            userText.setError(null);

            PlayerClass playerClass = new PlayerClass(name, String.valueOf(score));

            ScoreDatabase database =
                    new ScoreDatabase(this);

            database.insetScore(playerClass);


            finish();
        }
    }
}
