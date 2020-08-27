package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class MainActivityOutrasDoencas extends AppCompatActivity implements View.OnClickListener {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_outras_doencas);

        button1  = (Button) findViewById(R.id.aids);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.aids:
                openMainActivityAidsHome();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void openMainActivityAidsHome() {
        Intent intent = new Intent(this, MainActivityAidsHome.class);
        startActivity(intent);
    }


}

