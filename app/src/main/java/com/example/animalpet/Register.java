package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username, password, repassword;
    Button btnSignUp, btnSignIn;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        DB = new DBHelper(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LogIn.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                    else{
                        if (pass.equals(repass)){
                            Boolean checkuser = DB.checkusername(user);
                            if (checkuser == false){
                                Boolean insert = DB.insertUserData(user, pass);
                                if (insert == true){
                                    Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), LogIn.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(Register.this, "User Already exists", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(Register.this, "Password not correct", Toast.LENGTH_SHORT).show();
                        }
                }



            }
        });
    }
}