package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class BioManagements extends AppCompatActivity {

    CardView bodyMI, cardViewCalorie;
    Animation topAnim, bottomAnim;
    ImageView backFromBioManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_managements);

        bodyMI = (CardView)findViewById(R.id.cardViewBMI);
        cardViewCalorie = (CardView)findViewById(R.id.cardViewCalorie);
        backFromBioManagement = (ImageView)findViewById(R.id.backFromBioManagement);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


        bodyMI.setAnimation(bottomAnim);
        cardViewCalorie.setAnimation(topAnim);

        backFromBioManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //starting the dashbord activity
                Intent intent = new Intent(BioManagements.this, Dashbord.class);
                startActivity(intent);
                //Calling the fragment
                FragmentManager fragementManager = getSupportFragmentManager();
                DashbordFragment fragment = new DashbordFragment();
                fragementManager.beginTransaction().replace(R.id.mainContainer, fragment).commit();
                backFromBioManagement.setVisibility(View.GONE);
                cardViewCalorie.setVisibility(View.GONE);
                bodyMI.setVisibility(View.GONE);
            }
        });

        cardViewCalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BioManagements.this, Carolie.class);
                startActivity(intent);
            }
        });

        bodyMI.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(BioManagements.this, BodyMassIndex.class);
                 startActivity(intent);
}
    });
}
}














