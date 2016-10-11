package com.example.cs399.hewitt_m_kahn_a_lara_k_anagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class ChallengeActivity extends AppCompatActivity {

    private Button easyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        easyButton = (Button) findViewById(R.id.easybutton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toEasy();
            }
        });
    }

    public void toEasy(){
        Intent intent = new Intent(this, EasyActivity.class);
        startActivity(intent);
    }
}
