package com.dlpruniqe.beststatus.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.databinding.ActivityLanguageBinding;

public class LanguageActivity extends AppCompatActivity {
    private ActivityLanguageBinding binding;
    private String statusLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        boolean firstTime = true;

        getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
        getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));

        animations();
        sharedprefrence();
        getsharedPrefrence();
    }

    private void sharedprefrence(){
        SharedPreferences firstpref = getSharedPreferences("firsttime", MODE_PRIVATE);
        SharedPreferences.Editor firstEditor = firstpref.edit();

        SharedPreferences shrd = getSharedPreferences(getString(R.string.dbLang), MODE_PRIVATE);
        SharedPreferences.Editor langEdit = shrd.edit();

        binding.btnhindiStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                langEdit.putString(getString(R.string.langKey), getString(R.string.valueHindi));
                langEdit.apply();

                firstEditor.putBoolean("firsttime", false);
                firstEditor.apply();

                Intent hintent = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(hintent);
                finish();
            }
        });

        binding.btnenglishStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                langEdit.putString(getString(R.string.langKey), getString(R.string.valueEnglish));
                langEdit.apply();

                firstEditor.putBoolean("firsttime", false);
                firstEditor.apply();

                Intent eintent = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(eintent);
                finish();
            }
        });

    }

    private void animations(){
        binding.btnhindiStatus.setTranslationX(-500);
        binding.btnenglishStatus.setTranslationX(500);
        binding.btnhindiStatus.animate().translationX(0f).setDuration(2000).start();
        binding.btnenglishStatus.animate().translationX(0f).setDuration(2000).start();

        binding.appnametextView.setTranslationY(-500f);
        binding.appforground.setTranslationY(-500f);
        binding.statusTitle.setTranslationY(-500f);
        binding.idprivacy.setTranslationY(500);
        binding.appnametextView.animate().translationY(0f).setDuration(1500).start();
        binding.appforground.animate().translationY(0f).setDuration(1500).start();
        binding.statusTitle.animate().translationY(0f).setDuration(1500).start();
        binding.idprivacy.animate().translationY(0).setDuration(1500).start();

    }

    private void getsharedPrefrence(){
        SharedPreferences getfirstref = getSharedPreferences("firsttime", MODE_PRIVATE);
        boolean getfirsttime = getfirstref.getBoolean("firsttime", true);

        SharedPreferences getthemeprefrence = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        String themes = getthemeprefrence.getString(getString(R.string.backroundKey), "theme1");

        SharedPreferences getsrd = getSharedPreferences(getString(R.string.dbLang), MODE_PRIVATE);
        String languages = getsrd.getString(getString(R.string.langKey), getString(R.string.langhindi));

        if (getfirsttime==false){
            Intent screenIntent = new Intent(LanguageActivity.this, MainActivity.class);
            startActivity(screenIntent);
            finish();
        }

    }


}