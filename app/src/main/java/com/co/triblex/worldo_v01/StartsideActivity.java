package com.co.triblex.worldo_v01;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class StartsideActivity extends AppCompatActivity implements OnMenuSelectedListener, OnMenuStatusChangeListener {
    private CircleMenu circleMenu;

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
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.drawable.ic_action_select_all);
        circleMenu.addSubMenu(Color.parseColor("#30A400"), R.drawable.ic_action_send);
        circleMenu.addSubMenu(Color.parseColor("#FF4B32"), R.drawable.ic_action_user);
        circleMenu.setOnMenuSelectedListener(this);
        circleMenu.setOnMenuStatusChangeListener(this);
    }

    @Override
    public void onMenuSelected(int i) {
        switch(i){
            case 0:
                Toast.makeText(this, "Events", Toast.LENGTH_SHORT).show();
                Intent events = new Intent(this,Events.class);
                startActivity(events);
                break;
            case 1:
                Toast.makeText(this, "Send meetup request", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MeetupActivity.class);
                startActivity(intent);
                break;
            case 2:
                Toast.makeText(this, "Friends Schedule", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "BRRRRRRAA", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onMenuOpened() {
        Toast.makeText(this, "Menu Opened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuClosed() {
        Toast.makeText(this, "Menu Closed", Toast.LENGTH_SHORT).show();
    }
}
