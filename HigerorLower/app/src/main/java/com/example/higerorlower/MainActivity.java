package com.example.higerorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    int randomNumber;

    public int randomNum()
    {
        Random random = new Random();
        int randNum = random.nextInt(20) + 1;
        return randNum;
    }

    public void checkNum(View view)
    {
        EditText enteredNumber = (EditText) findViewById(R.id.editTextGuess);
        String num= enteredNumber.getText().toString();
        int numInt=Integer.parseInt(num);
        if(randomNumber > numInt)
        {
            Toast.makeText(MainActivity.this, "Higher!", Toast.LENGTH_SHORT).show();
        }
        else if(randomNumber < numInt)
        {
            Toast.makeText(MainActivity.this, "Lower!", Toast.LENGTH_SHORT).show();
        }
        else if(randomNumber == numInt)
        {
            Toast.makeText(MainActivity.this, "Correct! Try again :)", Toast.LENGTH_LONG).show();
            randomNumber = randomNum();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomNumber = randomNum();
        ;
    }
}
