package com.thezerocool.kalar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends FragmentActivity {
    static int last = 0;
    static int lastCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setFlags();
        getFragmentManager().popBackStack();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new Front();
        fm.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    private void setFlags() {

        switch (last) {
            case 1:
                Color.progressFlag = false;
                Color.t.interrupt();
                break;
            case 2:
                GodColor.progressFlag = false;
                GodColor.t.interrupt();
                break;
            case 3:
                GodWord.progressFlag = false;
                GodWord.t.interrupt();
                break;
            case 4:
                MadColor.progressFlag = false;
                MadColor.t.interrupt();
                break;
            case 5:
                MadWord.progressFlag = false;
                MadWord.t.interrupt();
                break;
            case 6:
                RushColor.progressFlag = false;
                RushColor.t.interrupt();
                break;
            case 7:
                RushWord.progressFlag = false;
                RushWord.t.interrupt();
                break;
            case 8:
                VsColor.progressFlag = false;
                VsColor.t.interrupt();
                break;
            case 9:
                VsWord.progressFlag = false;
                VsWord.t.interrupt();
                break;
            case 10:
                Word.progressFlag = false;
                Word.t.interrupt();
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        setFlags();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setFlags();
        getFragmentManager().popBackStack();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new Front();
        fm.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        setFlags();

        if(lastCount==1){
            getFragmentManager().popBackStack();
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new Front();
            fm.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
        }else{
            super.onBackPressed();
        }
    }
}

