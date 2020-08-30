package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class MainActivityOutrasDoencas extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_outras_doencas);

        button1  = (Button) findViewById(R.id.aids);
        button2 = (Button) findViewById(R.id.raiva);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.aids:
                openMainActivityAidsHome();
                break;
            case R.id.raiva:
                openMainActivityRaivaHome();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void openMainActivityAidsHome() {
        Intent intent = new Intent(this, MainActivityAidsHome.class);
        startActivity(intent);
    }

    private void openMainActivityRaivaHome(){
        Intent intent = new Intent(this, MainActivityRaivaHome.class);
        startActivity(intent);
    }


}

