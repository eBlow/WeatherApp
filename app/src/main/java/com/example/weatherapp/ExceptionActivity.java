package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExceptionActivity extends Activity {

    private Button theButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);

        theButton = findViewById(R.id.gotItButton);
        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WeatherAppLocationListener.getCurrentLocation() != null){
                    setResult(0);
                    finish();
                }
            }
        });
    }
}
