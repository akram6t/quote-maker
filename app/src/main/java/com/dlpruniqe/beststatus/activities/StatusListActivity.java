package com.dlpruniqe.beststatus.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.adapters.PoetryListAdapters;
import com.dlpruniqe.beststatus.databinding.ActivityStatusListBinding;
import com.dlpruniqe.beststatus.models.PoetryListModels;
import com.dlpruniqe.beststatus.other.GetAllGradient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StatusListActivity extends AppCompatActivity {
    private int[] all_grad;
    private ActivityStatusListBinding binding;
    private ArrayList<PoetryListModels> list;
    private String assetspath;
    private String gettheme;
    private String statusType = "";
    private InterstitialAd minterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this);
        AdView ladView = findViewById(R.id.ladView);
        AdRequest adRequest = new AdRequest.Builder().build();
        ladView.loadAd(adRequest);

        getsharedprefrence();
        themesettingsharedprefrencechack();

        GetAllGradient getAllGradient = new GetAllGradient();
        all_grad = getAllGradient.getGradientArray();
        assetspath = getIntent().getStringExtra("key");
        String categoryName = getIntent().getStringExtra("name");
        binding.idtextCategory.setText(categoryName);

        list = new ArrayList<>();
        getshayari();

        binding.idbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnlistRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnlistRefresh.animate().rotation(-405).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        getshayari();
                        binding.btnlistRefresh.setRotation(-45);
                    }
                }).start();

            }
        });

    }

    private String json(String key){
        String json = null;
       try {
           InputStream in = getAssets().open(key);
           int size = in.available();
           byte [] bbuffer = new byte[size];
           in.read(bbuffer);
           in.close();
           json = new String(bbuffer, StandardCharsets.UTF_8);

       } catch (IOException e) {
           e.printStackTrace();
       }

        return json;
    }

    private void getshayari() {
        Random random = new Random();
        try {
            JSONObject jsonObject = new JSONObject(json(assetspath+".json"));
            if (assetspath.contains("e/")){
                statusType = "e/";
            }else {
                if (assetspath.contains("h/")){
                    statusType = "h/";
                }
            }
            JSONArray jsonArray = jsonObject.getJSONArray(assetspath.replace(statusType, ""));
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                int r_grad = random.nextInt(all_grad.length);
                PoetryListModels lmodels = new PoetryListModels(obj.getInt("id"), all_grad[r_grad], obj.getString("p"));
                list.add(lmodels);
            }
            Collections.shuffle(list);
            PoetryListAdapters padapters = new PoetryListAdapters(StatusListActivity.this, list);
            binding.idlistrecyclerview.setAdapter(padapters);
            binding.idlistrecyclerview.setLayoutManager(new LinearLayoutManager(StatusListActivity.this));

            } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void themesettingsharedprefrencechack(){
        switch (gettheme){
            case "theme1":
                binding.listparentLayout.setBackgroundResource(R.drawable.main_back);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                break;
            case "theme2":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_2);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                break;
            case "theme3":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_3);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                break;
            case "theme4":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_4);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                break;
            case "theme5":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_5);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                break;
            case "theme6":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_6);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                break;
            case "theme7":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_7);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                break;
            case "theme8":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_8);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                break;
            case "theme9":
                binding.listparentLayout.setBackgroundResource(R.drawable.backround_9);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                break;
        }
    }

    private void getsharedprefrence(){
        SharedPreferences getshared = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        gettheme = getshared.getString(getString(R.string.backroundKey), "theme1");
    }

}