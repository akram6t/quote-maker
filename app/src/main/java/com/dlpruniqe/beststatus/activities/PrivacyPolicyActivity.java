package com.dlpruniqe.beststatus.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.dlpruniqe.beststatus.R;

public class PrivacyPolicyActivity extends AppCompatActivity {
    private ConstraintLayout pparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        WebView webView = findViewById(R.id.pwebView);
        pparent = findViewById(R.id.pparent);

        themesettingsharedprefrencechack();

        String privacyUrl = getString(R.string.privacypolicylink);
        webView.loadUrl(privacyUrl);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(false);
    }

    private void themesettingsharedprefrencechack(){
        SharedPreferences tshare = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        String gettheme = tshare.getString(getString(R.string.backroundKey), "theme1");

        switch (gettheme){
            case "theme1":
                pparent.setBackgroundResource(R.drawable.main_back);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                break;
            case "theme2":
                pparent.setBackgroundResource(R.drawable.backround_2);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                break;
            case "theme3":
                pparent.setBackgroundResource(R.drawable.backround_3);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                break;
            case "theme4":
                pparent.setBackgroundResource(R.drawable.backround_4);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                break;
            case "theme5":
                pparent.setBackgroundResource(R.drawable.backround_5);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                break;
            case "theme6":
                pparent.setBackgroundResource(R.drawable.backround_6);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                break;
            case "theme7":
                pparent.setBackgroundResource(R.drawable.backround_7);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                break;
            case "theme8":
                pparent.setBackgroundResource(R.drawable.backround_8);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                break;
            case "theme9":
                pparent.setBackgroundResource(R.drawable.backround_9);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                break;
        }
    }

}