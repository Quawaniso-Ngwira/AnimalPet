package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateVet extends AppCompatActivity {
    TextInputEditText vetname, address, treatments, vetrecomendation;
    Button buttonVetDetails;
    TextView petnameinVet;
    ImageView backToRegisterPet2;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vet);

        vetname = findViewById(R.id.VetName);
        address = findViewById(R.id.VetAddress);
        treatments = findViewById(R.id.vetTreatments);
        vetrecomendation = findViewById(R.id.VetRemarks);
        buttonVetDetails = findViewById(R.id.buttonVetDetails);
        petnameinVet = findViewById(R.id.petnameinVet);
        backToRegisterPet2 = findViewById(R.id.backToRegisterPet2);
        DB = new DBHelper(this);

   /*     String petname = getIntent().getExtras().getString("petname");
        petnameinVet.setText(petname); */
/*
        //Receiving the intents
        String vetnamee = getIntent().getExtras().getString("petname");
        vetname.setText(vetnamee);
*/
        backToRegisterPet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateVet.this, RegisterPet.class);
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
                String pname = petnameinVet.getText().toString();

                if (vname.equals("") || vaddress.equals("") || vtreatments.equals("") || vrecomendation.equals(""))
                    Toast.makeText(UpdateVet.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                else{
                    Boolean insert = DB.insertVetData(pname,vname, vaddress, vtreatments, vrecomendation);
                    if (insert == true){
                        Toast.makeText(UpdateVet.this, "Vet Info updated", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ViewDetailsActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(UpdateVet.this, "Sorry something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}