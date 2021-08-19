package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class BodyMassIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_mass_index);

        TextInputEditText PetWeight, PetHeight;
        TextView Result, bmiValueAmount, bmiSuggestion;
        Button btnReset, btnResult;
        ImageView backFromBMI;

        PetWeight= findViewById(R.id.PetWeight);
        PetHeight= findViewById(R.id.PetHeight);
        backFromBMI= findViewById(R.id.backFromBMI);

        Result= findViewById(R.id.Result);
        bmiValueAmount= findViewById(R.id.bmiValueAmount);
        bmiSuggestion= findViewById(R.id.bmiSuggestion);

        btnReset= findViewById(R.id.btnReset);
        btnResult= findViewById(R.id.btnResult);

        backFromBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BodyMassIndex.this, BioManagements.class);
                startActivity(intent);
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strwei = PetWeight.getText().toString();
                String strhei = PetHeight.getText().toString();

                if (strwei.equals("")){
                    PetWeight.setError("Please Enter Your Weight");
                    PetWeight.requestFocus();
                    return;
                }
                if (strhei.equals("")){
                    PetHeight.setError("Please Enter Your Height");
                    PetHeight.requestFocus();
                    return;
                }
                float weight = Float.parseFloat(strwei);
                float height = Float.parseFloat(strhei)/100;

                float bmiValue = BMICalculator(weight,height);

                Result.setText(InterpreteBMI(bmiValue));
                bmiValueAmount.setText("BMI="+bmiValue);
                bmiSuggestion.setText(SugestionBMI(bmiValue));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            PetWeight.setText("");
            PetHeight.setText("");
            Result.setText("");
            bmiValueAmount.setText("");
            bmiSuggestion.setText("");
            }
        });
    }
    public float BMICalculator(float weight, float height){
        return weight/(height*height);
    }
    public String InterpreteBMI(float bmiValue){
        if(bmiValue < 16){
            return "Servery Underweight";
        }
        if(bmiValue < 18.5){
            return "Underweight";
        }
        if(bmiValue < 25){
            return "normal";
        }
        else if(bmiValue < 30){
            return "Overweight";
        }
        else
            return "Obese";
    }

    public String SugestionBMI(float bmiValue){
        if(bmiValue < 16){
            return "Servery Underweight thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    " thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg";
        }
        if(bmiValue < 18.5){
            return "Increase the foods rich in Proteins thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg";
        }
        if(bmiValue < 25){
            return "Exercises should be done regularly thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg";
        }
        else if(bmiValue < 30){
            return "Please consult experts doctors for medication" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg";
        }
        else
            return "Anaemia one of the possible causes thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg" +
                    "thg dgers gfesdf gfedh myutyfhg rhgrtdg gertg";
    }
}