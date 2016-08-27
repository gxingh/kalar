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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Gagandeep Rangi on 4/29/2016.
 */
public class VsColor extends Fragment implements View.OnClickListener {
    Button p1c1, p1c2, p1c3, p1c4,
            p2c1, p2c2, p2c3, p2c4;
    TextView p1score, p2score,
            p1Ques, p2Ques;
    LinearLayout playerOne, playerTwo;
    Dialog dialog;

    String[] questions = {"blue", "red", "green", "yellow", "cyan", "black", "magenta"};
    static String currentColor1 = "blue";
    static String currentColor2 = "blue";
    static int p1ScoreValue, p2ScoreValue;
    static int index1, index2,
            colorIndex1, colorIndex2;

    //For Progress bar:
    ProgressBar progressBar;
    static int x = 1000;
    static Thread t;
    static boolean progressFlag = true;
    static boolean resultFlag = false;//false->GameOver, true->GameResult
    static int temp = 0;

    Handler handler;
    static SharedPreferences colorsx;

    //For shuffling answer positions:
    static int shuffledPosition;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_color_vs, viewGroup, false);

        MainActivity.last = 8;
        handler = new Handler(Looper.getMainLooper());

        p1c1 = (Button) v.findViewById(R.id.player1choice1);
        p1c2 = (Button) v.findViewById(R.id.player1choice2);
        p1c3 = (Button) v.findViewById(R.id.player1choice3);
        p1c4 = (Button) v.findViewById(R.id.player1choice4);
        p2c1 = (Button) v.findViewById(R.id.player2choice1);
        p2c2 = (Button) v.findViewById(R.id.player2choice2);
        p2c3 = (Button) v.findViewById(R.id.player2choice3);
        p2c4 = (Button) v.findViewById(R.id.player2choice4);
        p1score = (TextView) v.findViewById(R.id.player1score);
        p2score = (TextView) v.findViewById(R.id.player2score);
        p1Ques = (TextView) v.findViewById(R.id.player1Ques);
        p2Ques = (TextView) v.findViewById(R.id.player2Ques);
        playerOne = (LinearLayout) v.findViewById(R.id.playerOne);
        playerTwo = (LinearLayout) v.findViewById(R.id.playerTwo);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);

        p1c1.setOnClickListener(this);
        p1c2.setOnClickListener(this);
        p1c3.setOnClickListener(this);
        p1c4.setOnClickListener(this);
        p2c1.setOnClickListener(this);
        p2c2.setOnClickListener(this);
        p2c3.setOnClickListener(this);
        p2c4.setOnClickListener(this);
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

        if (savedInstanceState != null) {
            p1ScoreValue = savedInstanceState.getInt("score1");
            p2ScoreValue = savedInstanceState.getInt("score2");
        } else {
            p1ScoreValue = p2ScoreValue = 0;
        }
        p1score.setText("" + p1ScoreValue);
        p2score.setText("" + p2ScoreValue);

        Random r = new Random();
        index1 = r.nextInt(7);
        colorIndex1 = r.nextInt(7);
        index2 = r.nextInt(7);
        colorIndex2 = r.nextInt(7);

        p1Ques.setText(questions[index1]);
        p1Ques.setTextColor(colorsx.getInt(questions[colorIndex1], 0));
        currentColor1 = questions[colorIndex1];
        p2Ques.setText(questions[index2]);
        p2Ques.setTextColor(colorsx.getInt(questions[colorIndex2], 0));
        currentColor2 = questions[colorIndex2];

        shufflePositions1();
        shufflePositions2();

        progressFlag = true;
        x = 1000;
        updateProgressBar();

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score1", p1ScoreValue);
        outState.putInt("score2", p2ScoreValue);
        progressFlag = false;
    }

    private void shufflePositions1() {
        int[] tempPositions = new int[3];

        Random r = new Random();
        tempPositions[0] = r.nextInt(7);
        while (tempPositions[0] == colorIndex1) {
            tempPositions[0] = r.nextInt(7);
        }
        tempPositions[1] = r.nextInt(7);
        while (tempPositions[1] == colorIndex1 || tempPositions[1] == tempPositions[0]) {
            tempPositions[1] = r.nextInt(7);
        }
        tempPositions[2] = r.nextInt(7);
        while (tempPositions[2] == colorIndex1 || tempPositions[2] == tempPositions[1] || tempPositions[2] == tempPositions[0]) {
            tempPositions[2] = r.nextInt(7);
        }

        //Shuffle positions:
        shuffledPosition = r.nextInt(4);
        switch (shuffledPosition) {
            case 0:
                p1c1.setText(questions[colorIndex1]);
                p1c1.setBackgroundColor(colorsx.getInt(questions[colorIndex1], 0));

                p1c2.setText(questions[tempPositions[0]]);
                p1c2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p1c3.setText(questions[tempPositions[1]]);
                p1c3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p1c4.setText(questions[tempPositions[2]]);
                p1c4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
            case 1:
                p1c2.setText(questions[colorIndex1]);
                p1c2.setBackgroundColor(colorsx.getInt(questions[colorIndex1], 0));

                p1c1.setText(questions[tempPositions[0]]);
                p1c1.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p1c3.setText(questions[tempPositions[1]]);
                p1c3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p1c4.setText(questions[tempPositions[2]]);
                p1c4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
            case 2:
                p1c3.setText(questions[colorIndex1]);
                p1c3.setBackgroundColor(colorsx.getInt(questions[colorIndex1], 0));

                p1c2.setText(questions[tempPositions[0]]);
                p1c2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p1c1.setText(questions[tempPositions[1]]);
                p1c1.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p1c4.setText(questions[tempPositions[2]]);
                p1c4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
            case 3:
                p1c4.setText(questions[colorIndex1]);
                p1c4.setBackgroundColor(colorsx.getInt(questions[colorIndex1], 0));

                p1c2.setText(questions[tempPositions[0]]);
                p1c2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p1c3.setText(questions[tempPositions[1]]);
                p1c3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p1c1.setText(questions[tempPositions[2]]);
                p1c1.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
        }
    }


    private void shufflePositions2() {
        int[] tempPositions = new int[3];

        Random r = new Random();
        tempPositions[0] = r.nextInt(7);
        while (tempPositions[0] == colorIndex2) {
            tempPositions[0] = r.nextInt(7);
        }
        tempPositions[1] = r.nextInt(7);
        while (tempPositions[1] == colorIndex2 || tempPositions[1] == tempPositions[0]) {
            tempPositions[1] = r.nextInt(7);
        }
        tempPositions[2] = r.nextInt(7);
        while (tempPositions[2] == colorIndex2 || tempPositions[2] == tempPositions[1] || tempPositions[2] == tempPositions[0]) {
            tempPositions[2] = r.nextInt(7);
        }

        //Shuffle positions:
        shuffledPosition = r.nextInt(4);
        switch (shuffledPosition) {
            case 0:
                p2c1.setText(questions[colorIndex2]);
                p2c1.setBackgroundColor(colorsx.getInt(questions[colorIndex2], 0));

                p2c2.setText(questions[tempPositions[0]]);
                p2c2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p2c3.setText(questions[tempPositions[1]]);
                p2c3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p2c4.setText(questions[tempPositions[2]]);
                p2c4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
            case 1:
                p2c2.setText(questions[colorIndex2]);
                p2c2.setBackgroundColor(colorsx.getInt(questions[colorIndex2], 0));

                p2c1.setText(questions[tempPositions[0]]);
                p2c1.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p2c3.setText(questions[tempPositions[1]]);
                p2c3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p2c4.setText(questions[tempPositions[2]]);
                p2c4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
            case 2:
                p2c3.setText(questions[colorIndex2]);
                p2c3.setBackgroundColor(colorsx.getInt(questions[colorIndex2], 0));

                p2c2.setText(questions[tempPositions[0]]);
                p2c2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p2c1.setText(questions[tempPositions[1]]);
                p2c1.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p2c4.setText(questions[tempPositions[2]]);
                p2c4.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
            case 3:
                p2c4.setText(questions[colorIndex2]);
                p2c4.setBackgroundColor(colorsx.getInt(questions[colorIndex2], 0));

                p2c2.setText(questions[tempPositions[0]]);
                p2c2.setBackgroundColor(colorsx.getInt(questions[tempPositions[0]], 0));
                p2c3.setText(questions[tempPositions[1]]);
                p2c3.setBackgroundColor(colorsx.getInt(questions[tempPositions[1]], 0));
                p2c1.setText(questions[tempPositions[2]]);
                p2c1.setBackgroundColor(colorsx.getInt(questions[tempPositions[2]], 0));

                break;
        }
    }


    public void gameOver(int getResult) {
        temp = getResult;
        progressFlag = false;
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    resultFlag = false;

                    if (temp == 0) {
                        playerOne.setBackgroundColor(getResources().getColor(R.color.winBackground));
                        playerTwo.setBackgroundColor(getResources().getColor(R.color.looseBackground));
                    }
                    if (temp == 1) {
                        playerOne.setBackgroundColor(getResources().getColor(R.color.looseBackground));
                        playerTwo.setBackgroundColor(getResources().getColor(R.color.winBackground));
                    }
                    DialogGameOverVs dialog = new DialogGameOverVs();
                    dialog.show(getFragmentManager(), "show");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void nextQuestionPlayer1() {
        progressFlag = true;

        p1ScoreValue++;
        p1score.setText("" + p1ScoreValue);

        Random r = new Random();
        index1 = r.nextInt(7);
        colorIndex1 = r.nextInt(7);
        p1Ques.setText(questions[index1]);
        p1Ques.setTextColor(colorsx.getInt(questions[colorIndex1], 0));
        currentColor1 = questions[colorIndex1];
        shufflePositions1();
    }

    public void nextQuestionPlayer2() {
        progressFlag = true;

        p2ScoreValue++;
        p2score.setText("" + p2ScoreValue);

        Random r = new Random();
        index2 = r.nextInt(7);
        colorIndex2 = r.nextInt(7);
        p2Ques.setText(questions[index2]);
        p2Ques.setTextColor(colorsx.getInt(questions[colorIndex2], 0));
        currentColor2 = questions[colorIndex2];
        shufflePositions2();
    }

    public void updateProgressBar() {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressFlag) {
                    if (x == 0) {
                        progressFlag = false;
                        result();
                    }

                    progressBar.setProgress(x);
                    x--;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void result() {
        progressFlag = false;
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    resultFlag = true;

                    if (p1ScoreValue > p2ScoreValue){
                        playerOne.setBackgroundColor(getResources().getColor(R.color.winBackground));
                        playerTwo.setBackgroundColor(getResources().getColor(R.color.looseBackground));
                    }
                    if (p1ScoreValue < p2ScoreValue){
                        playerOne.setBackgroundColor(getResources().getColor(R.color.looseBackground));
                        playerTwo.setBackgroundColor(getResources().getColor(R.color.winBackground));
                    }
                    if (p1ScoreValue == p2ScoreValue){
                        playerOne.setBackgroundColor(getResources().getColor(R.color.winBackground));
                        playerTwo.setBackgroundColor(getResources().getColor(R.color.winBackground));
                    }

                    DialogGameOverVs dialog = new DialogGameOverVs();
                    dialog.show(getFragmentManager(), "show");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        //0: Player 1; 1: Player2
        switch (v.getId()) {
            case R.id.player1choice1:
                if (currentColor1.equals(p1c1.getText().toString()))
                    nextQuestionPlayer1();
                else
                    gameOver(1);
                break;
            case R.id.player1choice2:
                if (currentColor1.equals(p1c2.getText().toString()))
                    nextQuestionPlayer1();
                else
                    gameOver(1);
                break;
            case R.id.player1choice3:
                if (currentColor1.equals(p1c3.getText().toString()))
                    nextQuestionPlayer1();
                else
                    gameOver(1);
                break;
            case R.id.player1choice4:
                if (currentColor1.equals(p1c4.getText().toString()))
                    nextQuestionPlayer1();
                else
                    gameOver(1);
                break;
            case R.id.player2choice1:
                if (currentColor2.equals(p2c1.getText().toString()))
                    nextQuestionPlayer2();
                else
                    gameOver(0);
                break;
            case R.id.player2choice2:
                if (currentColor2.equals(p2c2.getText().toString()))
                    nextQuestionPlayer2();
                else
                    gameOver(0);
                break;
            case R.id.player2choice3:
                if (currentColor2.equals(p2c3.getText().toString()))
                    nextQuestionPlayer2();
                else
                    gameOver(0);
                break;
            case R.id.player2choice4:
                if (currentColor2.equals(p2c4.getText().toString()))
                    nextQuestionPlayer2();
                else
                    gameOver(0);
                break;
        }

    }
}
