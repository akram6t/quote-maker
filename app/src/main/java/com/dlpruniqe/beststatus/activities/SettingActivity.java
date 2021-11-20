package com.dlpruniqe.beststatus.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.databinding.ActivitySettingBinding;
import com.dlpruniqe.beststatus.databinding.ProgressLayoutBinding;
import com.dlpruniqe.beststatus.other.CToast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class SettingActivity extends AppCompatActivity {
    private ActivitySettingBinding binding;
    private boolean isthemeopenable;
    private boolean isfontopenable;
    private boolean islanguageopenable;
    private String gettheme;
    AlertDialog loaddialog;
    ProgressLayoutBinding progbinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progbinding = ProgressLayoutBinding.inflate(getLayoutInflater());
        progbinding.progressTextView.setText("Loading Ads...");
        loaddialog = new AlertDialog.Builder(this)
        .setView(progbinding.getRoot())
        .setCancelable(false).create();
        loaddialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

        MobileAds.initialize(this);

        AdView sadView = findViewById(R.id.sadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        sadView.loadAd(adRequest);

        binding.idsetbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getsharedprefrence();
        themesettingallwork();
        themesettingsharedprefrencechack();
        fontssettingsallwork();
        fontssettingssharedprefrencechack();
        languagesettingsallwork();
        languagechackinSharedPrefrence();


    }

    private void themesettingallwork(){
        SharedPreferences backpref = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        SharedPreferences.Editor editor = backpref.edit();
        isthemeopenable = true;
        binding.themelayout.setVisibility(View.GONE);

        binding.btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isthemeopenable){
                    binding.themelayout.setVisibility(View.VISIBLE);
                    Animation animationopen = AnimationUtils.loadAnimation(SettingActivity.this, R.anim.anim_themelayout_opend);
                    binding.themelayout.setAnimation(animationopen);
                    animationopen.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            binding.themelayout.setVisibility(View.VISIBLE);
                            isthemeopenable = false;
                            binding.themelayout.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    binding.themeaeroimage.animate().rotation(90f).setDuration(500).start();
                }else {
                    binding.themelayout.setVisibility(View.GONE);
                    isthemeopenable = true;
                    binding.themeaeroimage.animate().rotation(0).setDuration(500).start();
                }
            }
        });

        binding.btntheme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme1")){
                    gettheme = "theme1";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme2")){
                    gettheme = "theme2";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme3")){
                    gettheme = "theme3";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme4")){
                    gettheme = "theme4";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme5")){
                    gettheme = "theme5";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme6")){
                    gettheme = "theme6";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme7")){
                    gettheme = "theme7";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme8")){
                    gettheme = "theme8";
                    loadvideorewarded();
                }

            }
        });
        binding.btntheme9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gettheme.equals("theme9")){
                    gettheme = "theme9";
                    loadvideorewarded();
                }

            }
        });

    }
    private void themesettingsharedprefrencechack(){
        switch (gettheme){
            case "theme1":
                binding.parentLayout.setBackgroundResource(R.drawable.main_back);
                binding.setthemeback.setBackgroundResource(R.drawable.main_back);
                binding.btnApply1.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                break;
            case "theme2":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_2);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_2);
                binding.btnApply2.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                break;
            case "theme3":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_3);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_3);
                binding.btnAppy3.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                break;
            case "theme4":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_4);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_4);
                binding.btnApply4.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                break;
            case "theme5":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_5);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_5);
                binding.btnApply5.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                break;
            case "theme6":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_6);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_6);
                binding.btnApply6.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                break;
            case "theme7":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_7);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_7);
                binding.btnApply7.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                break;
            case "theme8":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_8);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_8);
                binding.btnApply8.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                break;
            case "theme9":
                binding.parentLayout.setBackgroundResource(R.drawable.backround_9);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_9);
                binding.btnApply9.setVisibility(View.VISIBLE);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                break;
        }
    }

    private void fontssettingsallwork(){
        isfontopenable = true;
        SharedPreferences fontShared = getSharedPreferences(getString(R.string.dbfont), MODE_PRIVATE);
        SharedPreferences.Editor feditor = fontShared.edit();
        binding.fontslayout.setVisibility(View.GONE);
        binding.btnFonts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfontopenable){
                    binding.fontslayout.setVisibility(View.VISIBLE);
                    Animation animationopen = AnimationUtils.loadAnimation(SettingActivity.this, R.anim.anim_themelayout_opend);
                    binding.fontslayout.setAnimation(animationopen);
                    animationopen.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            binding.fontslayout.setVisibility(View.VISIBLE);
                            isfontopenable = false;
                            binding.fontslayout.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    binding.fontaeroimage.animate().rotation(90f).setDuration(500).start();
                }else {
                    binding.fontslayout.setVisibility(View.GONE);
                    isfontopenable = true;
                    binding.fontaeroimage.animate().rotation(0).setDuration(500).start();
                }
            }
        });

        binding.font1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface tf1 = ResourcesCompat.getFont(SettingActivity.this, R.font.anonymous_pro);
                binding.textfonts.setTypeface(tf1);
                binding.font1.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font1.setTextColor(getResources().getColor(R.color.white));

                binding.font2.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font3.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font4.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font5.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font6.setBackgroundResource(R.drawable.setting_font_back_default);

                binding.font2.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font3.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font4.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font5.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font6.setTextColor(getResources().getColor(R.color.halfblack));
                feditor.putString(getString(R.string.fontKey), "font1");
                feditor.apply();
            }
        });
        binding.font2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface tf2 = ResourcesCompat.getFont(SettingActivity.this, R.font.amarante);
                binding.textfonts.setTypeface(tf2);
                binding.font2.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font2.setTextColor(getResources().getColor(R.color.white));

                binding.font1.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font3.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font4.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font5.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font6.setBackgroundResource(R.drawable.setting_font_back_default);

                binding.font1.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font3.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font4.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font5.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font6.setTextColor(getResources().getColor(R.color.halfblack));
                feditor.putString(getString(R.string.fontKey), "font2");
                feditor.apply();
            }
        });
        binding.font3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface tf3 = ResourcesCompat.getFont(SettingActivity.this, R.font.love_ya_like_a_sister);
                binding.textfonts.setTypeface(tf3);
                binding.font3.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font3.setTextColor(getResources().getColor(R.color.white));

                binding.font2.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font1.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font4.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font5.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font6.setBackgroundResource(R.drawable.setting_font_back_default);

                binding.font2.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font1.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font4.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font5.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font6.setTextColor(getResources().getColor(R.color.halfblack));
                feditor.putString(getString(R.string.fontKey), "font3");
                feditor.apply();
            }
        });
        binding.font4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface tf4 = ResourcesCompat.getFont(SettingActivity.this, R.font.alike_angular);
                binding.textfonts.setTypeface(tf4);
                binding.font4.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font4.setTextColor(getResources().getColor(R.color.white));

                binding.font2.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font3.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font1.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font5.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font6.setBackgroundResource(R.drawable.setting_font_back_default);

                binding.font2.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font3.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font1.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font5.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font6.setTextColor(getResources().getColor(R.color.halfblack));
                feditor.putString(getString(R.string.fontKey), "font4");
                feditor.apply();
            }
        });
        binding.font5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface tf5 = ResourcesCompat.getFont(SettingActivity.this, R.font.special_elite);
                binding.textfonts.setTypeface(tf5);
                binding.font5.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font5.setTextColor(getResources().getColor(R.color.white));

                binding.font2.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font3.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font4.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font1.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font6.setBackgroundResource(R.drawable.setting_font_back_default);

                binding.font2.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font3.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font4.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font1.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font6.setTextColor(getResources().getColor(R.color.halfblack));
                feditor.putString(getString(R.string.fontKey), "font5");
                feditor.apply();
            }
        });
        binding.font6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface tf6 = ResourcesCompat.getFont(SettingActivity.this, R.font.short_stack);
                binding.textfonts.setTypeface(tf6);
                binding.font6.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font6.setTextColor(getResources().getColor(R.color.white));

                binding.font2.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font3.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font4.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font5.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.font1.setBackgroundResource(R.drawable.setting_font_back_default);

                binding.font2.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font3.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font4.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font5.setTextColor(getResources().getColor(R.color.halfblack));
                binding.font1.setTextColor(getResources().getColor(R.color.halfblack));
                feditor.putString(getString(R.string.fontKey), "font6");
                feditor.apply();
            }
        });

    }
    private void fontssettingssharedprefrencechack(){
        SharedPreferences getfontchackprefrence = getSharedPreferences(getString(R.string.dbfont), MODE_PRIVATE);
        String getfont = getfontchackprefrence.getString(getString(R.string.fontKey), "font1");

        switch (getfont){
            case "font1":
                Typeface typeface1 = ResourcesCompat.getFont(SettingActivity.this, R.font.anonymous_pro);
                binding.textfonts.setTypeface(typeface1);
                binding.font1.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font1.setTextColor(getResources().getColor(R.color.white));
                break;
            case "font2":
                Typeface typeface2 = ResourcesCompat.getFont(SettingActivity.this, R.font.amarante);
                binding.textfonts.setTypeface(typeface2);
                binding.font2.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font2.setTextColor(getResources().getColor(R.color.white));
                break;
            case "font3":
                Typeface typeface3 = ResourcesCompat.getFont(SettingActivity.this, R.font.love_ya_like_a_sister);
                binding.textfonts.setTypeface(typeface3);
                binding.font3.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font3.setTextColor(getResources().getColor(R.color.white));
                break;
            case "font4":
                Typeface typeface4 = ResourcesCompat.getFont(SettingActivity.this, R.font.alike_angular);
                binding.textfonts.setTypeface(typeface4);
                binding.font4.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font4.setTextColor(getResources().getColor(R.color.white));
                break;
            case "font5":
                Typeface typeface5 = ResourcesCompat.getFont(SettingActivity.this, R.font.special_elite);
                binding.textfonts.setTypeface(typeface5);
                binding.font5.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font5.setTextColor(getResources().getColor(R.color.white));
                break;
            case "font6":
                Typeface typeface6 = ResourcesCompat.getFont(SettingActivity.this, R.font.short_stack);
                binding.textfonts.setTypeface(typeface6);
                binding.font6.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.font6.setTextColor(getResources().getColor(R.color.white));
                break;

        }

    };

    private void languagesettingsallwork(){
        SharedPreferences langpref = getSharedPreferences(getString(R.string.dbLang), MODE_PRIVATE);
        SharedPreferences.Editor ledit = langpref.edit();
        islanguageopenable = true;
        binding.lnaguagelayout.setVisibility(View.GONE);
        binding.btnlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (islanguageopenable){
                    binding.lnaguagelayout.setVisibility(View.VISIBLE);
                    Animation animationlang = AnimationUtils.loadAnimation(SettingActivity.this, R.anim.anim_themelayout_opend);
                    binding.lnaguagelayout.setAnimation(animationlang);
                    animationlang.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            binding.lnaguagelayout.setVisibility(View.VISIBLE);
                            islanguageopenable = false;
                            binding.lnaguagelayout.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    binding.lanaeroimage.animate().rotation(90f).setDuration(500).start();
                }else {
                    binding.lnaguagelayout.setVisibility(View.GONE);
                    islanguageopenable = true;
                    binding.lanaeroimage.animate().rotation(0).setDuration(500).start();
                }
            }
        });

        binding.changeHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.changeHindi.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.changeHindi.setTextColor(getResources().getColor(R.color.white));
                ledit.putString(getString(R.string.langKey), getString(R.string.valueHindi));
                ledit.apply();
                binding.changeEnglish.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.changeEnglish.setTextColor(getResources().getColor(R.color.halfblack));
                binding.textlang.setText("Hindi");
            }
        });
        binding.changeEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.changeEnglish.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.changeEnglish.setTextColor(getResources().getColor(R.color.white));
                ledit.putString(getString(R.string.langKey), getString(R.string.valueEnglish));
                ledit.apply();
                binding.changeHindi.setBackgroundResource(R.drawable.setting_font_back_default);
                binding.changeHindi.setTextColor(getResources().getColor(R.color.halfblack));
                binding.textlang.setText("English");

            }
        });

    }
    private void languagechackinSharedPrefrence(){
        SharedPreferences getlanpref = getSharedPreferences(getString(R.string.dbLang), MODE_PRIVATE);
        String getlang = getlanpref.getString(getString(R.string.langKey), getString(R.string.valueHindi));

        if (getlang.equals(getString(R.string.valueHindi))){
            binding.changeHindi.setBackgroundResource(R.drawable.setting_font_back_clicked);
            binding.changeHindi.setTextColor(getResources().getColor(R.color.white));
            binding.textlang.setText("Hindi");
        }else
            if (getlang.equals(getString(R.string.valueEnglish))){
                binding.changeEnglish.setBackgroundResource(R.drawable.setting_font_back_clicked);
                binding.changeEnglish.setTextColor(getResources().getColor(R.color.white));
                binding.textlang.setText("English");
        }
    }

    private void getsharedprefrence(){
        SharedPreferences getshared = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        gettheme = getshared.getString(getString(R.string.backroundKey), "theme1");
    }

    private void loadvideorewarded(){
        loaddialog.show();
        SharedPreferences themepreef = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        SharedPreferences.Editor editor = themepreef.edit();

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, getString(R.string.rewardedAds),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Toast.makeText(SettingActivity.this, "Internet Problem", Toast.LENGTH_SHORT).show();
                        loaddialog.dismiss();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        loaddialog.dismiss();

                        rewardedAd.show(SettingActivity.this, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                CToast cToast = new CToast(SettingActivity.this);
                                cToast.setText("Successfully Theme Changed");
                                cToast.iconVisible(false);
                                cToast.show();
                                switch (gettheme){
                                    case "theme1":
                                        binding.parentLayout.setBackgroundResource(R.drawable.main_back);
                binding.btnApply1.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.main_back);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme1");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                                        gettheme = "theme1";
                                        break;
                                    case "theme2":
                                         binding.parentLayout.setBackgroundResource(R.drawable.backround_2);
                binding.btnApply2.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_2);

                binding.btnApply1.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme2");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                                        gettheme = "theme2";
                                        break;
                                    case "theme3":
                                        binding.parentLayout.setBackgroundResource(R.drawable.backround_3);
                binding.btnAppy3.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_3);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnApply1.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme3");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                                        gettheme = "theme3";
                                        break;
                                    case "theme4":
                                        binding.parentLayout.setBackgroundResource(R.drawable.backround_4);
                binding.btnApply4.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_4);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply1.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme4");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                                        gettheme = "theme4";
                                        break;
                                    case "theme5":
                                        binding.parentLayout.setBackgroundResource(R.drawable.backround_5);
                binding.btnApply5.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_5);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply1.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme5");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                                        gettheme = "theme5";
                                        break;
                                    case "theme6":
                                        binding.parentLayout.setBackgroundResource(R.drawable.backround_6);
                binding.btnApply6.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_6);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply1.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme6");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                                        gettheme = "theme6";
                                        break;
                                    case "theme7":
                                         binding.parentLayout.setBackgroundResource(R.drawable.backround_7);
                binding.btnApply7.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_7);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply1.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme7");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                gettheme = "theme7";
                break;

                                    case "theme8":
                                        binding.parentLayout.setBackgroundResource(R.drawable.backround_8);
                binding.btnApply8.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_8);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply1.setVisibility(View.GONE);
                binding.btnApply9.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme8");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                gettheme = "theme8";
                                        break;
                                    case "theme9":
                                        binding.parentLayout.setBackgroundResource(R.drawable.backround_9);
                binding.btnApply9.setVisibility(View.VISIBLE);
                binding.setthemeback.setBackgroundResource(R.drawable.backround_9);

                binding.btnApply2.setVisibility(View.GONE);
                binding.btnAppy3.setVisibility(View.GONE);
                binding.btnApply4.setVisibility(View.GONE);
                binding.btnApply5.setVisibility(View.GONE);
                binding.btnApply6.setVisibility(View.GONE);
                binding.btnApply7.setVisibility(View.GONE);
                binding.btnApply8.setVisibility(View.GONE);
                binding.btnApply1.setVisibility(View.GONE);
                editor.putString(getString(R.string.backroundKey), "theme9");
                editor.apply();
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                                        gettheme = "theme9";
                                        break;

                                }

                            }
                        });
                    }
                });
    }
}