package com.dlpruniqe.beststatus.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.adapters.MainAdapters;
import com.dlpruniqe.beststatus.databinding.HelpLayoutBinding;
import com.dlpruniqe.beststatus.databinding.MainContentBinding;
import com.dlpruniqe.beststatus.databinding.NavHeaderBinding;
import com.dlpruniqe.beststatus.models.MainModels;
import com.dlpruniqe.beststatus.other.HelpTextViewText;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainContentBinding mbinding;
    private DrawerLayout drawerLayout;
    private MainModels mainModels;
    private NavigationView navigationView;
    private NavHeaderBinding hbinding;
    private String gettheme;
    AlertDialog helpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigationView);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        drawerLayout = findViewById(R.id.drawerLayout);
        View mview = findViewById(R.id.maincontent);
        mbinding = MainContentBinding.bind(mview);

        View headerView = navigationView.getHeaderView(0);
        hbinding = NavHeaderBinding.bind(headerView);

        helpButtonwork();

        getsharedprefrence();
        themesettingsharedprefrencechack();
        navHeaderallwork();
        openDrawerbutton();
        recyclerviewLoadData();
        createStatusfloating();

    }

    private void openDrawerbutton(){
            mbinding.btnopenmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(findViewById(R.id.navigationView));

                }
            });
    }

    private void navHeaderallwork(){

        hbinding.hhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navigationView);
            }
        });
        hbinding.hsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(setIntent);
            }
        });
        hbinding.hmystatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statusIntent = new Intent(MainActivity.this, MyStatusActivity.class);
                startActivity(statusIntent);
            }
        });
        hbinding.hshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(Intent.ACTION_SEND);
                sIntent.setType("text/application");
                sIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharetext) + getString(R.string.playstorelink) +getPackageName());
                startActivity(Intent.createChooser(sIntent, "Share Link using"));
            }
        });
        hbinding.hrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playLink = getString(R.string.playstorelink)+getPackageName();
                try {
                    Intent rintent = new Intent(Intent.ACTION_VIEW, Uri.parse(playLink));
                    startActivity(rintent);
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });
        hbinding.hprivacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pintent = new Intent(MainActivity.this, PrivacyPolicyActivity.class);
                startActivity(pintent);
            }
        });
        hbinding.hyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent yintent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.myyoutube)));
                    startActivity(yintent);
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });
        hbinding.hinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent iintent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.myinstagram)));
                    startActivity(iintent);
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });

    }

    private void recyclerviewLoadData(){
        SharedPreferences langPref = getSharedPreferences(getString(R.string.dbLang), MODE_PRIVATE);
        String getLanguage = langPref.getString(getString(R.string.langKey), getString(R.string.valueHindi));

        ArrayList<MainModels> statusList = new ArrayList<>();
        if (getLanguage.equals(getString(R.string.valueHindi))){
            statusList.add(new MainModels("h/loveH", "Love\nStatus"));
            statusList.add(new MainModels("h/sadH", "Sad\nStatus"));
            statusList.add(new MainModels("h/attitudeH", "Attitude\nStatus"));
            statusList.add(new MainModels("h/attitude2H", "Attitude 2.0\nStatus"));
            statusList.add(new MainModels("h/freindsH", "Freinds\nStatus"));
            statusList.add(new MainModels("h/fannyH", "Fanny\nStatus"));
            statusList.add(new MainModels("h/motivationH", "Motivation\nStatus"));
            statusList.add(new MainModels("h/whatsappH", "WhatsApp\nStatus"));
            statusList.add(new MainModels("h/gmH", "Good Morning\nStatus"));
            statusList.add(new MainModels("h/pubgH", "Pubg\nStatus"));
            statusList.add(new MainModels("h/freefireH", "FreeFire\nStatus"));
            hbinding.navHeading.setText("Best Status in Hindi");
        }else {
            if (getLanguage.equals(getString(R.string.valueEnglish))) {
                statusList.add(new MainModels("e/loveE", "Love\nStatus"));
                statusList.add(new MainModels("e/freindE", "Freinds\nStatus"));
                statusList.add(new MainModels("e/fannyE", "Fanny\nStatus"));
                statusList.add(new MainModels("e/motivationE", "Motivation\nStatus"));
                statusList.add(new MainModels("e/sadE", "Sad\nStatus"));
                statusList.add(new MainModels("e/attitudeE", "Attitude\nStatus"));
                statusList.add(new MainModels("e/whatsappE", "WhatsApp\nStatus"));
                statusList.add(new MainModels("e/pubgE", "Pubg\nStatus"));
                hbinding.navHeading.setText("Best Status in English");
            }
        }

        MainAdapters mainAdapters = new MainAdapters(MainActivity.this, statusList);
        mbinding.recyclerview.setAdapter(mainAdapters);
        mbinding.recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

    }

    private void themesettingsharedprefrencechack(){
        switch (gettheme){
            case "theme1":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.main_back);
                navigationView.setBackgroundResource(R.drawable.main_back);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                break;
            case "theme2":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_2);
                navigationView.setBackgroundResource(R.drawable.backround_2);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                break;
            case "theme3":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_3);
                navigationView.setBackgroundResource(R.drawable.backround_3);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                break;
            case "theme4":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_4);
                navigationView.setBackgroundResource(R.drawable.backround_4);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                break;
            case "theme5":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_5);
                navigationView.setBackgroundResource(R.drawable.backround_5);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                break;
            case "theme6":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_6);
                navigationView.setBackgroundResource(R.drawable.backround_6);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                break;
            case "theme7":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_7);
                navigationView.setBackgroundResource(R.drawable.backround_7);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                break;
            case "theme8":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_8);
                navigationView.setBackgroundResource(R.drawable.backround_8);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                break;
            case "theme9":
                mbinding.parentMainLayout.setBackgroundResource(R.drawable.backround_9);
                navigationView.setBackgroundResource(R.drawable.backround_9);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                break;
        }
    }

    private void getsharedprefrence(){
        SharedPreferences getshared = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        gettheme = getshared.getString(getString(R.string.backroundKey), "theme1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getsharedprefrence();
        themesettingsharedprefrencechack();
        recyclerviewLoadData();
    }

    private void createStatusfloating(){
        mbinding.floatingcreateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cintent = new Intent(MainActivity.this, StatusViewActivity.class);
                cintent.putExtra(getString(R.string.createStatusKey), true);
                startActivity(cintent);
            }
        });
    }

    private void helpButtonwork(){
        HelpLayoutBinding helpBinding = HelpLayoutBinding.inflate(getLayoutInflater());
        helpDialog = new AlertDialog
                .Builder(MainActivity.this)
                .setView(helpBinding.getRoot())
                .create();
        helpDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        helpBinding.helpText.setText(Html.fromHtml(new HelpTextViewText().gettext()));

        mbinding.btnmainHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.show();
            }
        });
        helpBinding.helpclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });

    }

}