package com.example.android.factorify;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    View viewBackground;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SCORE = "score";
    public static final String STREAK = "streak";
    TextView streakTextView;
    TextView scoreTextView;
    EditText inputNumber;
    Button option1;
    Button option2;
    Button option3;
    String scoreData = "0";
    String streakData = "0";
    int input, random_factor, random_non_factor1, random_non_factor2, flag = 0;
    int score, streak;
    ArrayList<Integer> factors = new ArrayList<Integer>(0);
    ArrayList<Integer> non_factors = new ArrayList<Integer>(0);
    ArrayList<Integer> options = new ArrayList<Integer>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewBackground = this.getWindow().getDecorView();
        streakTextView = findViewById(R.id.streak_text_view);
        inputNumber = findViewById(R.id.input_edit_text);
        scoreTextView = findViewById(R.id.score_text_view);
        option1 = findViewById(R.id.button_1);
        option2 = findViewById(R.id.button_2);
        option3 = findViewById(R.id.button_3);
        loadData();
        updateViews();
        score = Integer.parseInt(scoreData);
        streak = Integer.parseInt(streakData);
    }


    public void generateFactor(View view) {
        goNormal(viewBackground);
        try {
            input = Integer.parseInt(inputNumber.getText().toString());
            Log.v("MainActivity", "input " + input);
            factorise(input);
            if (factors.isEmpty()) {
                if (input == 0 | input == 1) {
                    Toast.makeText(this, "Do not enter 0 or 1! ", Toast.LENGTH_SHORT).show();
                    flag = 1;
                } else {
                    Toast.makeText(this, "Prime number found! Only factors are 1 and " + input, Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
            }
            if (flag == 0) {
                getRandomFactor(factors);
                Log.v("MainActivity", "random factor selected");
                Collections.shuffle(non_factors);
                Log.v("MainActivity", "shuffled non factors");
                random_non_factor1 = non_factors.get(0);
                Log.v("MainActivity", "non factor 1");
                random_non_factor2 = non_factors.get(1);
                Log.v("MainActivity", "non factor 2");
                factors.clear();
                Log.v("MainActivity", "factor list cleared");
                non_factors.clear();
                Log.v("MainActivity", "non factor list cleared");
                options.add(random_factor);
                options.add(random_non_factor1);
                options.add(random_non_factor2);
                Log.v("MainActivity", "options added in list");
                Collections.shuffle(options);
                Log.v("MainActivity", "options shuffled");
                button1();
                Log.v("MainActivity", "button 1 showed");
                button2();
                Log.v("MainActivity", "button 2 showed");
                button3();
                Log.v("MainActivity", "button 3 showed");
            }
            flag = 0;
        } catch (Exception e) {
            Toast.makeText(this, "Enter a no. first", Toast.LENGTH_SHORT).show();
        }
    }
    public void goGreen(View view){
        view.setBackgroundResource(R.color.green);
    }
    public void goRed(View view){
        view.setBackgroundResource(R.color.red);
    }
    public void goNormal(View view) {
        view.setBackgroundResource(R.color.white);
    }

    public void saveData() {
        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(STREAK, streakTextView.getText().toString());
        editor.putString(SCORE, scoreTextView.getText().toString());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        scoreData = sharedpreferences.getString(SCORE, "0");
        streakData = sharedpreferences.getString(STREAK, "0");
    }

    public void updateViews() {
        scoreTextView.setText(scoreData);
        streakTextView.setText(streakData);
    }

    @SuppressLint("SetTextI18n")
    public void button1(View view) {
        try {
            if (options.get(0) == random_factor) {
                Toast.makeText(this, "Congrats! correct answer", Toast.LENGTH_SHORT).show();
                goGreen(viewBackground);
                score += 100;
                streak += 1;
                scoreTextView.setText("" + score);
                streakTextView.setText("" + streak);
            } else {
                Toast.makeText(this, "Sorry, incorrect. The correct answer is " + random_factor, Toast.LENGTH_SHORT).show();
                goRed(viewBackground);
                score = 0;
                streak = 0;
                scoreTextView.setText("" + score);
                streakTextView.setText("" + streak);
            }
            options.clear();
        } catch (Exception e) {
            Toast.makeText(this, "Generate factor again!", Toast.LENGTH_SHORT).show();
        }
        saveData();
        Log.v("MainActivity", "saved data");
    }

    @SuppressLint("SetTextI18n")
    public void button2(View view) {
        try {
            if (options.get(1) == random_factor) {
                Toast.makeText(this, "Congrats! correct answer", Toast.LENGTH_SHORT).show();
                goGreen(viewBackground);
                score += 100;
                streak += 1;
                scoreTextView.setText("" + score);
                streakTextView.setText("" + streak);
            } else {
                Toast.makeText(this, "Sorry, incorrect. The correct answer is " + random_factor, Toast.LENGTH_SHORT).show();
                goRed(viewBackground);
                score = 0;
                streak = 0;
                scoreTextView.setText("" + score);
                streakTextView.setText("" + streak);
            }
            options.clear();
        } catch (Exception e) {
            Toast.makeText(this, "Generate factor again!", Toast.LENGTH_SHORT).show();
        }
        saveData();
        Log.v("MainActivity", "saved data");
    }

    @SuppressLint("SetTextI18n")
    public void button3(View view) {
        try {
            if (options.get(2) == random_factor) {
                Toast.makeText(this, "Congrats! correct answer", Toast.LENGTH_SHORT).show();
                goGreen(viewBackground);
                score += 100;
                streak += 1;
                scoreTextView.setText("" + score);
                streakTextView.setText("" + streak);
            } else {
                Toast.makeText(this, "Sorry, incorrect. The correct answer is " + random_factor, Toast.LENGTH_SHORT).show();
                goRed(viewBackground);
                score = 0;
                streak = 0;
                scoreTextView.setText("" + score);
                streakTextView.setText("" + streak);
            }
            options.clear();
        } catch (Exception e) {
            Toast.makeText(this, "Generate factor again!", Toast.LENGTH_SHORT).show();
        }
        saveData();
        Log.v("MainActivity", "saved data");
    }

    private static Random random = new Random();

    private void getRandomFactor(ArrayList<Integer> list) {
        int index = random.nextInt(factors.size());
        random_factor = factors.get(index);
    }

    private void factorise(int number) {
        for (int i = 2; i < input; i++) {
            if (input % i == 0) {
                factors.add(i);
            } else {
                non_factors.add(i);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void button1() {
        option1.setText("" + options.get(0));
    }

    @SuppressLint("SetTextI18n")
    private void button2() {
        option2.setText("" + options.get(1));
    }

    @SuppressLint("SetTextI18n")
    private void button3() {
        option3.setText("" + options.get(2));
    }
}


