package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //api.openweathermap.org/data/2.5/weather?q=bangalore&appid=87740ae6c74e1a4fcf40cf4a362e4038

    TextView weatherInfo;
    EditText cityName;


    public void getWeather(View view){

        String cityNameEntered = cityName.getText().toString().toLowerCase();
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityNameEntered + "&appid=87740ae6c74e1a4fcf40cf4a362e4038";
        GetWeatherInfo weatherReport = new GetWeatherInfo();
        weatherReport.execute(apiUrl);

    }

    @SuppressLint("StaticFieldLeak")
    public class GetWeatherInfo extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder cityWeather = new StringBuilder();
            URL url;
            HttpURLConnection connection = null;

            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data!=-1){
                    char letter = (char) data;
                    cityWeather.append(letter);
                    data = reader.read();
                }
                return cityWeather.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "Could not get weather!";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject jsonObject = new JSONObject(s);
                String jsonInfo = jsonObject.getString("weather");
                JSONArray arr = new JSONArray(jsonInfo);
                JSONObject jsonPart = arr.getJSONObject(0);
                String displayInfo = jsonPart.getString("description");
                weatherInfo.setText(displayInfo.toUpperCase());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherInfo = findViewById(R.id.textViewInfo);
        cityName = findViewById(R.id.editTextCityName);
    }
}
