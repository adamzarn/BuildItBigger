package com.example.android.displayjokes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by adamzarn on 9/12/17.
 */

public class DisplayJokeActivity extends AppCompatActivity {

    TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        jokeTextView = (TextView) findViewById(R.id.joke_text_view);
        jokeTextView.setText(getIntent().getStringExtra("joke"));

    }


}
