package com.example.hp.SneakAlert;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity {

    float x1,x2;
    static final int MIN_DISTANCE = 100;
    ImageButton nav_bar_drawer;
    RelativeLayout activity_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        nav_bar_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation_Drawer(R.animator.enter_from_left,R.animator.exit_to_left);
            }
        });
    }
    void init(){
        nav_bar_drawer = (ImageButton)findViewById(R.id.nav_bar_drawer);
        activity_home = (RelativeLayout)findViewById(R.id.activity_home);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    if(x2>x1) {
                        Navigation_Drawer(R.animator.enter_from_left,R.animator.enter_from_left);
                    }
                    else {
                        Navigation_Drawer(R.animator.exit_to_left,R.animator.exit_to_left);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    void Navigation_Drawer(int enter,int exit){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(enter,exit);
        fragmentTransaction.replace(R.id.fragment_place, new Navigation_Bar());
        fragmentTransaction.commit();
    }
}
