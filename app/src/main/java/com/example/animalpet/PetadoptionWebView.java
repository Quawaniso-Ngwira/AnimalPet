/* package com.example.animalpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class PetadoptionWebView extends AppCompatActivity {

    WebView web;
    ImageView backFromMainPetAdoption;
   // TextView showPetHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petadoption_web_view);

        web =findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());
        web.loadUrl("https://www.lilongwespca.org/adoptions");

        backFromMainPetAdoption = findViewById(R.id.backFromMainPetAdoption);
       // showPetHeader = findViewById(R.id.showPetHeader);


        backFromMainPetAdoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //starting the dashbord activity
                Intent intent = new Intent(PetadoptionWebView.this, Dashbord.class);
                startActivity(intent);
                //Calling the fragment
                FragmentManager fragementManager = getSupportFragmentManager();
                DashbordFragment fragment = new DashbordFragment();
                fragementManager.beginTransaction().replace(R.id.mainContainerRegisterFromPetAdoption, fragment).commit();
                web.setVisibility(View.GONE);
//              showPetHeader.setVisibility(View.GONE);
            }
        });
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}


 */