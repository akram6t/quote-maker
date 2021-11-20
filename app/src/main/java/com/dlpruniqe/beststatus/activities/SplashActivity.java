package com.dlpruniqe.beststatus.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.dlpruniqe.beststatus.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private String gettheme;
    private ConstraintLayout parentsplashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        parentsplashLayout = findViewById(R.id.splashparentLayout);

        getsharedprefrence();
        themesettingsharedprefrencechack();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.zoom_anim);
        findViewById(R.id.imageView2).setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LanguageActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

    private void themesettingsharedprefrencechack(){
        switch (gettheme){
            case "theme1":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_1);
                break;
            case "theme2":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_2);
                break;
            case "theme3":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_3);
                break;
            case "theme4":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_4);
                break;
            case "theme5":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_5);
                break;
            case "theme6":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_6);
                break;
            case "theme7":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_7);
                break;
            case "theme8":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_8);
                break;
            case "theme9":
                parentsplashLayout.setBackgroundResource(R.drawable.splash_9);
                break;
        }
    }

    private void getsharedprefrence(){
        SharedPreferences getshared = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        gettheme = getshared.getString(getString(R.string.backroundKey), "theme1");
    }

}