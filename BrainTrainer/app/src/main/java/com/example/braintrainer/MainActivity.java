package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int q1,q2;
    int corrop, op1;
    int scoreNum , totalNum ;
    String ques;
    String scoreText;
    String timerText;




    @SuppressLint("SetTextI18n")
    public void resetGame(View view){
        TextView finalScore = findViewById(R.id.textViewFinalScore);
        TextView finalHead = findViewById(R.id.textViewFinalHead);
        TextView question = findViewById(R.id.textViewQuestion);
        TextView score = findViewById(R.id.textViewScore);
        final TextView timer = findViewById(R.id.textViewTimer);
        GridLayout options = findViewById(R.id.options);

        scoreNum = 0;
        totalNum = 0;
        score.setText("Score");
        scoreText = "0/0";


        finalHead.setVisibility(View.GONE);
        finalScore.setVisibility(View.GONE);
        view.setVisibility(View.GONE);

        generateQuestion();

        view.setVisibility(View.GONE);


        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long l) {
                timerText = String.valueOf(l/1000) + "s";
                timer.setText(timerText);
            }

            @Override
            public void onFinish() {
                stopGame();
            }
        }.start();

        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        options.setVisibility(View.VISIBLE);

    }

    public void nextQuestion(View view){
        TextView score = findViewById(R.id.textViewScore);

        totalNum += 1;
        Log.i("correct", String.valueOf(corrop));
        Log.i("Tag", view.getTag().toString());

        if (view.getTag().toString().equals(String.valueOf(corrop))) {
            scoreNum += 1;
        }
        scoreText = String.valueOf(scoreNum) + "/" + String.valueOf(totalNum);
        score.setText(scoreText);
        generateQuestion();

    }

    public void generateQuestion(){
        TextView question = findViewById(R.id.textViewQuestion);
        TextView option1 = findViewById(R.id.textViewOption1);
        TextView option2 = findViewById(R.id.textViewOption2);
        TextView option3 = findViewById(R.id.textViewOption3);
        TextView option4 = findViewById(R.id.textViewOption4);

        Random random = new Random();
        q1 = random.nextInt(50);
        q2 = random.nextInt(50);
        ques= String.valueOf(q1) + " + " + String.valueOf(q2);
        question.setText(ques);
        corrop = random.nextInt(4) + 1;
        Log.i("Tag", String.valueOf(corrop));
        checkCorrectNumber(corrop, option1, q1+q2);
        checkCorrectNumber(corrop, option2, q1+q2);
        checkCorrectNumber(corrop, option3, q1+q2);
        checkCorrectNumber(corrop, option4, q1+q2);
    }

    public void stopGame(){
        Button resetButton = findViewById(R.id.buttonRest);
        TextView finalScore = findViewById(R.id.textViewFinalScore);
        TextView finalHead = findViewById(R.id.textViewFinalHead);
        TextView score = findViewById(R.id.textViewScore);
        TextView timer = findViewById(R.id.textViewTimer);
        GridLayout options = findViewById(R.id.options);
        TextView question = findViewById(R.id.textViewQuestion);

        question.setVisibility(View.GONE);
        score.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
        options.setVisibility(View.GONE);

        finalHead.setVisibility(View.VISIBLE);
        finalScore.setText(scoreText);
        finalScore.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.VISIBLE);

    }

    public void checkCorrectNumber(int op, TextView view,int correctAnswer){
        if(view.getTag().toString().equals(String.valueOf(op))){
            view.setText(String.valueOf(correctAnswer));
        }
        else{
            Random random = new Random();
            op1 = random.nextInt(100);
            while(op1==q1+q2){
                op1 = random.nextInt(100);
            }
            view.setText(String.valueOf(op1));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
}
