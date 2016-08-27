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
 * Created by Gagandeep Rangi on 4/19/2016.
 */
public class MadColor extends Fragment implements View.OnClickListener {
    //For Scores:
    SharedPreferences scores;
    final static String MAD_COLOR_HIGH = "Mad Color High Score";

    ImageButton back;
    TextView score, ques, gameMode;
    Button choice1, choice2, choice3, choice4;
    static ProgressBar progressBar;

    Dialog dialog;

    String[] questions = {"blue", "red", "green", "yellow", "cyan", "black", "magenta"};
    static int index;
    static String currentColor = "blue";
    static int colorIndex;
    static int defaultValue = android.graphics.Color.BLUE;
    static int scoreValue, highScoreValue;
    static int scoreQuotient;
    static boolean progressFlag = true;
    static Thread t;
    static int x = 1000;
    Handler handler;
    static SharedPreferences colorsx;

    //For shuffling answer positions:
    static int shuffledPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_color, viewGroup, false);
        MainActivity.last = 4;
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

        gameMode.setText("Mad Color");
        progressBar.setMax(1000);

        colorsx = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = colorsx.edit();
        editor.putInt("blue", android.graphics.Color.BLUE);
        editor.putInt("red", android.graphics.Color.RED);
        editor.putInt("yellow", android.graphics.Color.YELLOW);
        editor.putInt("green", android.graphics.Color.GREEN);
        editor.putInt("cyan", android.graphics.Color.CYAN);
        editor.putInt("black", android.graphics.Color.BLACK);
        editor.putInt("magenta", android.graphics.Color.MAGENTA);
        editor.commit();

        scores = getActivity().getSharedPreferences("Scores", Context.MODE_PRIVATE);
//        scores = getActivity().getPreferences(Context.MODE_PRIVATE);

        back.setOnClickListener(this);
        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);

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
        ques.setTextColor(colorsx.getInt(questions[colorIndex], defaultValue));
        currentColor = questions[colorIndex];

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
        while (tempPositions[0] == colorIndex) {
            tempPositions[0] = r.nextInt(7);
        }
        tempPositions[1] = r.nextInt(7);
        while (tempPositions[1] == colorIndex || tempPositions[1] == tempPositions[0]) {
            tempPositions[1] = r.nextInt(7);
        }
        tempPositions[2] = r.nextInt(7);
        while (tempPositions[2] == colorIndex || tempPositions[2] == tempPositions[1] || tempPositions[2] == tempPositions[0]) {
            tempPositions[2] = r.nextInt(7);
        }

        //Shuffle positions:
        shuffledPosition = r.nextInt(4);
        switch (shuffledPosition) {
            case 0:
                choice1.setText(questions[colorIndex]);
                choice1.setBackgroundColor(colorsx.getInt(questions[colorIndex], defaultValue));

                choice2.setText(questions[tempPositions[0]]);
                choice2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], defaultValue));
                choice3.setText(questions[tempPositions[1]]);
                choice3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], defaultValue));
                choice4.setText(questions[tempPositions[2]]);
                choice4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], defaultValue));

                break;
            case 1:
                choice2.setText(questions[colorIndex]);
                choice2.setBackgroundColor(colorsx.getInt(questions[colorIndex], defaultValue));

                choice1.setText(questions[tempPositions[0]]);
                choice1.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], defaultValue));
                choice3.setText(questions[tempPositions[1]]);
                choice3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], defaultValue));
                choice4.setText(questions[tempPositions[2]]);
                choice4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], defaultValue));

                break;
            case 2:
                choice3.setText(questions[colorIndex]);
                choice3.setBackgroundColor(colorsx.getInt(questions[colorIndex], defaultValue));

                choice2.setText(questions[tempPositions[0]]);
                choice2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], defaultValue));
                choice1.setText(questions[tempPositions[1]]);
                choice1.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], defaultValue));
                choice4.setText(questions[tempPositions[2]]);
                choice4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], defaultValue));

                break;
            case 3:
                choice4.setText(questions[colorIndex]);
                choice4.setBackgroundColor(colorsx.getInt(questions[colorIndex], defaultValue));

                choice2.setText(questions[tempPositions[0]]);
                choice2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], defaultValue));
                choice3.setText(questions[tempPositions[1]]);
                choice3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], defaultValue));
                choice1.setText(questions[tempPositions[2]]);
                choice1.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], defaultValue));

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
                    updateScore(x);
                    x--;
                    try {
                        Thread.sleep(3);
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
        x = 1000;

        scoreValue += scoreQuotient;
        score.setText("" + scoreValue);

        Random r = new Random();
        index = r.nextInt(7);
        colorIndex = r.nextInt(7);
        ques.setText(questions[index]);

        ques.setTextColor(colorsx.getInt(questions[colorIndex], defaultValue));
        currentColor = questions[colorIndex];
        shufflePositions();
    }

    public void updateScore(int temp) {
        scoreQuotient = temp / 100;
    }

    public void gameOver() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    int temp = scores.getInt(MAD_COLOR_HIGH, 0);
                    highScoreValue = temp;
                    if (scoreValue >= temp) {
                        SharedPreferences.Editor editor = scores.edit();
                        editor.putInt(MAD_COLOR_HIGH, MadColor.scoreValue);
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
                if (currentColor.equals(choice1.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;
            case R.id.choice2:
                if (currentColor.equals(choice2.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;
            case R.id.choice3:
                if (currentColor.equals(choice3.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;
            case R.id.choice4:
                if (currentColor.equals(choice4.getText().toString())) {
                    nextQuestion();
                } else {
                    gameOver();
                }
                break;

        }

    }
}
