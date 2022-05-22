package com.example.guesstheceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {

    Button option1;
    Button option2;
    Button option3;
    Button option4;
    ImageView imageView;

    int correctOption, correctName;

    String webContent = null;

    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

    public static class DownloadData extends AsyncTask<String, Void, String>{

        StringBuilder webContent = new StringBuilder();
        //String webContent = null;

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;
            try{
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){

                    char letter = (char) data;
                    webContent.append(letter);
                    data = reader.read();

                }
                return webContent.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public static class DownloadBitmap extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;
            Bitmap myBitmap;

            try{
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(in);

                return myBitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    public void setImage(String url) throws ExecutionException, InterruptedException {

        Bitmap imageBitmap;
        DownloadBitmap imageDownloader = new DownloadBitmap();
        imageBitmap = imageDownloader.execute(url).get();
        imageView.setImageBitmap(imageBitmap);
    }


    public void checkOptionClicked(View view){
        if(Integer.parseInt(view.getTag().toString()) == correctOption){
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(MainActivity.this, "Wrong! It is " + names.get(correctName), Toast.LENGTH_LONG).show();
        }

        setQuestion();
    }

    public void setQuestion(){

        Random random = new Random();
        correctName = random.nextInt(100);
        correctOption = random.nextInt(4) + 1;
        try {
            setImage(links.get(correctName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkOption(correctOption, option1, names.get(correctName), correctName);
        checkOption(correctOption, option2, names.get(correctName), correctName);
        checkOption(correctOption, option3, names.get(correctName), correctName);
        checkOption(correctOption, option4, names.get(correctName), correctName);

    }

    public void checkOption(int a, Button button, String correctName,int b){
        if(a == Integer.parseInt(button.getTag().toString())){
            button.setText(correctName);
        }
        else{
            Random random = new Random();
            int randomNum = random.nextInt(100);
            while(randomNum == b){
                randomNum = random.nextInt(100);
            }
            button.setText(names.get(randomNum));
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        option1 = findViewById(R.id.buttonOption1);
        option2 = findViewById(R.id.buttonOption2);
        option3 = findViewById(R.id.buttonOption3);
        option4 = findViewById(R.id.buttonOption4);
        imageView = findViewById(R.id.imageView);

        DownloadData task = new DownloadData();

        try {
            webContent = task.execute("https://www.imdb.com/list/ls052283250/").get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        Pattern pName = Pattern.compile("<img alt=\"(.*?)\"");
        Matcher mName = pName.matcher(webContent);
        Pattern pLink = Pattern.compile("src=\"(.*?)\"");
        Matcher mLink = pLink.matcher(webContent);

        while (mName.find()){
             String name = mName.group(1);
             names.add(name);
        }

        for (int i = 1; i<=106;i++) {

            mLink.find();
            if (i > 5) {
                String imgLink = mLink.group(1);
                links.add(imgLink);
            }
        }
        setQuestion();

    }
}
