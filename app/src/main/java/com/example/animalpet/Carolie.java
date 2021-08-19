package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Carolie extends AppCompatActivity {

    ImageView backFromCalorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carolie);

        backFromCalorie = findViewById(R.id.backFromCalorie);

        backFromCalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carolie.this, BioManagements.class);
                startActivity(intent);
            }
        });
    }
}