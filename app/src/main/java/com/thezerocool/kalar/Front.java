package com.thezerocool.kalar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Gagandeep Rangi on 4/19/2016.
 */
public class Front extends Fragment implements View.OnClickListener {

    ImageButton colorButton, wordButton, colorMadButton, wordMadButton,
            colorRushButton, wordRushButton, colorVsButton, wordVsButton,
            colorGodButton, wordGodButton, scoresButton, aboutButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_front, container, false);

        colorButton = (ImageButton) v.findViewById(R.id.colorImageButton);
        wordButton = (ImageButton) v.findViewById(R.id.wordImageButton);
        colorMadButton = (ImageButton) v.findViewById(R.id.madColorImageButton);
        wordMadButton = (ImageButton) v.findViewById(R.id.madWordImageButton);
        colorRushButton = (ImageButton) v.findViewById(R.id.rushColorImageButton);
        wordRushButton = (ImageButton) v.findViewById(R.id.rushWordImageButton);
        colorVsButton = (ImageButton) v.findViewById(R.id.vsColorImageButton);
        wordVsButton = (ImageButton) v.findViewById(R.id.vsWordImageButton);
        colorGodButton = (ImageButton) v.findViewById(R.id.godColorImageButton);
        wordGodButton = (ImageButton) v.findViewById(R.id.godWordImageButton);

        scoresButton = (ImageButton) v.findViewById(R.id.scoresImageButton);
        aboutButton = (ImageButton) v.findViewById(R.id.aboutImageButton);

        colorButton.setOnClickListener(this);
        wordButton.setOnClickListener(this);
        colorMadButton.setOnClickListener(this);
        wordMadButton.setOnClickListener(this);
        colorRushButton.setOnClickListener(this);
        wordRushButton.setOnClickListener(this);
        colorVsButton.setOnClickListener(this);
        wordVsButton.setOnClickListener(this);
        colorGodButton.setOnClickListener(this);
        wordGodButton.setOnClickListener(this);
        scoresButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);

        getFragmentManager().popBackStack();
        MainActivity.lastCount=0;

        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        FragmentTransaction ft;
        switch (v.getId()) {
            case R.id.colorImageButton:
                fragment = new Color();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.wordImageButton:
                fragment = new Word();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.madColorImageButton:
                fragment = new MadColor();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.madWordImageButton:
                fragment = new MadWord();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.rushColorImageButton:
                fragment = new RushColor();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.rushWordImageButton:
                fragment = new RushWord();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.vsColorImageButton:
                fragment = new VsColor();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.vsWordImageButton:
                fragment = new VsWord();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.godColorImageButton:
                fragment = new GodColor();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.godWordImageButton:
                fragment = new GodWord();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.scoresImageButton:
                fragment = new Scores();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
            case R.id.aboutImageButton:
                fragment = new About();
                ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                MainActivity.lastCount=1;
                ft.commit();
                break;
        }

    }
}
