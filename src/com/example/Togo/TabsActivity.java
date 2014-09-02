package com.example.Togo;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by lilian on 8/15/14.
 */
public class TabsActivity extends TabActivity {

    @Override
    public void  onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        //instantiating the tab host
        Resources resources=getResources();
        TabHost tabhost=getTabHost();


        Intent friendsActivity = new Intent().setClass(getApplicationContext(), Friends.class);
        TabHost.TabSpec hometabspec = tabhost.newTabSpec("Friends").setIndicator("Friends", resources.getDrawable(R.drawable.ic_launcher))//setting UI for a specific tab
                .setContent(friendsActivity);

        Intent chatActivity = new Intent().setClass(getApplicationContext(), Chat.class);
        TabHost.TabSpec chattabspec = tabhost.newTabSpec("Chat").setIndicator("Chat", resources.getDrawable(R.drawable.ic_launcher))//setting UI for a specific tab
                .setContent(chatActivity);


        Intent roomsActivity = new Intent().setClass(getApplicationContext(), Rooms.class);
        TabHost.TabSpec roomstabspec = tabhost.newTabSpec("Home").setIndicator("Home", resources.getDrawable(R.drawable.ic_launcher))//setting UI for a specific tab
                .setContent(roomsActivity);


    }
}