package com.example.android.displayjokes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by adamzarn on 9/12/17.
 */

public class DisplayJokeActivity extends AppCompatActivity {

    TextView setupTextView;
    TextView punchlineTextView;
    Button showPunchlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        String setupAndPunchline = getIntent().getStringExtra("joke");
        String joke[] = setupAndPunchline.split("\n\n");
        String setup = joke[0];
        String punchline = joke[1];

        setupTextView = (TextView) findViewById(R.id.setup_text_view);
        setupTextView.setText(setup);

        punchlineTextView = (TextView) findViewById(R.id.punchline_text_view);
        punchlineTextView.setText(punchline);
        punchlineTextView.setVisibility(View.GONE);

        showPunchlineButton = (Button) findViewById(R.id.show_punchline_button);
        showPunchlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPunchline();
            }
        });

    }

    private void showPunchline() {
        punchlineTextView.setVisibility(View.VISIBLE);
        showPunchlineButton.setVisibility(View.GONE);
    }


}
