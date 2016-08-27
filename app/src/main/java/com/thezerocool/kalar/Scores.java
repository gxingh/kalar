package com.thezerocool.kalar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Gagandeep Rangi on 4/29/2016.
 */
public class Scores extends Fragment {

    TextView color, word, madColor, madWord, rushColor, rushWord, godColor, godWord;
    ImageButton back;
    //For Scores:
    SharedPreferences scores;
    final static String COLOR_HIGH = "Color High Score";
    final static String WORD_HIGH = "Word High Score";
    final static String MAD_COLOR_HIGH = "Mad Color High Score";
    final static String MAD_WORD_HIGH = "Mad Word High Score";
    final static String RUSH_COLOR_HIGH = "Rush High Score";
    final static String RUSH_WORD_HIGH = "Rush High Score";
    final static String GOD_COLOR_HIGH = "God Color High Score";
    final static String GOD_WORD_HIGH = "God Word High Score";

    static int defaultValue = 3;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_scores, container, false);

        color = (TextView) v.findViewById(R.id.colorScore);
        word = (TextView) v.findViewById(R.id.wordScore);
        madColor = (TextView) v.findViewById(R.id.madColorScore);
        madWord = (TextView) v.findViewById(R.id.madWordScore);
        rushColor = (TextView) v.findViewById(R.id.rushColorScore);
        rushWord = (TextView) v.findViewById(R.id.rushWordScore);
        godColor = (TextView) v.findViewById(R.id.godColorScore);
        godWord = (TextView) v.findViewById(R.id.godWordScore);
        back = (ImageButton) v.findViewById(R.id.backButton);

        scores = getActivity().getSharedPreferences("Scores", Context.MODE_PRIVATE);
        color.setText("" + scores.getInt(COLOR_HIGH, defaultValue));
        word.setText("" + scores.getInt(WORD_HIGH, defaultValue));
        madColor.setText("" + scores.getInt(MAD_COLOR_HIGH, defaultValue));
        madWord.setText("" + scores.getInt(MAD_WORD_HIGH, defaultValue));
        rushColor.setText("" + scores.getInt(RUSH_COLOR_HIGH, defaultValue));
        rushWord.setText("" + scores.getInt(RUSH_WORD_HIGH, defaultValue));
        godColor.setText("" + scores.getInt(GOD_COLOR_HIGH, defaultValue));
        godWord.setText("" + scores.getInt(GOD_WORD_HIGH, defaultValue));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f;
                FragmentTransaction ft;

                f = new Front();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, f);

                ft.commit();
            }
        });

        return v;
    }
}
