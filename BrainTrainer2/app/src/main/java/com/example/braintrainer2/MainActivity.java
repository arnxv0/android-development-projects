package com.example.braintrainer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   //Button buttonStart = findViewById(R.id.buttonStart);

    public void resetGame(View view){
        /*
        scoreNum = 0;
        totalNum = 0;

        finalHead.setVisibility(View.INVISIBLE);
        finalScore.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(Button.INVISIBLE);

        generateQuestion();*/

        view.setVisibility(View.GONE);


        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long l) {
                Log.i("Sec", "one");
                //timerText = String.valueOf(l) + "/30";
                //timer.setText(timerText);
            }

            @Override
            public void onFinish() {
                //stopGame();
            }
        }.start();

        //question.setVisibility(View.VISIBLE);
        //score.setVisibility(View.VISIBLE);
        //timer.setVisibility(View.VISIBLE);
        //options.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
