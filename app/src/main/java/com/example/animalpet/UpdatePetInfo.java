package com.example.animalpet;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdatePetInfo extends AppCompatActivity {


    private static final String[] COUNTRIES = new String[]{
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "pakistan", "German", "Australia", "Mexico", "Uganda",
            "Israel", "Brazil", "Comoros", "Egypt", "France", "Malawi", "Madagascar", "Mozambique", "South Africa", "Cameroon", "Ivory Cost", "Zambia", "Kenya", "Chad", "Burundi", "Tunisia", "Gabon", "Mauritius", "Eswatini", "Djibouti", "Lesotho"
    };

    TextInputEditText petname, petcolor, petdate;
    AutoCompleteTextView petorigin,autoCompleteTextView;
    TextView showPetHeader;
    RadioButton radioButtonFemale, radioButtonMale;
    Button btnUpdatePet;
    ImageView backFromRegisterPet;
    int year, month, day;
    DBHelper DB;
    private Object v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pet_info);

        petname = findViewById(R.id.petName);
        petcolor = findViewById(R.id.petColor);
        petorigin = findViewById(R.id.petOrigin);
        petdate = findViewById(R.id.DateOfBirth);
        autoCompleteTextView = findViewById(R.id.petType);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        btnUpdatePet = findViewById(R.id.btnRegisterPet);
        backFromRegisterPet = findViewById(R.id.backFromRegisterPet);
        Calendar calendar = Calendar.getInstance();
        DB = new DBHelper(this);

        //receiving the intents
        String petnamee = getIntent().getExtras().getString("petname");
        String petcolorr = getIntent().getExtras().getString("petcolor");
        String petoriginn = getIntent().getExtras().getString("petorigin");
        String pettypee = getIntent().getExtras().getString("pettype");
        String petdatee = getIntent().getExtras().getString("petdate");
        petname.setText(petnamee);
        petcolor.setText(petcolorr);
        petorigin.setText(petoriginn);
        petdate.setText(petdatee);
        autoCompleteTextView.setText(pettypee);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COUNTRIES);
        petorigin.setAdapter(adapter);

        String[] option = {
                "Dog", "Cat", "Rabbit"
        };
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);


        petdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdatePetInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        petdate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        backFromRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting the dashbord activity
                Intent intent = new Intent(UpdatePetInfo.this, Dashbord.class);
                startActivity(intent);
                //Calling the fragment
                FragmentManager fragementManager = getSupportFragmentManager();
                DashbordFragment fragment = new DashbordFragment();
                fragementManager.beginTransaction().replace(R.id.mainContainerRegisterFromPet, fragment).commit();
                backFromRegisterPet.setVisibility(View.GONE);
                petname.setVisibility(View.GONE);
                petcolor.setVisibility(View.GONE);
                petorigin.setVisibility(View.GONE);
                petdate.setVisibility(View.GONE);
                radioButtonFemale.setVisibility(View.GONE);
                radioButtonMale.setVisibility(View.GONE);
                autoCompleteTextView.setVisibility(View.GONE);
                btnUpdatePet.setVisibility(View.GONE);
            }
        });

        btnUpdatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pname = petname.getText().toString();
                String pcolor = petcolor.getText().toString();
                String porigin = petorigin.getText().toString();
                String pdate = petdate.getText().toString();
                String ptype = autoCompleteTextView.getText().toString();


                if (pname.equals("") || pcolor.equals("") || porigin.equals("") || ptype.equals("") || pdate.equals(""))
                    Toast.makeText(UpdatePetInfo.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                else {
                        Boolean update = DB.updatePetData(pname, pcolor, porigin, ptype, pdate);
                        if (update == true) {
                            Toast.makeText(UpdatePetInfo.this, "Info updated successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainPetActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(UpdatePetInfo.this, "Pet update failed!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        });

      /**  public void updatePetData(View v){
            String pname = petname.getText().toString();
            String pcolor = petcolor.getText().toString();
            String porigin = petorigin.getText().toString();
            String aCompleteTextView = autoCompleteTextView.getText().toString();
            ViewDetailsActivity.DB.updatePetData(pname, pcolor, porigin, aCompleteTextView);
            startActivity(new Intent(UpdatePetInfo.this, ViewDetailsActivity.class));
            finish();
        };
       */

    }

}

