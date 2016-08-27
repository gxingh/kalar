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
public class About extends Fragment{
    ImageButton back;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedBundle){
        View v = inflater.inflate(R.layout.layout_about, container, false);

        back = (ImageButton) v.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Fragment f = new Front();
                FragmentTransaction ft = getFragmentManager().
                        beginTransaction();
                ft.replace(R.id.fragmentContainer, f);
                ft.commit();
            }
        });

        return v;
    }
}
