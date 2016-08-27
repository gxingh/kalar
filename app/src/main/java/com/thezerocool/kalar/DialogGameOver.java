package com.thezerocool.kalar;

import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Gagandeep Rangi on 5/27/2016.
 */
public class DialogGameOver extends DialogFragment implements View.OnClickListener{
    int scoreValue, highScoreValue;
    TextView score, highScore;
    ImageButton again, menu;

    Fragment g = new Color();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_game_over, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Drawable d = new ColorDrawable(android.graphics.Color.TRANSPARENT);
        getDialog().getWindow().setBackgroundDrawable(d);
        getDialog().setCanceledOnTouchOutside(false);

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment f = new Front();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentContainer, f);
                    ft.commit();
                    dismiss();
                }
                return true;
            }
        });

        switch (MainActivity.last) {
            case 1:
                Color.progressFlag = false;
                Color.t.interrupt();
                g = new Color();
                scoreValue = Color.scoreValue;
                highScoreValue = Color.highScoreValue;
                break;
            case 2:
                GodColor.progressFlag = false;
                GodColor.t.interrupt();
                g = new GodColor();
                scoreValue = GodColor.scoreValue;
                highScoreValue = GodColor.highScoreValue;
                break;
            case 3:
                GodWord.progressFlag = false;
                GodWord.t.interrupt();
                g = new GodWord();
                scoreValue = GodWord.scoreValue;
                highScoreValue = GodWord.highScoreValue;
                break;
            case 4:
                MadColor.progressFlag = false;
                MadColor.t.interrupt();
                g = new MadColor();
                scoreValue = MadColor.scoreValue;
                highScoreValue = MadColor.highScoreValue;
                break;
            case 5:
                MadWord.progressFlag = false;
                MadWord.t.interrupt();
                g = new MadWord();
                scoreValue = MadWord.scoreValue;
                highScoreValue = MadWord.highScoreValue;
                break;
            case 6:
                RushColor.progressFlag = false;
                RushColor.t.interrupt();
                g = new RushColor();
                scoreValue = RushColor.scoreValue;
                highScoreValue = RushColor.highScoreValue;
                break;
            case 7:
                RushWord.progressFlag = false;
                RushWord.t.interrupt();
                g = new RushWord();
                scoreValue = RushWord.scoreValue;
                highScoreValue = RushWord.highScoreValue;
                break;
            case 8:
                VsColor.progressFlag = false;
                VsColor.t.interrupt();
                g = new VsColor();
                break;
            case 9:
                VsWord.progressFlag = false;
                VsWord.t.interrupt();
                g = new VsWord();
                break;
            case 10:
                Word.progressFlag = false;
                Word.t.interrupt();
                g = new Word();
                scoreValue = Word.scoreValue;
                highScoreValue = Word.highScoreValue;
                break;
        }

        again = (ImageButton) v.findViewById(R.id.again);
        again.setOnClickListener(this);
        menu = (ImageButton) v.findViewById(R.id.menu);
        menu.setOnClickListener(this);

        score = (TextView) v.findViewById(R.id.score);
        highScore = (TextView) v.findViewById(R.id.highScore);

        score.setText("" + scoreValue);
        highScore.setText("" + highScoreValue);

        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment f;
        FragmentTransaction ft;
        switch (v.getId()) {
            case R.id.again:
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, g);
                ft.commit();
                dismiss();
                break;
            case R.id.menu:
                f = new Front();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, f);
                ft.commit();
                dismiss();
                break;
        }
    }


}
