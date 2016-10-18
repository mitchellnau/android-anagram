package com.example.cs399.hewitt_m_kahn_a_anagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private int res_score = 0;
    int res_total = 0;
    int res_incorrect = 0;
    int res_unanswered = 0;

    int from = 0;

    private TextView scoreTotal;
    private TextView correctText;
    private TextView incorrectText;
    private TextView resultUnanswered;
    private TextView resultScore;
    private TextView anagramText;
    private Button homeButton;
    private Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        scoreTotal = (TextView)findViewById(R.id.resultTotal);
        correctText = (TextView)findViewById(R.id.correctfield);
        incorrectText = (TextView)findViewById(R.id.incorrectfield);
        resultUnanswered = (TextView)findViewById(R.id.resultUnanswered);
        resultScore = (TextView)findViewById(R.id.resultScore);
        anagramText = (TextView)findViewById(R.id.anagramlist);
        homeButton = (Button)findViewById(R.id.backtohome);
        playAgainButton = (Button)findViewById(R.id.playagainbutton);


        Intent intent = getIntent();

        res_total = intent.getIntExtra("total", -1);
        res_score = intent.getIntExtra("score", -1);
        res_incorrect = intent.getIntExtra("incorrect", -1);
        res_unanswered = intent.getIntExtra("unanswered", -1);
        from = intent.getIntExtra("from", 0);
        String[] correctListOfAnagrams = intent.getStringArrayExtra("correctListOfAnagrams");
        String[] incorrectListOfAnagrams = intent.getStringArrayExtra("incorrectListOfAnagrams");

        scoreTotal.setText("Total anagrams: " + Integer.toString(res_total));
        correctText.setText("Correct: " + Integer.toString(res_score));
        incorrectText.setText("Incorrect: " + Integer.toString(res_incorrect));
        resultUnanswered.setText("Unanswered: " + Integer.toString(res_unanswered));
        resultScore.setText("Score: " + Double.toString((double)res_score/res_total*100) + "%");
        anagramText.setText("Answer :: Guess" +
                correctListOfAnagrams[0] + incorrectListOfAnagrams[0] + "\n" +
                correctListOfAnagrams[1] + incorrectListOfAnagrams[1] + "\n" +
                correctListOfAnagrams[2] + incorrectListOfAnagrams[2] + "\n" +
                correctListOfAnagrams[3] + incorrectListOfAnagrams[3] + "\n" +
                correctListOfAnagrams[4] + incorrectListOfAnagrams[4] + "\n" +
                correctListOfAnagrams[5] + incorrectListOfAnagrams[5] + "\n" +
                correctListOfAnagrams[6] + incorrectListOfAnagrams[6] + "\n" +
                correctListOfAnagrams[7] + incorrectListOfAnagrams[7] + "\n" +
                correctListOfAnagrams[8] + incorrectListOfAnagrams[8] + "\n" +
                correctListOfAnagrams[9] + incorrectListOfAnagrams[9] + "\n");

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHome();
            }
        });
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(from == 0){
                    playEasy();
                }
                else{
                    playAgain();
                }
            }
        });

    }

    public void toHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void playEasy(){
        Intent intent = new Intent(this, EasyActivity.class);
        startActivity(intent);
    }

    public void playAgain(){
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);
    }
}
