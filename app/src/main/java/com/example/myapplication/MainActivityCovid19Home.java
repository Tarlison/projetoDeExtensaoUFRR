package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityCovid19Home extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_covid19);

        button1  = (Button) findViewById(R.id.covid19_symptoms);
        button2  = (Button) findViewById(R.id.covid19_preventions);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.covid19_symptoms:
                openMainActivityCovid19Symptoms();
                break;
            case R.id.covid19_preventions:
                openMainActivityCovid19Preventions();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void openMainActivityCovid19Symptoms() {
        Intent intent = new Intent(this,MainActivityCovid19Symptoms.class);
        startActivity(intent);
    }

    private void openMainActivityCovid19Preventions() {
        Intent intent = new Intent(this,MainActivityCovid19Preventions.class);
        startActivity(intent);
    }
}