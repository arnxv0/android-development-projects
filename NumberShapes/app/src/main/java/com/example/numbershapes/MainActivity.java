package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public boolean checkSquare(int num)
    {
        int i = 1;
        while(i<=(num/2))
        {
            if(num%i==0 && i*i==num)
            {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean checkTriangle(int num)
    {
        if(num==1)
        {
            return false;
        }
        int i=1;
        while(num>=0)
        {
            if (num==0)
            {
                return true;
            }
            num=num-i;
            i++;
        }
        return false;
    }

    public void makeToast(String str)
    {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    public void checkNum(View view)
    {
        EditText enteredNumber = (EditText) findViewById(R.id.editTextNum);
        if (enteredNumber.getText().toString().isEmpty())
        {
            makeToast("Enter a number!");
        }
        else
         {
            int numberInt = Integer.parseInt(enteredNumber.getText().toString());
            boolean square = checkSquare(numberInt);
            boolean triangle = checkTriangle(numberInt);

            if (numberInt <= 0)
            {
                makeToast("Enter a positive integer!");
            }
            else if (square && triangle)
            {
                makeToast("It is a square and triangle number!");
            }
            else if (square)
            {
                makeToast("It is a square number!");
            }
            else if (triangle)
            {
                makeToast("It is a triangle number!");
            }
            else
            {
                makeToast("Try again!");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
