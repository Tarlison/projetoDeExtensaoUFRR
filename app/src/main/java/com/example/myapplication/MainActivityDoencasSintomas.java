package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class MainActivityDoencasSintomas extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doencas_sintomas);

        button1  = (Button) findViewById(R.id.covid19);

        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.covid19:
                openMainActivityCovid19();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void openMainActivityCovid19() {
        Intent intent = new Intent(this,MainActivityCovid19.class);
        startActivity(intent);
    }
}

