package com.example.animalpet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewDetailsActivity extends AppCompatActivity {

    static DBHelper DB;
    TextView petnameInViewDetails, petcolorinDetails, petorigininDetails, pettypeinDetails, petdateofbirthDetails,vetnameInViewDetails, pettreatmentsinDetails, petremarksinDetails, petadressinDetails;
    String petnamesummary, petcolorsummary, petoriginsummary, pettypesummary, petdateofbirthsummary, vetnamesummary, vetaddresssummary, vettreatmentsummary, vetremarkssummary;
    ImageView deletePetInfo, editPetInfo, editVetInfo;
    //Button btnRegisterVet;
    FloatingActionButton btnRegisterVet;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        btnRegisterVet = findViewById(R.id.btnRegisterVet);
        petnameInViewDetails = findViewById(R.id.petnameInViewDetails);
        petcolorinDetails = findViewById(R.id.petcolorinDetails);
        petorigininDetails = findViewById(R.id.petorigininDetails);
        pettypeinDetails = findViewById(R.id.pettypeinDetails);
        petdateofbirthDetails = findViewById(R.id.petdateofbirthdetails);
        editVetInfo = findViewById(R.id.editVetInfo);

        vetnameInViewDetails = findViewById(R.id.vetnameInViewDetails);
        pettreatmentsinDetails = findViewById(R.id.pettreatmentsinDetails);
        petremarksinDetails = findViewById(R.id.petremarksinDetails);
        petadressinDetails = findViewById(R.id.petadressinDetails);

        petnamesummary = getIntent().getStringExtra("pname");
        petcolorsummary = getIntent().getStringExtra("pcolor");
        petoriginsummary = getIntent().getStringExtra("porigin");
        pettypesummary = getIntent().getStringExtra("ptype");
        petdateofbirthsummary = getIntent().getStringExtra("pdate");

        vetnamesummary = getIntent().getStringExtra("vtname");
        vetaddresssummary = getIntent().getStringExtra("vtaddress");
        vettreatmentsummary = getIntent().getStringExtra("vttreatments");
        vetremarkssummary = getIntent().getStringExtra("vtremarks");

        petnameInViewDetails.setText(petnamesummary);
        petcolorinDetails.setText(petcolorsummary);
        petorigininDetails.setText(petoriginsummary);
        pettypeinDetails.setText(pettypesummary);
        petdateofbirthDetails.setText(petdateofbirthsummary);

       vetnameInViewDetails.setText(vetnamesummary);
        pettreatmentsinDetails.setText(vettreatmentsummary);
        petremarksinDetails.setText(vettreatmentsummary);
        petadressinDetails.setText(vetaddresssummary);

        deletePetInfo = findViewById(R.id.deletePetInfo);
        editPetInfo = findViewById(R.id.editPetInfo);
        DB = new DBHelper(this);


        btnRegisterVet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String petname = petnameInViewDetails.getText().toString();
                Intent intent = new Intent(getApplicationContext(), VetDetails.class);
                intent.putExtra("petname", petname);
                startActivity(intent);
                finish();
            }
        });

        editVetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get all Intent extras into update section and update using id
                Intent intent = new Intent(ViewDetailsActivity.this, UpdateVet.class);
                startActivity(intent);
            }
        });

        editPetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     Cursor cursor = (Cursor) myAdapter.getItem(position);
                String petname = petnameInViewDetails.getText().toString();
                String petcolor = petcolorinDetails.getText().toString();
                String petorigin = petorigininDetails.getText().toString();
                String pettype = pettypeinDetails.getText().toString();
                String petdate = petdateofbirthDetails.getText().toString();
                Intent intent = new Intent(ViewDetailsActivity.this, UpdatePetInfo.class);
                intent.putExtra("petname", petname);
                intent.putExtra("petcolor", petcolor);
                intent.putExtra("petorigin", petorigin);
                intent.putExtra("pettype", pettype);
                intent.putExtra("petdate", petdate);
                startActivity(intent);
                finish();
            }
        });

        deletePetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Petname = petnameInViewDetails.getText().toString();
                Boolean deletePetData = DB.deletePetInfo(Petname);
                if (deletePetData == true) {
                    Intent intent = new Intent(ViewDetailsActivity.this, MainPetActivity.class);
                    startActivity(intent);
                    Toast.makeText(ViewDetailsActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewDetailsActivity.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}