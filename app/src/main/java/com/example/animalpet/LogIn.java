package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    TextView TxtRegisterFromSignIn, TxtResetPassword;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        TxtRegisterFromSignIn = (TextView) findViewById(R.id.TxtRegisterFromSignIn);
        TxtResetPassword = (TextView) findViewById(R.id.TxtResetPassword);
        DB = new DBHelper(this);


        TxtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, ResetPassWord.class);
                startActivity(intent);
            }
        });

        TxtRegisterFromSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LogIn.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass) {
                        Toast.makeText(LogIn.this, "Sign up successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Dashbord.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LogIn.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

    }
}