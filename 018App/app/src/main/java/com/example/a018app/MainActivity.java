package com.example.a018app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void buttonClicked(View view)
    {
        EditText enteredName = (EditText) findViewById(R.id.enteredName);
        Toast.makeText(MainActivity.this, "Hey there " + enteredName.getText().toString() + "!", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
