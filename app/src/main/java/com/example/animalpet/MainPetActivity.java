package com.example.animalpet;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainPetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<model> dataholder;
    private myAdapter adapter;
    private EditText searchbar;
    ImageView backFromMainPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pet);

        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchbar = findViewById(R.id.searchbar);
        backFromMainPet = findViewById(R.id.backFromMainPet);

        backFromMainPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //starting the dashbord activity
                Intent intent = new Intent(MainPetActivity.this, Dashbord.class);
                startActivity(intent);
                //Calling the fragment
                FragmentManager fragementManager = getSupportFragmentManager();
                DashbordFragment fragment = new DashbordFragment();
                fragementManager.beginTransaction().replace(R.id.mainContainerPet, fragment).commit();
                searchbar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                backFromMainPet.setVisibility(View.GONE);
            }
        });

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }

        });

        Cursor cursor = new DBHelper(this).ViewPetData();

        dataholder = new ArrayList<>();

        while (cursor.moveToNext()) {
            model obj = new model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            dataholder.add(obj);
        }

        adapter = new myAdapter(dataholder);
        recyclerView.setAdapter(adapter);

    }

    private void filter(String text) {
        ArrayList<model> filteredList = new ArrayList<>();

        for (model item : dataholder){
            if (item.getPetname().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
}