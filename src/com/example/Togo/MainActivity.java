package com.example.Togo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by lilian on 8/15/14.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    Button btnCon;
    Spinner spinner1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //initializing variable
        btnCon=(Button)findViewById(R.id.button);
        btnCon.setOnClickListener(this);

        //initializing spinner
        ArrayList<String> list= new ArrayList<String>();
        list.add("Kenya");
        list.add("Tanzania");
        list.add("Uganda");
        list.add("America");
        list.add("South Africa");
        list.add("Somalia");
        list.add("Australia");
        list.add("China");
        list.add("Eritrea");
        list.add("Rwanda");
        list.add("Sudan");
        list.add("Lesotho");
        list.add("Cameroon");
        list.add("Nigeria");
        list.add("Algeria");

        ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                Intent i=new Intent(getApplicationContext(),TabsActivity.class);
                startActivity(i);
        }
    }
}