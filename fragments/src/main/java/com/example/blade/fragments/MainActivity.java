package com.example.blade.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button toF1Btn;
    Button toF2Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //---get the current display info---
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();

        if (d.getWidth() <= d.getHeight()) {
            Fragment1 fragment1 = new Fragment1();
            // android.R.id.content refers to the content // view of the activity
            fragmentTransaction.replace(android.R.id.content, fragment1);
            toF1Btn =  (Button)this.findViewById(R.id.tof2);

            toF1Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment2 fragment1 = new Fragment2();
                    fragmentTransaction.replace(android.R.id.content, fragment1);
                    fragmentTransaction.commit();
                }
            });
        } else {
            Fragment2 fragment2 = new Fragment2();
            fragmentTransaction.replace(android.R.id.content, fragment2);
            toF2Btn =  (Button)this.findViewById(R.id.tof2);

            toF2Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment2 fragment2 = new Fragment2();
                    fragmentTransaction.replace(android.R.id.content, fragment2);
                    fragmentTransaction.commit();
                }
            });
        }
        fragmentTransaction.commit();





    }
}
