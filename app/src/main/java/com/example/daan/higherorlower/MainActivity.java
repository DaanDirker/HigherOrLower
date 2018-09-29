package com.example.daan.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mScore;
    private TextView mHighscore;
    private ImageView mHigherButton;
    private ImageView mLowerButton;
    private ImageView mDiceImage;
    private int[] mDiceNames;
    private ArrayAdapter<String> mAdapter;
    private List<String> mThrows;
    private ListView mDiceOverview;
    private int score = 0;
    private int highScore = 0;
    Dice dice = new Dice();

    private int previousThrow;
    private int currentThrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiate variables
        mScore = findViewById(R.id.score);
        mHighscore = findViewById(R.id.highscore);
        mHigherButton = findViewById(R.id.higherButton);
        mLowerButton = findViewById(R.id.lowerButton);
        mDiceImage = findViewById(R.id.diceImage);
        mDiceNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4,
                R.drawable.d5, R.drawable.d6};
        mDiceOverview = findViewById(R.id.diceThrows);
        mThrows = new ArrayList<>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                mThrows);
        mDiceOverview.setAdapter(mAdapter);

        //Set first dice rolled
        mDiceImage.setImageResource(mDiceNames[dice.rollDice()]);

        //Set up all OnClicks
        setUpMHigherButtonOnClick();
        setUpMLowerButtonOnClick();
    }

    private void setUpThrow() {
        previousThrow = dice.getIndexRolled();
        dice.rollDice();
        currentThrow = dice.getIndexRolled();

        mDiceImage.setImageResource(mDiceNames[currentThrow]);
        mThrows.add("Dice says: " + (currentThrow + 1));
    }

    private void updateScore(int score) {
        mScore.setText(""+this.score);
    }

    private void updateHighScore(int score){
        //Check whether the score is higher than the current highscore
        if (score > this.highScore) {
            this.highScore = score;
            mHighscore.setText(""+this.highScore);
        }
    }

    private void updateUI() {
        updateScore(score);
        mAdapter.notifyDataSetChanged();
        mDiceOverview.setSelection(mAdapter.getCount() - 1);
    }

    //** All OnClick methods **//
    private void setUpMHigherButtonOnClick () {
        mHigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpThrow();

                if (currentThrow > previousThrow) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                } else {
                    Toast.makeText(getApplicationContext(), "Game over!", Toast.LENGTH_LONG).show();
                    updateHighScore(score);
                    score = 0;
                }

                updateUI();
            }
        });
    }

    private void setUpMLowerButtonOnClick () {
        mLowerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpThrow();

                if (currentThrow < previousThrow) {
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    score++;
                } else {
                    Toast.makeText(getApplicationContext(), "Game over!", Toast.LENGTH_LONG).show();
                    updateHighScore(score);
                    score = 0;
                }

                updateUI();
            }
        });
    }
}
