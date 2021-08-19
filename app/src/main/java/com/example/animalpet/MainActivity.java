package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonSignUp;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignUp=(Button)findViewById(R.id.btnSignUp);
        buttonRegister=(Button)findViewById(R.id.btnRegister);

        //setting the onclick methods that are checked after clicking the buttons
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityRegister();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivityLogin();
            }
        });
    }
    //calling the functions declared on the setOnclickListener above
    public void startActivityLogin(){
        Intent intent = new Intent(MainActivity.this, LogIn.class);
        startActivity(intent);
    }
    public void startActivityRegister(){
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

}