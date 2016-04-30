package com.example.blade.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    Button toF2Btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);

        /*toF2Btn =  (Button)getView().findViewById(R.id.tof2);

        toF2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment2 fragment2 = new Fragment2();
                fragmentTransaction.replace(android.R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });*/
        //---Inflate the layout for this fragment---
        return inflater.inflate( R.layout.fragment1, container, false);

    }

}