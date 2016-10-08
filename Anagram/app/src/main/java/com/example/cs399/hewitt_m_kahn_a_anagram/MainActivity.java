package com.example.cs399.hewitt_m_kahn_a_anagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button aboutButton;
    private Button newGameButton;
    private Button exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton = (Button) findViewById(R.id.button2);
        newGameButton = (Button) findViewById(R.id.button);
        exitButton = (Button) findViewById(R.id.button4);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNewGame();
            }
        });
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAbout();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    public void toNewGame(){

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void toAbout(){

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
