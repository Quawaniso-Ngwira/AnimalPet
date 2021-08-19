package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ResetPassWord extends AppCompatActivity {
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_word);

        backArrow=(ImageView)findViewById(R.id.resetPassWordBack);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityBackFromRecoverPassword();
            }
        });
    }

    public void startActivityBackFromRecoverPassword(){
        Intent intent = new Intent(ResetPassWord.this, LogIn.class);
        startActivity(intent);
    }
}