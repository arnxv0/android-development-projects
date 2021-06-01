package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void currencyConvert(View view)
    {
        EditText rupee = (EditText) findViewById(R.id.editTextEnteredCurrency);
        double rupeeValueDouble = Double.parseDouble(rupee.getText().toString());
        double dollarValueDouble = 0.013*rupeeValueDouble;
        Toast.makeText(MainActivity.this, String.format("%.2f", dollarValueDouble) + " Dollars", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
