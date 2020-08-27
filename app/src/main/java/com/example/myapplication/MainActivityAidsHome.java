package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityAidsHome extends AppCompatActivity  implements View.OnClickListener{

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aids_home);

        button1  = (Button) findViewById(R.id.aids_symptoms);
        button2  = (Button) findViewById(R.id.aids_preventions);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.aids_symptoms:
                openMainActivityAidsSymptoms();
                break;
            case R.id.aids_preventions:
                openMainActivityAidsPreventions();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void openMainActivityAidsSymptoms() {
        Intent intent = new Intent(this,MainActivityAidsSymptoms.class);
        startActivity(intent);
    }

    private void openMainActivityAidsPreventions() {
        Intent intent = new Intent(this,MainActivityAidsPreventions.class);
        startActivity(intent);
    }
}