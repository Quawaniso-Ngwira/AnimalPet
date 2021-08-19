package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDetailsActivity extends AppCompatActivity {

    TextView petnameInViewDetails, petcolorinDetails, petorigininDetails, pettypeinDetails;
    String petnamesummary, petcolorsummary, petoriginsummary, pettypesummary;
    ImageView deletePetInfo, editPetInfo;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        petnameInViewDetails = findViewById(R.id.petnameInViewDetails);
        petcolorinDetails = findViewById(R.id.petcolorinDetails);
        petorigininDetails = findViewById(R.id.petorigininDetails);
        pettypeinDetails = findViewById(R.id.pettypeinDetails);
        petnamesummary = getIntent().getStringExtra("pname");
        petcolorsummary = getIntent().getStringExtra("pcolor");
        petoriginsummary = getIntent().getStringExtra("porigin");
        pettypesummary = getIntent().getStringExtra("ptype");
        petnameInViewDetails.setText(petnamesummary);
        petcolorinDetails.setText(petcolorsummary);
        petorigininDetails.setText(petoriginsummary);
        pettypeinDetails.setText(pettypesummary);
        deletePetInfo = findViewById(R.id.deletePetInfo);
        editPetInfo = findViewById(R.id.editPetInfo);
        DB = new DBHelper(this);

        deletePetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Petname = petnameInViewDetails.getText().toString();
                Boolean deletePetData = DB.deletePetInfo(Petname);
                if (deletePetData == true){
                    Intent intent = new Intent(ViewDetailsActivity.this, MainPetActivity.class);
                startActivity(intent);
                Toast.makeText(ViewDetailsActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show(); }
                else {
                    Toast.makeText(ViewDetailsActivity.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}