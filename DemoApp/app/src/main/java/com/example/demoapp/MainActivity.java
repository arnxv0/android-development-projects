package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity
{
    public void revealButton(View view)
    {
        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.doctorr);
        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.GONE);
        Log.i("Test", "Pressed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
