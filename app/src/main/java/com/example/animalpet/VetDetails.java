
package com.example.animalpet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class VetDetails extends AppCompatActivity {

    TextInputEditText vetname, address, treatments, vetrecomendation;
    Button buttonVetDetails;
    ImageView backToRegisterPet2;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_details);

        vetname = findViewById(R.id.VetName);
        address = findViewById(R.id.VetAddress);
        treatments = findViewById(R.id.vetTreatments);
        vetrecomendation = findViewById(R.id.VetRemarks);
        buttonVetDetails = findViewById(R.id.buttonVetDetails);
        backToRegisterPet2 = findViewById(R.id.backToRegisterPet2);
        DB = new DBHelper(this);

        backToRegisterPet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VetDetails.this, RegisterPet2.class);
                startActivity(intent);
            }
        });

        buttonVetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vname = vetname.getText().toString();
                String vaddress = address.getText().toString();
                String vtreatments = treatments.getText().toString();
                String vrecomendation =vetrecomendation.getText().toString();

                if (vname.equals("") || vaddress.equals("") || vtreatments.equals("") || vrecomendation.equals(""))
                    Toast.makeText(VetDetails.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                else{
                    Boolean insert = DB.insertVetData(vname, vaddress, vtreatments, vrecomendation);
                    if (insert == true){
                        Toast.makeText(VetDetails.this, "Pet registered successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainPetActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(VetDetails.this, "Sorry something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}