package com.example.animalpet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Dashbord extends AppCompatActivity {

    ImageView imageView,imageView3;
    AlertDialog.Builder builder;
    CardView cardView,cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        imageView=(ImageView)findViewById(R.id.imageLogout);
        builder = new AlertDialog.Builder(this);
        cardView=(CardView)findViewById(R.id.cardView);
        cardView2=(CardView)findViewById(R.id.cardView2);

       final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

       findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               drawerLayout.openDrawer(GravityCompat.START);
           }
       });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        final TextView textTitle = findViewById(R.id.textTitle);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            textTitle.setText(destination.getLabel());
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Dashbord.this, LogIn.class);
                                startActivity(intent);
                                finish();
                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                 AlertDialog alert = builder.create();
                 alert.setTitle("Logout");
                 alert.show();
            }
        });
    }
}