package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.LineNumberInputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HealthAndStatus extends AppCompatActivity {
    private TextView textViewResults;
    ImageView backFromPetAdoption;
    TextView text_view_result,showPetHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_and_status);

        textViewResults = (TextView)findViewById(R.id.text_view_result);
        backFromPetAdoption = findViewById(R.id.backFromPetAdoption);
        showPetHeader = findViewById(R.id.showPetHeader);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    textViewResults.setText("code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n";

                    textViewResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            textViewResults.setText(t.getMessage());
            }
        });

        backFromPetAdoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //starting the dashbord activity
                Intent intent = new Intent(HealthAndStatus.this, Dashbord.class);
                startActivity(intent);
                //Calling the fragment
                FragmentManager fragementManager = getSupportFragmentManager();
                DashbordFragment fragment = new DashbordFragment();
                fragementManager.beginTransaction().replace(R.id.mainContainerFromHealthandStatus, fragment).commit();
                textViewResults.setVisibility(View.GONE);
                showPetHeader.setVisibility(View.GONE);

            }
        });
    }
}