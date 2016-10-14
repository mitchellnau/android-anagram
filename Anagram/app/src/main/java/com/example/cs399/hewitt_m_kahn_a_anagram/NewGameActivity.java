package com.example.cs399.hewitt_m_kahn_a_anagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity {

    private TextView letterfield;
    private TextView scorefield;
    private EditText inputfield;
    private Button nextButton;
    int number = 0;
    int score = 0;
    int current = 0;
    int total = 10;
    String input = "";
    String[] words;
    private String lf_content = "";
    private String[] wordlist = {"good", "worse", "first", //50 words in this list
            "last", "long", "great", "little", "turtle",
            "other", "blink", "right", "awesome", "high",
            "different", "miniscule", "large", "next",
            "early", "young", "important", "flexible",
            "public", "potter", "toxic", "able", "timeliness",
            "person", "year", "gopher", "golfcourse", "thing",
            "winch", "world", "ancient", "hand", "part",
            "child", "roster", "woman", "place", "work",
            "week", "case", "point", "government",
            "company", "number", "group", "problem",
            "fact", "office", "napkin", "restrict"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        /*
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
        */

        Random random = new Random();
        current++;

        number = random.nextInt(67) % 50;
        lf_content = jumbleString(wordlist[number]);
        letterfield = (TextView)findViewById(R.id.letterfield);
        letterfield.setText(lf_content);

        scorefield = (TextView)findViewById(R.id.scoreField);

        inputfield = (EditText)findViewById(R.id.inputField);

        updateScore();

        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore();
                if(current < total){
                    nextAnagram();
                }
                if(current == total){
                    nextButton.setText("Results");
                }
                if(current > total){
                    toResults();
                }
                current++;
            }
        });


    }
    private String jumbleString(String input){
        Random random = new Random();
        int number = 0;

        for(int i = 0; i < input.length(); i++){
            number = random.nextInt(50) % input.length();
            input = input.substring(0, number) + input.substring(number+1, input.length()) + Character.toString(input.charAt(number));

        }
        return input;
    }
    public void nextAnagram(){
        Random random = new Random();
        number = random.nextInt(67) % 50;
        lf_content = jumbleString(wordlist[number]);
        letterfield.setText(lf_content);
    }
    public void updateScore(){
        if(wordlist[number].compareTo(inputfield.getText().toString()) == 0){
            score++;
        }
        scorefield.setText("Score: " + Integer.toString(score) + "/" + Integer.toString(total) + " ");
        inputfield.setText("");
    }

    public void toResults(){
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("total", total);
        intent.putExtra("from", 1);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}
