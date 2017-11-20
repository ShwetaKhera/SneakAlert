package com.example.hp.SneakAlert;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shweta on 08-07-2017.
 */

public class Navigation_Bar extends Fragment {

    View v;
    TextView home,settings,privacy,about_us;
    static TextView profile_name;
    static RoundedImageView profile_image;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstaneState){

        v = inflater.inflate(R.layout.navigation_bar,container,false);

        init();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),HomeActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
    void init(){
        home             = (TextView)           v.findViewById(R.id.home);
        settings         = (TextView)           v.findViewById(R.id.settings);
        privacy          = (TextView)           v.findViewById(R.id.privacy);
        about_us         = (TextView)           v.findViewById(R.id.about_us);
        profile_name     = (TextView)           v.findViewById(R.id.profile_name);
        profile_image    = (RoundedImageView)   v.findViewById(R.id.profile_image);
    }


}
