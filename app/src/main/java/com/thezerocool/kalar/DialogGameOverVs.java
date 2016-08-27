package com.thezerocool.kalar;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
public class DialogGameOverVs extends DialogFragment implements View.OnClickListener {
    TextView p1ScoreField, p2ScoreField, p1ScoreValue, p2ScoreValue, result;
    ImageButton again, menu;

    Fragment g = new Color();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_game_over_vs, container, false);
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

        again = (ImageButton) v.findViewById(R.id.again);
        again.setOnClickListener(this);
        menu = (ImageButton) v.findViewById(R.id.menu);
        menu.setOnClickListener(this);

        p1ScoreField = (TextView) v.findViewById(R.id.player1ScoreField);
        p1ScoreValue = (TextView) v.findViewById(R.id.player1ScoreValue);
        p2ScoreField = (TextView) v.findViewById(R.id.player2ScoreField);
        p2ScoreValue = (TextView) v.findViewById(R.id.player2ScoreValue);
        result = (TextView) v.findViewById(R.id.result);

        switch (MainActivity.last) {
            case 8:
                VsColor.progressFlag = false;
                VsColor.t.interrupt();
                g = new VsColor();

                if (VsColor.resultFlag == false) {
                    p1ScoreField.setText("");
                    p1ScoreValue.setText("");
                    p2ScoreField.setText("");
                    p2ScoreValue.setText("");

                    if (VsColor.temp == 0)
                        result.setText("Player 1\n Wins");
                    if (VsColor.temp == 1)
                        result.setText("Player 2\n Wins");

                } else {
                    p1ScoreValue.setText("" + VsColor.p1ScoreValue);
                    p2ScoreValue.setText("" + VsColor.p2ScoreValue);
                    if (VsColor.p1ScoreValue > VsColor.p2ScoreValue)
                        result.setText("Player 1 Wins");
                    if (VsColor.p1ScoreValue < VsColor.p2ScoreValue)
                        result.setText("Player 2 Wins");
                    if (VsColor.p1ScoreValue == VsColor.p2ScoreValue)
                        result.setText("Game Draw");
                }
                break;
            case 9:
                VsWord.progressFlag = false;
                VsWord.t.interrupt();
                g = new VsWord();

                if (VsWord.resultFlag == false) {
                    p1ScoreField.setText("");
                    p1ScoreValue.setText("");
                    p2ScoreField.setText("");
                    p2ScoreValue.setText("");

                    if (VsWord.temp == 0)
                        result.setText("Player 1\n Wins");
                    if (VsWord.temp == 1)
                        result.setText("Player 2\n Wins");

                } else {
                    p1ScoreValue.setText("" + VsWord.p1ScoreValue);
                    p2ScoreValue.setText("" + VsWord.p2ScoreValue);
                    if (VsWord.p1ScoreValue > VsWord.p2ScoreValue)
                        result.setText("Player 1 Wins");
                    if (VsWord.p1ScoreValue < VsWord.p2ScoreValue)
                        result.setText("Player 2 Wins");
                    if (VsWord.p1ScoreValue == VsWord.p2ScoreValue)
                        result.setText("Game Draw");
                }
                break;
        }

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

