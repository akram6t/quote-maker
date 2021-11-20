package com.dlpruniqe.beststatus.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.databinding.ActivityStatusDownloadBinding;
import com.dlpruniqe.beststatus.databinding.ToastLayoutBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StatusDownloadActivity extends AppCompatActivity {
    private ActivityStatusDownloadBinding binding;
    private String poetryName;
    private Uri shareUri;
    private ToastLayoutBinding toastBinding;
    private boolean isSaved = true;
    private String gettheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusDownloadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this);
        AdView dadView = findViewById(R.id.dadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        dadView.loadAd(adRequest);

        loadinterstitialads();

        chackReadWritepermissions();
        getsharedprefrence();
        themesettingsharedprefrencechack();

        Intent intent = getIntent();
        poetryName = intent.getStringExtra("poetryname");
        Uri imageuri = Uri.parse(intent.getExtras().getString("imageuri"));
        binding.downloadImage.setImageURI(imageuri);

        toastBinding = ToastLayoutBinding.inflate(getLayoutInflater());
        binding.bottomLayout.setVisibility(View.VISIBLE);




        binding.btnidShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.bottomLayout.setVisibility(View.GONE);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) binding.downloadImage.getDrawable();
                Bitmap shareImage = bitmapDrawable.getBitmap();
                String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(),shareImage,"share",null);
                shareUri = Uri.parse(bitmapPath);
                shareimageMethod();
                binding.bottomLayout.setVisibility(View.VISIBLE);
            }
        });
        binding.btnidCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(StatusDownloadActivity.this);
                toast.setView(toastBinding.getRoot());
                toastBinding.toastIcon.setVisibility(View.VISIBLE);
                toastBinding.toastIcon.setImageResource(R.drawable.icon_copy);
                toastBinding.toastText.setText("Copied...");
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", poetryName);
                clipboardManager.setPrimaryClip(clipData);
                toast.show();
            }
        });
        binding.btnidDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSaved){
                    binding.bottomLayout.setVisibility(View.GONE);
                    saveImage();
                    binding.bottomLayout.setVisibility(View.VISIBLE);
                }else {
                    Toast toast = new Toast(StatusDownloadActivity.this);
                    toast.setView(toastBinding.getRoot());
                    toastBinding.toastIcon.setVisibility(View.GONE);
                    toastBinding.toastText.setText("Image Already Downloaded");
                    toast.show();
                }
            }
        });

        binding.idbackarrowdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void saveImage(){
        String imagedate = new SimpleDateFormat("ssmmHHddMMyyyy").format(new Date());
        String imageName = imagedate + ".png";
        File filePath = new File(Environment.getExternalStorageDirectory()+"/"+"DCIM"+"/Best Status");
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        File file = new File(filePath,imageName);
        Bitmap imageBitmap = ((BitmapDrawable) binding.downloadImage.getDrawable()).getBitmap();
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.close();
            toastBinding.toastIcon.setVisibility(View.GONE);
            toastBinding.toastText.setText("Image Saved in\n"+filePath.toString()+imageName);
            Toast dtoast = new Toast(StatusDownloadActivity.this);
            dtoast.setView(toastBinding.getRoot());
            dtoast.show();
            isSaved = false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            toastBinding.toastIcon.setVisibility(View.GONE);
            toastBinding.toastText.setText("Image Not Saved in Storage");
            Toast toast = new Toast(StatusDownloadActivity.this);
            toast.setView(toastBinding.getRoot());
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shareimageMethod(){
        Random random  = new Random();
        int ran = random.nextInt(5);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/png");
        shareIntent.putExtra(Intent.EXTRA_STREAM, shareUri);
        if (ran==2){
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download this App 👇\n" + getString(R.string.playstorelink) +getPackageName());
        }
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }

    private void chackReadWritepermissions(){
        Dexter.withContext(StatusDownloadActivity.this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()){
                        }else {
                            finish();
                            Toast.makeText(StatusDownloadActivity.this, "please Allow Permission", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

    private void themesettingsharedprefrencechack(){
        switch (gettheme){
            case "theme1":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.main_back);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                break;
            case "theme2":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_2);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                break;
            case "theme3":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_3);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                break;
            case "theme4":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_4);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                break;
            case "theme5":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_5);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                break;
            case "theme6":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_6);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                break;
            case "theme7":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_7);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                break;
            case "theme8":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_8);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                break;
            case "theme9":
                binding.downloadparentLayout.setBackgroundResource(R.drawable.backround_9);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                break;
        }
    }

    private void getsharedprefrence(){
        SharedPreferences getshared = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        gettheme = getshared.getString(getString(R.string.backroundKey), "theme1");
    }

    private void loadinterstitialads() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getString(R.string.interestialAds), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAd.show(StatusDownloadActivity.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Toast.makeText(StatusDownloadActivity.this, loadAdError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}