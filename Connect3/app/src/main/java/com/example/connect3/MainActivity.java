package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //1 = x , 0 = o
    int moves = 0;
    int player = 1;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameIsActive = true;


    @SuppressLint("SetTextI18n")
    public void dropIn(View view){
        moves +=1;
        TextView winnerText = findViewById(R.id.textViewWinner);
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter]==2 && gameIsActive) {
            counter.setTranslationY(-2000f);
            gameState[tappedCounter] = player;

            if (player == 1) {
                counter.setImageResource(R.drawable.x);
                player = 0;
                winnerText.setText("O's turn!");
            } else if (player == 0) {
                counter.setImageResource(R.drawable.o);
                player = 1;
                winnerText.setText("X's turn!");
            }

            counter.animate().translationYBy(2000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions){

                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){

                    gameIsActive = false;
                    String winnerSymbol = "";
                    if(gameState[winningPosition[0]]==0){
                        winnerSymbol ="O";
                    }
                    else {
                        winnerSymbol ="X";
                    }

                    Button buttonReset = (Button) findViewById(R.id.buttonReset);
                    winnerText.setText(winnerSymbol +" won!");
                    buttonReset.setVisibility(View.VISIBLE);
                }
            }
            if (moves==9){
                Button buttonReset = (Button) findViewById(R.id.buttonReset);
                winnerText.setText("Draw!");
                buttonReset.setVisibility(View.VISIBLE);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void resetGame(View view){
        gameIsActive=true;
        Button buttonReset = findViewById(R.id.buttonReset);
        TextView winnerText = findViewById(R.id.textViewWinner);

        buttonReset.setVisibility(View.GONE);
        winnerText.setText("X's turn!");
        moves = 0;
        player = 1;
        Arrays.fill(gameState, 2);

        GridLayout gridLayout = findViewById(R.id.gridLayoutBoard);
        for(int i=0 ; i <gridLayout.getChildCount() ; i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
