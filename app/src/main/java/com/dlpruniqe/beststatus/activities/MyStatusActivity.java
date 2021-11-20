package com.dlpruniqe.beststatus.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.adapters.MyStatusAdapter;
import com.dlpruniqe.beststatus.databinding.DeleteOrNotdeleteLayoutBinding;
import com.dlpruniqe.beststatus.databinding.MystatusEditLayoutBinding;
import com.dlpruniqe.beststatus.database.SQlHelper;
import com.dlpruniqe.beststatus.databinding.ActivityMyStatusBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MyStatusActivity extends AppCompatActivity {
    private ActivityMyStatusBinding binding;
    private DeleteOrNotdeleteLayoutBinding deleteBinding;
    private AlertDialog ddialog;
    private SQlHelper sQlHelper;
    private AlertDialog edialog;
    private MystatusEditLayoutBinding editBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(MyStatusActivity.this);
        AdView msAdView = findViewById(R.id.msadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        msAdView.loadAd(adRequest);

        themesettingsharedprefrencechack();
        deletealertdialogcreate();
        editalertdialogcreate();
        getsqllitedata();

    }

    private void themesettingsharedprefrencechack(){
        SharedPreferences getthemeshared = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        String gettheme = getthemeshared.getString(getString(R.string.backroundKey), "theme1");
        switch (gettheme){
            case "theme1":
                binding.mystatusparent.setBackgroundResource(R.drawable.main_back);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                break;
            case "theme2":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_2);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                break;
            case "theme3":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_3);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                break;
            case "theme4":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_4);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                break;
            case "theme5":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_5);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                break;
            case "theme6":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_6);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                break;
            case "theme7":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_7);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                break;
            case "theme8":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_8);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                break;
            case "theme9":
                binding.mystatusparent.setBackgroundResource(R.drawable.backround_9);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                break;
        }
    }

    private void getsqllitedata(){
        sQlHelper = new SQlHelper(MyStatusActivity.this);

        if (!sQlHelper.readStatusList().isEmpty()){
            binding.textnodata.setVisibility(View.GONE);
        }
        MyStatusAdapter myAdapter = new MyStatusAdapter(MyStatusActivity.this, sQlHelper.readStatusList(), ddialog, deleteBinding, editBinding, edialog);
        binding.statusrecycler.setAdapter(myAdapter);
        LinearLayoutManager slm = new LinearLayoutManager(MyStatusActivity.this);
        binding.statusrecycler.setLayoutManager(slm);

        binding.idbackbtnmystatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

    private void deletealertdialogcreate(){
        deleteBinding = DeleteOrNotdeleteLayoutBinding.inflate(getLayoutInflater());
        ddialog = new AlertDialog.Builder(MyStatusActivity.this)
                .setView(deleteBinding.getRoot())
                .setCancelable(false)
                .create();
        deleteBinding.dIcon.setImageResource(R.drawable.icon_delete);
        deleteBinding.btndDelete.setText("Delete");
        deleteBinding.btndCancel.setText("Cancel");
        deleteBinding.dTitle.setText(getString(R.string.delete_layout_title));
        deleteBinding.dMessage.setText(getString(R.string.delete_layout_message));
        ddialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

        ddialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                if (sQlHelper.readStatusList().isEmpty()){
                    binding.textnodata.setVisibility(View.VISIBLE);
                }
                getsqllitedata();
            }
        });
    }

    private void editalertdialogcreate(){
        editBinding = MystatusEditLayoutBinding.inflate(getLayoutInflater());
        edialog = new AlertDialog.Builder(MyStatusActivity.this)
                .setView(editBinding.getRoot())
                .setCancelable(false)
                .create();
        edialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

        edialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                getsqllitedata();
            }
        });

    }

}