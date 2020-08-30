package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityRaivaHome extends AppCompatActivity  implements View.OnClickListener{

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_raiva_home);

        button1  = (Button) findViewById(R.id.raiva_symptoms);
        button2  = (Button) findViewById(R.id.raiva_preventions);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.raiva_symptoms:
                openMainActivityRaivaSymptoms();
                break;
            case R.id.raiva_preventions:
                openMainActivityRaivaPreventions();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void openMainActivityRaivaSymptoms() {
        Intent intent = new Intent(this,MainActivityRaivaSymptoms.class);
        startActivity(intent);
    }

    private void openMainActivityRaivaPreventions() {
        Intent intent = new Intent(this,MainActivityRaivaPreventions.class);
        startActivity(intent);
    }
}