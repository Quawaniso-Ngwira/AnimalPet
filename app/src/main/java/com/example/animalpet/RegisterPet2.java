package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterPet2 extends AppCompatActivity {

    TextInputEditText vaccinationstatus,rabiesdue;
    AutoCompleteTextView autoCompleteTextView;
    Button buttonRegisterPet2;
    ImageView backToRegisterPetOne;
    int year, month, day;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet2);

        vaccinationstatus = findViewById(R.id.vaccinationStatus);
        backToRegisterPetOne = findViewById(R.id.backToRegisterPetOne);
        rabiesdue = findViewById(R.id.rabiesDue);
        autoCompleteTextView = findViewById(R.id.petScheme);
        buttonRegisterPet2 = findViewById(R.id.buttonRegisterPet2);
        DB = new DBHelper(this);
        Calendar calendar = Calendar.getInstance();


        String [] option = {
                "Local", "Super","VVIP"
        };
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false );
        autoCompleteTextView.setAdapter(arrayAdapter);


        rabiesdue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterPet2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   rabiesdue.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });

        backToRegisterPetOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPet2.this, RegisterPet.class);
                startActivity(intent);
            }
        });

        buttonRegisterPet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vstatus = vaccinationstatus.getText().toString();
                String rdue = rabiesdue.getText().toString();
                String pscheme = autoCompleteTextView.getText().toString();

                if (vstatus.equals("") || rdue.equals("") || pscheme.equals(""))
                    Toast.makeText(RegisterPet2.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                else {
                    Boolean insert = DB.insertHealthData(vstatus, rdue, pscheme);
                    if (insert) {
                        Toast.makeText(RegisterPet2.this, "success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), VetDetails.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterPet2.this, "Sorry something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}