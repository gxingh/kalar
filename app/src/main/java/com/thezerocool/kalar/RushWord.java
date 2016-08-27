package com.thezerocool.kalar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Gagandeep Rangi on 4/29/2016.
 */
public class RushWord extends Fragment implements View.OnClickListener {

    //For Scores:
    SharedPreferences scores;
    final static String RUSH_WORD_HIGH = "Rush High Score";

    ImageButton back;
    TextView score, ques, gameMode;
    Button choice1, choice2, choice3, choice4;
    static ProgressBar progressBar;
    Dialog dialog;
    String[] questions = {"blue", "red", "green", "yellow", "cyan", "black", "magenta"};
    int[] colors = {android.graphics.Color.BLUE,
            android.graphics.Color.RED,
            android.graphics.Color.GREEN,
            android.graphics.Color.YELLOW,
            android.graphics.Color.CYAN,
            android.graphics.Color.BLACK,
            android.graphics.Color.MAGENTA
    };
    static int index;
    static int colorIndex;
    static int scoreValue = 0, highScoreValue;
    static boolean progressFlag = true;
    static Thread t;
    static int x = 1000;
    Handler handler;

    //For shuffling answer positions:
    static int shuffledPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_color, viewGroup, false);

        MainActivity.last = 7;
        handler = new Handler(Looper.getMainLooper());

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        back = (ImageButton) v.findViewById(R.id.backButton);
        score = (TextView) v.findViewById(R.id.score);
        ques = (TextView) v.findViewById(R.id.ques);
        gameMode = (TextView) v.findViewById(R.id.gameMode);
        choice1 = (Button) v.findViewById(R.id.choice1);
        choice2 = (Button) v.findViewById(R.id.choice2);
        choice3 = (Button) v.findViewById(R.id.choice3);
        choice4 = (Button) v.findViewById(R.id.choice4);

        gameMode.setText("Rush Word");
        progressBar.setMax(1000);

        back.setOnClickListener(this);
        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);

        scores = getActivity().getSharedPreferences("Scores", Context.MODE_PRIVATE);
//        scores = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (savedInstanceState != null) {
            scoreValue = savedInstanceState.getInt("score");
        } else {
            scoreValue = 0;
        }
        score.setText("" + scoreValue);

        Random r = new Random();
        index = r.nextInt(7);
        colorIndex = r.nextInt(7);
        ques.setText(questions[index]);
        ques.setTextColor(colors[colorIndex]);

        shufflePositions();

        progressFlag = true;
        x = 1000;
        updateProgressBar();

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", scoreValue);
        progressFlag = false;
    }

    private void shufflePositions() {
        int[] tempPositions = new int[3];

        Random r = new Random();
        tempPositions[0] = r.nextInt(7);
        while (tempPositions[0] == index) {
            tempPositions[0] = r.nextInt(7);
        }
        tempPositions[1] = r.nextInt(7);
        while (tempPositions[1] == index || tempPositions[1] == tempPositions[0]) {
            tempPositions[1] = r.nextInt(7);
        }
        tempPositions[2] = r.nextInt(7);
        while (tempPositions[2] == index || tempPositions[2] == tempPositions[1] || tempPositions[2] == tempPositions[0]) {
            tempPositions[2] = r.nextInt(7);
        }

        //Shuffle positions:
        shuffledPosition = r.nextInt(4);
        switch (shuffledPosition) {
            case 0:
                choice1.setText(questions[index]);
                choice1.setBackgroundColor(colors[index]);

                choice2.setText(questions[tempPositions[0]]);
                choice2.setBackgroundColor(colors[tempPositions[0]]);
                choice3.setText(questions[tempPositions[1]]);
                choice3.setBackgroundColor(colors[tempPositions[1]]);
                choice4.setText(questions[tempPositions[2]]);
                choice4.setBackgroundColor(colors[tempPositions[2]]);

                break;
            case 1:
                choice2.setText(questions[index]);
                choice2.setBackgroundColor(colors[index]);

                choice1.setText(questions[tempPositions[0]]);
                choice1.setBackgroundColor(colors[tempPositions[0]]);
                choice3.setText(questions[tempPositions[1]]);
                choice3.setBackgroundColor(colors[tempPositions[1]]);
                choice4.setText(questions[tempPositions[2]]);
                choice4.setBackgroundColor(colors[tempPositions[2]]);

                break;
            case 2:
                choice3.setText(questions[index]);
                choice3.setBackgroundColor(colors[index]);

                choice2.setText(questions[tempPositions[0]]);
                choice2.setBackgroundColor(colors[tempPositions[0]]);
                choice1.setText(questions[tempPositions[1]]);
                choice1.setBackgroundColor(colors[tempPositions[1]]);
                choice4.setText(questions[tempPositions[2]]);
                choice4.setBackgroundColor(colors[tempPositions[2]]);

                break;
            case 3:
                choice4.setText(questions[index]);
                choice4.setBackgroundColor(colors[index]);

                choice2.setText(questions[tempPositions[0]]);
                choice2.setBackgroundColor(colors[tempPositions[0]]);
                choice3.setText(questions[tempPositions[1]]);
                choice3.setBackgroundColor(colors[tempPositions[1]]);
                choice1.setText(questions[tempPositions[2]]);
                choice1.setBackgroundColor(colors[tempPositions[2]]);

                break;
        }
    }


    private void updateProgressBar() {

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressFlag) {
                    if (x == 0) {
                        progressFlag = false;
                        gameOver();
                    }

                    progressBar.setProgress(x);
                    x--;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        });
        t.start();

    }

    public void nextQuestion() {
        progressFlag = true;

        scoreValue++;
        score.setText("" + scoreValue);

        Random r = new Random();
        index = r.nextInt(7);
        colorIndex = r.nextInt(7);
        ques.setText(questions[index]);
        ques.setTextColor(colors[colorIndex]);

        shufflePositions();
    }

    public void gameOver() {
        progressFlag = false;
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    int temp = scores.getInt(RUSH_WORD_HIGH, 0);
                    highScoreValue = temp;
                    if (scoreValue >= temp) {
                        SharedPreferences.Editor editor = scores.edit();
                        editor.putInt(RUSH_WORD_HIGH, RushWord.scoreValue);
                        highScoreValue = scoreValue;
                        editor.commit();
                    }
                    DialogGameOver dialog = new DialogGameOver();
                    dialog.show(getFragmentManager(), "show");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Fragment f;
        FragmentTransaction ft;

        progressFlag = false;
        switch (v.getId()) {
            case R.id.backButton:
                getFragmentManager().popBackStack();
                f = new Front();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, f);

                ft.commit();

                break;
            case R.id.choice1:
                if (questions[index].equals(choice1.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;
            case R.id.choice2:
                if (questions[index].equals(choice2.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;
            case R.id.choice3:
                if (questions[index].equals(choice3.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;
            case R.id.choice4:
                if (questions[index].equals(choice4.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;
        }

    }
}

