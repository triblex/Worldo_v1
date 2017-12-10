package com.co.triblex.worldo_v01;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class MainMenuActivity extends AppCompatActivity implements OnMenuSelectedListener, OnMenuStatusChangeListener {
    private CircleMenu circleMenu;
    Handler mHandler = new Handler();

    //HELP FROM https://www.youtube.com/watch?v=j1AKD8T1H9E
    // GITHUB USED https://github.com/Hitomis/CircleMenu

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startside);
        initView();
    }

    private void initView() {
        circleMenu = (CircleMenu) findViewById(R.id.cm);
        circleMenu.setMainMenu(Color.BLUE, R.drawable.ic_action_overflow, R.drawable.ic_action_locate);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.drawable.profile);
        circleMenu.addSubMenu(Color.parseColor("#30A400"), R.drawable.meetup);
        circleMenu.addSubMenu(Color.parseColor("#FF4B32"), R.drawable.events);
        circleMenu.addSubMenu(Color.parseColor("#242424"), R.drawable.friends);
        circleMenu.addSubMenu(Color.parseColor("#9B59B6"), R.drawable.tickets);
        circleMenu.addSubMenu(Color.parseColor("#990000"), R.drawable.help);
        circleMenu.setOnMenuSelectedListener(this);
        circleMenu.setOnMenuStatusChangeListener(this);
    }

    @Override
    public void onMenuSelected(int i) {
        switch(i){
            case 0:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(mProfile ,900);
                break;
            case 1:
                Toast.makeText(this, "Send meetup request", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(mMeetup ,900);
                break;
            case 2:
                Toast.makeText(this, "EventsActivity", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(mEvents,900);
                break;
            case 3:
                Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "Flight tickets", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(mFlightTickets,900);
                break;
            case 5:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(mHelp ,900);
                break;
            default:
                Toast.makeText(this, "BRRRRRRAA", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private Runnable mProfile= new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(i);
        }
    };

    private Runnable mFlightTickets= new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(),WebViewActivity.class);
            startActivity(i);
        }
    };

    private Runnable mHelp= new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(),HelpActivity.class);
            startActivity(i);
        }
    };

    private Runnable mEvents= new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(),EventsActivity.class);
            startActivity(i);
        }
    };

    private Runnable mMeetup= new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(),MeetupActivity.class);
            startActivity(i);
        }
    };



    @Override
    public void onMenuOpened() {
        //Toast.makeText(this, "Menu Opened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuClosed() {
        //Toast.makeText(this, "Menu Closed", Toast.LENGTH_SHORT).show();
    }
}
