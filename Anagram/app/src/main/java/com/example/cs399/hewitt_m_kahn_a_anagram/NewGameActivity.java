package com.example.cs399.hewitt_m_kahn_a_anagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity {

    private TextView letterfield;
    private String lf_content = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        String alphabet = "AEIOUBCDFGHJKLMNPQRSTVWXYZ";


        String current_letter = "$";
        int number = 0;
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            number = random.nextInt(50) % 26;
            current_letter = Character.toString(alphabet.charAt(number));
            while(lf_content.contains(current_letter)){
                number = random.nextInt(50) % 26;
                current_letter = Character.toString(alphabet.charAt(number));
            }
            lf_content = lf_content + current_letter;
        }
        //if there are no vowels, add one
        if(!(lf_content.contains("A") ||
                lf_content.contains("E") ||
                lf_content.contains("I") ||
                lf_content.contains("O") ||
                lf_content.contains("U"))){
            number = random.nextInt(50) % 5;
            current_letter = Character.toString(alphabet.charAt(number));
            lf_content = lf_content + current_letter;
        }

        //if there is a q, add a u if there isn't one already
        if(lf_content.contains("Q") && !lf_content.contains("U")){
            current_letter = Character.toString(alphabet.charAt(4));
            lf_content = lf_content + current_letter;
        }

        letterfield = (TextView)findViewById(R.id.letterfield);
        letterfield.setText(lf_content);


    }
}
