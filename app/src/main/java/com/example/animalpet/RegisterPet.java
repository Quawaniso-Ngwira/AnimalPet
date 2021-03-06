package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class RegisterPet extends AppCompatActivity {

    private static final String[] COUNTRIES = new String[]{
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "pakistan", "German", "Australia", "Mexico", "Uganda",
            "Israel", "Brazil", "Comoros", "Egypt", "France", "Malawi","Madagascar", "Mozambique", "South Africa", "Cameroon", "Ivory Cost", "Zambia", "Kenya", "Chad", "Burundi", "Tunisia","Gabon", "Mauritius", "Eswatini", "Djibouti", "Lesotho"
    };

    TextInputEditText petname, petcolor, dateofbirthpet;
    AutoCompleteTextView petorigin;
    TextView showPetHeader;
    RadioButton radioButtonFemale, radioButtonMale;
    Button btnRegisterPet;
    ImageView backFromRegisterPet;
    int year, month, day;
    DBHelper DB;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        petname = findViewById(R.id.petName);
        petcolor = findViewById(R.id.petColor);
        petorigin = findViewById(R.id.petOrigin);
        dateofbirthpet = findViewById(R.id.DateOfBirth);
        autoCompleteTextView = findViewById(R.id.petType);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        btnRegisterPet = findViewById(R.id.btnRegisterPet);
        backFromRegisterPet = findViewById(R.id.backFromRegisterPet);
        Calendar calendar = Calendar.getInstance();
        DB = new DBHelper(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , COUNTRIES);
        petorigin.setAdapter(adapter);

        String[] option = {
                "Dog", "Cat", "Rabbit"
        };
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);


        dateofbirthpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterPet.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateofbirthpet.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });

        backFromRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting the dashbord activity
                Intent intent = new Intent(RegisterPet.this, Dashbord.class);
                startActivity(intent);
                //Calling the fragment
                FragmentManager fragementManager = getSupportFragmentManager();
                DashbordFragment fragment = new DashbordFragment();
                fragementManager.beginTransaction().replace(R.id.mainContainerRegisterFromPet, fragment).commit();
                backFromRegisterPet.setVisibility(View.GONE);
                petname.setVisibility(View.GONE);
                petcolor.setVisibility(View.GONE);
                petorigin.setVisibility(View.GONE);
                radioButtonFemale.setVisibility(View.GONE);
                radioButtonMale.setVisibility(View.GONE);
                autoCompleteTextView.setVisibility(View.GONE);
                btnRegisterPet.setVisibility(View.GONE);
            }
        });

        btnRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pname = petname.getText().toString();
                String pcolor = petcolor.getText().toString();
                String porigin = petorigin.getText().toString();
                String ptype = autoCompleteTextView.getText().toString();



                if (pname.equals("") || pcolor.equals("") || porigin.equals("") || ptype.equals(""))
                    Toast.makeText(RegisterPet.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                else {
                    Boolean checkpet = DB.checkpetname(pname);
                    if (checkpet == false) {
                        Boolean insert = DB.insertPetData(pname, pcolor, porigin, ptype);
                        if (insert == true) {
                            Toast.makeText(RegisterPet.this, "success", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainPetActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterPet.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegisterPet.this, "Pet Already exists", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}