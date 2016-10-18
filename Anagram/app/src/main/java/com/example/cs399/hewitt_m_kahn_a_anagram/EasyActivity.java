package com.example.cs399.hewitt_m_kahn_a_lara_k_anagram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import android.os.CountDownTimer;

public class EasyActivity extends AppCompatActivity {
    private TextView letterfield;
    private TextView remfield;
    private TextView timerfield;
    private EditText inputfield;
    private Button nextButton;
    private Button skipButton;
    Queue<Integer> words = new LinkedList<>();
    int incorrect = 0;
    int score, tries = 0;
    int current = 0;
    int total = 10;
    int time= 60;
    String theirWord;
    String[] wordsWrong;
    private String lf_content = "";
    private String[] wordlist = {"good", "worse", "first", //10 words in this list
            "last", "long", "great", "turtle", "other", "blink", "large"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        wordsWrong = new String[total];
        for(int i = 0; i < total; i++){
            wordsWrong[i] = "";
        }

        String wordnums = "";
        for(int i = 0; i < total; i++){
            wordnums += String.valueOf(i);
        }
        wordnums = jumbleString(wordnums);
        for(int i = 0; i < total; i++){
            String tmp = "";
            tmp += wordnums.charAt(i);
            words.add(Integer.valueOf(tmp));
        }
        current = words.remove();
        lf_content = jumbleString(wordlist[current]);
        letterfield = (TextView)findViewById(R.id.letterfield);
        letterfield.setText(lf_content);

        remfield = (TextView)findViewById(R.id.remfield);
        timerfield = (TextView)findViewById(R.id.timerfield);

        inputfield = (EditText)findViewById(R.id.inputField);

        dispScore();

        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tries++;
                updateScore(current);
                if(tries < total){
                    nextAnagram();
                }
                else{
                    toResults(0);
                }
            }
        });

        skipButton = (Button)findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words.add(current);
                nextAnagram();

            }
        });

        new CountDownTimer(time * 1000, 1000){
            String minutes, seconds;
            int mins = 0;
            int secs = 0;
            public void onTick(long millsUntilOver){
                mins = time / 60;
                secs = time % 60;
                if(mins < 10){
                    minutes = "0" + String.valueOf(mins);
                }else{
                    minutes = String.valueOf(mins);
                }
                if(secs < 10){
                    seconds = "0" + String.valueOf(secs);
                }else {
                    seconds = String.valueOf(secs);
                }
                timerfield.setText("Time: " + minutes + ":" + seconds);
                if(secs == 0){
                    mins--;
                }
                time--;
            }
            public void onFinish(){
                timerfield.setText("Time: 00:00");
                toResults(1);
            }
        }.start();
    }
    private String jumbleString(String input){
        Random random = new Random();
        int number;

        for(int i = 0; i < input.length(); i++){
            number = random.nextInt(50) % input.length();
            input = input.substring(0, number) + input.substring(number+1, input.length()) + Character.toString(input.charAt(number));

        }
        return input;
    }
    public void nextAnagram(){
        if(words.isEmpty()){
            toResults(0);
        }else {
            current = words.remove();
            lf_content = jumbleString(wordlist[current]);
            letterfield.setText(lf_content);
        }
    }
    public void updateScore(int ind){
        theirWord = inputfield.getText().toString();
        if(wordlist[ind].compareTo(theirWord) == 0){
            score++;
        }else{
            incorrect++;
        }
        wordsWrong[current] = theirWord;
        remfield.setText("Remaining: " + Integer.toString(score) + " right /" + Integer.toString(total - tries) + " left");
        inputfield.setText("");
    }

    public void dispScore(){
        remfield.setText("Remaining: " + Integer.toString(score) + " right /" + Integer.toString(total - tries) + " left");
        inputfield.setText("");
    }

    public void toResults(int work){
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("total", total);
        intent.putExtra("score", score);
        intent.putExtra("unanswered", words.size() + work);
        intent.putExtra("incorrect", incorrect);
        intent.putExtra("correctList", wordlist);
        intent.putExtra("incorrectList", wordsWrong);
        startActivity(intent);
    }
}
