package com.dlpruniqe.beststatus.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.basicadapters.TextColorAdapters;
import com.dlpruniqe.beststatus.basicadapters.TextFontsAdapters;
import com.dlpruniqe.beststatus.database.SQlHelper;
import com.dlpruniqe.beststatus.databinding.ActivityStatusViewBinding;
import com.dlpruniqe.beststatus.databinding.ColorpickerDialogBinding;
import com.dlpruniqe.beststatus.databinding.DeleteOrNotdeleteLayoutBinding;
import com.dlpruniqe.beststatus.databinding.LayTextEditBinding;
import com.dlpruniqe.beststatus.databinding.LayoutBackroundRecyclerviewBinding;
import com.dlpruniqe.beststatus.databinding.LayoutBorderBinding;
import com.dlpruniqe.beststatus.databinding.LayoutGradientRecyclerviewBinding;
import com.dlpruniqe.beststatus.databinding.LayoutResizeDownloadBinding;
import com.dlpruniqe.beststatus.databinding.ProgressLayoutBinding;
import com.dlpruniqe.beststatus.databinding.TextColorLayoutBinding;
import com.dlpruniqe.beststatus.databinding.TextColorPickerBinding;
import com.dlpruniqe.beststatus.databinding.TextEdittextDialogBinding;
import com.dlpruniqe.beststatus.databinding.TextFontLayoutBinding;
import com.dlpruniqe.beststatus.databinding.TextSizeLayoutBinding;
import com.dlpruniqe.beststatus.databinding.TextStyleLayoutBinding;
import com.dlpruniqe.beststatus.databinding.ToastLayoutBinding;
import com.dlpruniqe.beststatus.mainadapters.BackroundAdapter;
import com.dlpruniqe.beststatus.mainadapters.GradientAdapter;
import com.dlpruniqe.beststatus.models.MyStatusModels;
import com.dlpruniqe.beststatus.other.GetAllBackround;
import com.dlpruniqe.beststatus.other.GetAllFonts;
import com.dlpruniqe.beststatus.other.GetAllGradient;
import com.dlpruniqe.beststatus.other.ViewToUriConverter;
import com.dlpruniqe.beststatus.other.getAllColors;
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
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StatusViewActivity extends AppCompatActivity {
    private ActivityStatusViewBinding binding;
    private Intent getintentData;
    private ToastLayoutBinding toastbind;
    private LayTextEditBinding textBinding;
    private LayoutGradientRecyclerviewBinding gradientBinding;
    private LayoutBackroundRecyclerviewBinding backroundBinding;
    private LayoutBorderBinding borderBinding;
    private LayoutResizeDownloadBinding cropBinding;
    private TextEdittextDialogBinding textedittextdialogBinding;
    private TextColorLayoutBinding textColorLayoutBinding;
    private TextFontLayoutBinding textFontLayoutBinding;
    private TextSizeLayoutBinding textSizeLayoutBinding;
    private TextStyleLayoutBinding textStyleLayoutBinding;
    private AlertDialog text_editext_dialog;
    private AlertDialog borderalertdialog, textcolorDialog;
    private ColorpickerDialogBinding colorpickerDialogBinding;
    private int defaulttext_color = Color.parseColor("#ffffff");
    private int defaulttext_size = 20;
    private int default_borderpadding;
    private LinearLayout.LayoutParams default_params;
    private int okbordercolor;
    private Typeface defaulttextfont_typface;
    private Typeface defaulttextstyle_font;
    private Drawable default_gradient;
    private Drawable backroundDrawable;
    private final int BACKROUND_IMAGE_ADD_REQUESTCODE = 555;
    private Toast customToast;
    private ProgressLayoutBinding progressBinding;
    private AlertDialog progressDialog;
    private String gettheme;
    private boolean istextLAyoutOpened;
    private boolean isdataSaved;
    private DeleteOrNotdeleteLayoutBinding backBinding;
    private AlertDialog backDialog;
    private boolean onBackpressed;
    private String cstatusName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this);
        AdView vadView = findViewById(R.id.vadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        vadView.loadAd(adRequest);

        getsharedprefrence();
        themesettingsharedprefrencechack();
        fontchackinSharedPrefrence();

        binding.inclay.mainLayout.setBackgroundResource(getintentGradient());
        binding.inclay.mainTextPoetry.setText(getintentPoetry());

        boolean createStatusKey = getintentData.getBooleanExtra(getString(R.string.createStatusKey), false);
        boolean ismystatus = getintentData.getBooleanExtra(getString(R.string.ismystatus), false);
        String mystatusText = getintentData.getStringExtra(getString(R.string.mystatustext));

        backbuttononclick();
        allBindings();
        allBottomNavClickListener();
        createbackbuttonDialog();

        textEdittextallwork();
        textcolorallwork();
        textfontsallwork();
        textsizeallwork();
        textstyleallwork();

        if (createStatusKey) {
            binding.inclay.mainLayout.setBackgroundResource(new GetAllGradient().getGradientArray()[new Random().nextInt(new GetAllGradient().getGradientArray().length)]);
            textBinding.textEdittext.callOnClick();
        }
        if (ismystatus){
            binding.inclay.mainLayout.setBackgroundResource(new GetAllGradient().getGradientArray()[new Random().nextInt(new GetAllGradient().getGradientArray().length)]);
            binding.inclay.mainTextPoetry.setText(mystatusText);
            binding.btnSaveRoom.setVisibility(View.GONE);
        }

        maingradientallwork();
        mainbackroundallwork();
        mainborderallwork();
        mainsizeallwork();

        progressDialog = new AlertDialog.Builder(StatusViewActivity.this)
                .setView(progressBinding.getRoot())
                .setCancelable(false).create();

        binding.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customToast = new Toast(StatusViewActivity.this);
                customToast.setView(toastbind.getRoot());
                toastbind.toastIcon.setVisibility(View.VISIBLE);
                toastbind.toastIcon.setImageResource(R.drawable.icon_copy);
                toastbind.toastText.setText("Copied ...");
                customToast.show();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("CopiedText", binding.inclay.mainTextPoetry.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });

        binding.btnAllDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ssmmHH");
                String date = simpleDateFormat.format(new Date());
                ViewToUriConverter viewtoUri = new ViewToUriConverter(StatusViewActivity.this, binding.inclay.downloadLayout, date);
                Uri doneImageUri = viewtoUri.getImageuri();
                Intent intent = new Intent(StatusViewActivity.this, StatusDownloadActivity.class);
                intent.putExtra("poetryname", binding.inclay.mainTextPoetry.getText().toString());
                intent.putExtra("imageuri", doneImageUri.toString());
                startActivity(intent);
                progressDialog.dismiss();
                onBackpressed = true;
            }
        });

        binding.btnSaveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curStatus = binding.inclay.mainTextPoetry.getText().toString();
                if (!cstatusName.equals(binding.inclay.mainTextPoetry.getText().toString())){
                    isdataSaved = false;
                }
                if (!isdataSaved) {
                    loadinterstitialads();
                        SQlHelper sQlHelper = new SQlHelper(StatusViewActivity.this);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                        String date = simpleDateFormat.format(new Date());
                        MyStatusModels cmod = new MyStatusModels();
                        cmod.setStatus(curStatus);
                            sQlHelper.addStatus(binding.inclay.mainTextPoetry.getText().toString(), date);
                            Toast toast = new Toast(StatusViewActivity.this);
                            toast.setView(toastbind.getRoot());
                            toastbind.toastText.setText(getString(R.string.savedatainroom));
                            toastbind.toastIcon.setVisibility(View.GONE);
                            toast.show();
                            isdataSaved = true;
                            onBackpressed = true;
                            cstatusName = binding.inclay.mainTextPoetry.getText().toString();
                    } else {
                        Toast toast = new Toast(StatusViewActivity.this);
                        toast.setView(toastbind.getRoot());
                        toastbind.toastText.setText("Already Saved");
                        toastbind.toastIcon.setVisibility(View.GONE);
                        toast.show();
                    }
            }
        });

    }

    private void allBindings() {
        textBinding = LayTextEditBinding.inflate(getLayoutInflater());
        gradientBinding = LayoutGradientRecyclerviewBinding.inflate(getLayoutInflater());
        backroundBinding = LayoutBackroundRecyclerviewBinding.inflate(getLayoutInflater());
        borderBinding = LayoutBorderBinding.inflate(getLayoutInflater());
        cropBinding = LayoutResizeDownloadBinding.inflate(getLayoutInflater());
        toastbind = ToastLayoutBinding.inflate(getLayoutInflater());
        progressBinding = ProgressLayoutBinding.inflate(getLayoutInflater());
        backBinding = DeleteOrNotdeleteLayoutBinding.inflate(getLayoutInflater());
    }

    private void allBottomNavClickListener() {
        binding.btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!istextLAyoutOpened) {
                    binding.editLayout.setVisibility(View.VISIBLE);
                    binding.editLayout.removeAllViewsInLayout();
                    binding.editLayout.addView(textBinding.getRoot());
                    binding.btnText.setBackgroundResource(R.color.clickhold);

                    binding.btnGradient.setBackgroundResource(R.color.transparent);
                    binding.btnBackround.setBackgroundResource(R.color.transparent);
                    binding.btnBorder.setBackgroundResource(R.color.transparent);
                    binding.btnSize.setBackgroundResource(R.color.transparent);
                    istextLAyoutOpened = true;
                } else {
                    binding.editLayout.setVisibility(View.GONE);
                    binding.btnText.setBackgroundResource(R.color.transparent);
                    istextLAyoutOpened = false;
                }

            }
        });
        binding.btnGradient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.VISIBLE);
                binding.editLayout.removeAllViewsInLayout();
                binding.editLayout.addView(gradientBinding.getRoot());
                binding.btnGradient.setBackgroundResource(R.color.clickhold);

                binding.btnText.setBackgroundResource(R.color.transparent);
                binding.btnBackround.setBackgroundResource(R.color.transparent);
                binding.btnBorder.setBackgroundResource(R.color.transparent);
                binding.btnSize.setBackgroundResource(R.color.transparent);

            }
        });
        binding.btnBackround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.VISIBLE);
                binding.editLayout.removeAllViewsInLayout();
                binding.editLayout.addView(backroundBinding.getRoot());
                binding.btnBackround.setBackgroundResource(R.color.clickhold);

                binding.btnText.setBackgroundResource(R.color.transparent);
                binding.btnGradient.setBackgroundResource(R.color.transparent);
                binding.btnBorder.setBackgroundResource(R.color.transparent);
                binding.btnSize.setBackgroundResource(R.color.transparent);


            }
        });
        binding.btnBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.VISIBLE);
                binding.editLayout.removeAllViewsInLayout();
                binding.editLayout.addView(borderBinding.getRoot());
                binding.btnBorder.setBackgroundResource(R.color.clickhold);

                binding.btnText.setBackgroundResource(R.color.transparent);
                binding.btnGradient.setBackgroundResource(R.color.transparent);
                binding.btnBackround.setBackgroundResource(R.color.transparent);
                binding.btnSize.setBackgroundResource(R.color.transparent);

                borderBinding.borderSeekbar.setProgress(10);
                borderBinding.borderTextview.setText("10");
                binding.inclay.downloadLayout.setDividerPadding(10);
            }
        });
        binding.btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.VISIBLE);
                binding.editLayout.removeAllViewsInLayout();
                binding.editLayout.addView(cropBinding.getRoot());
                binding.btnSize.setBackgroundResource(R.color.clickhold);

                binding.btnText.setBackgroundResource(R.color.transparent);
                binding.btnGradient.setBackgroundResource(R.color.transparent);
                binding.btnBackround.setBackgroundResource(R.color.transparent);
                binding.btnBorder.setBackgroundResource(R.color.transparent);

                if (binding.inclay.downloadLayout.getLayoutParams().height == LinearLayout.LayoutParams.MATCH_PARENT) {
                    cropBinding.crophalfsize.setBackgroundResource(R.color.transparent);
                    cropBinding.cropfullsize.setBackgroundResource(R.color.clickhold);
                } else {
                    if (binding.inclay.downloadLayout.getLayoutParams().height == LinearLayout.LayoutParams.WRAP_CONTENT) {
                        cropBinding.crophalfsize.setBackgroundResource(R.color.clickhold);
                        cropBinding.cropfullsize.setBackgroundResource(R.color.transparent);
                    }
                }
            }
        });
    }

    private void backbuttononclick() {
        binding.idbackarrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getintentPoetry() {
        getintentData = getIntent();
        String poetryName = getintentData.getStringExtra("poetryname");
        return poetryName;
    }

    private int getintentGradient() {
        getintentData = getIntent();
        int gradient = getintentData.getIntExtra("gradient", 0);
        return gradient;
    }


    private void textEdittextallwork() {
        textedittextdialogBinding = TextEdittextDialogBinding.inflate(getLayoutInflater());

        text_editext_dialog = new AlertDialog.Builder(StatusViewActivity.this)
                .setView(textedittextdialogBinding.getRoot()).setCancelable(false).create();
        text_editext_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        textBinding.textEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textedittextdialogBinding.textEditextEdit.setText(binding.inclay.mainTextPoetry.getText().toString());
                text_editext_dialog.show();
                textedittextdialogBinding.textEditextEdit.setSelection(textedittextdialogBinding.textEditextEdit.getText().length());
                textBinding.textEdittext.setBackgroundResource(R.color.clickhold);

                textBinding.textColor.setBackgroundResource(R.color.transparent);
                textBinding.textStyle.setBackgroundResource(R.color.transparent);
                textBinding.textSize.setBackgroundResource(R.color.transparent);
                textBinding.textfontStyle.setBackgroundResource(R.color.transparent);
            }
        });

        textedittextdialogBinding.textEditextEdit.requestFocus();


        textedittextdialogBinding.textdialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_editext_dialog.dismiss();
                textBinding.textEdittext.setBackgroundResource(R.color.transparent);
                binding.mainEditLayout.setVisibility(View.GONE);
                binding.superEditLayout.setVisibility(View.VISIBLE);
            }
        });
        textedittextdialogBinding.textDialogright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setText(textedittextdialogBinding.textEditextEdit.getText().toString());
                text_editext_dialog.dismiss();
                textBinding.textEdittext.setBackgroundResource(R.color.transparent);
                binding.mainEditLayout.setVisibility(View.GONE);
                binding.superEditLayout.setVisibility(View.VISIBLE);
            }
        });
    }
    private void textcolorallwork() {

        textColorLayoutBinding = TextColorLayoutBinding.inflate(getLayoutInflater());
        TextColorPickerBinding textColorPickerBinding = TextColorPickerBinding.inflate(getLayoutInflater());
        textcolorDialog = new AlertDialog.Builder(StatusViewActivity.this)
                .setView(textColorPickerBinding.getRoot())
                .create();

        getAllColors getAllColors = new getAllColors();
        TextColorAdapters colorAdapters = new TextColorAdapters(StatusViewActivity.this, binding.inclay.mainTextPoetry, getAllColors.getmaincolor(), textcolorDialog, textColorPickerBinding);
        textColorLayoutBinding.textcolorrecyclerview.setAdapter(colorAdapters);
        LinearLayoutManager textcolorlm = new LinearLayoutManager(StatusViewActivity.this, RecyclerView.HORIZONTAL, false);
        textColorLayoutBinding.textcolorrecyclerview.setLayoutManager(textcolorlm);

        textColorPickerBinding.textcolordialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textcolorDialog.dismiss();
            }
        });


        textBinding.textColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mainEditLayout.removeAllViewsInLayout();
                binding.mainEditLayout.addView(textColorLayoutBinding.getRoot());
                binding.superEditLayout.setVisibility(View.GONE);
                binding.mainEditLayout.setVisibility(View.VISIBLE);

                textBinding.textColor.setBackgroundResource(R.color.clickhold);

                textBinding.textEdittext.setBackgroundResource(R.color.transparent);
                textBinding.textSize.setBackgroundResource(R.color.transparent);
                textBinding.textStyle.setBackgroundResource(R.color.transparent);
                textBinding.textfontStyle.setBackgroundResource(R.color.transparent);
            }
        });

        textColorLayoutBinding.textcolorwrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setTextColor(defaulttext_color);
                binding.mainEditLayout.setVisibility(View.GONE);
                binding.superEditLayout.setVisibility(View.VISIBLE);

                textBinding.textColor.setBackgroundResource(R.color.transparent);
            }
        });
        textColorLayoutBinding.textcolorright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.superEditLayout.setVisibility(View.VISIBLE);
                binding.mainEditLayout.setVisibility(View.GONE);
                defaulttext_color = binding.inclay.mainTextPoetry.getCurrentTextColor();

                textBinding.textColor.setBackgroundResource(R.color.transparent);
            }
        });
    }
    private void textfontsallwork() {
        defaulttextfont_typface = ResourcesCompat.getFont(StatusViewActivity.this, R.font.alike_angular);
        textFontLayoutBinding = TextFontLayoutBinding.inflate(getLayoutInflater());

        GetAllFonts getAllFonts = new GetAllFonts();
        TextFontsAdapters textFontsAdapters = new TextFontsAdapters(StatusViewActivity.this, getAllFonts.fontlist(), binding.inclay.mainTextPoetry);
        textFontLayoutBinding.textfontrecyclerview.setAdapter(textFontsAdapters);
        textFontLayoutBinding.textfontrecyclerview.setLayoutManager(new LinearLayoutManager(StatusViewActivity.this, RecyclerView.HORIZONTAL, false));

        textBinding.textfontStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mainEditLayout.removeAllViewsInLayout();
                binding.mainEditLayout.addView(textFontLayoutBinding.getRoot());
                binding.superEditLayout.setVisibility(View.GONE);
                binding.mainEditLayout.setVisibility(View.VISIBLE);
                textBinding.textfontStyle.setBackgroundResource(R.color.clickhold);

                textBinding.textColor.setBackgroundResource(R.color.transparent);
                textBinding.textEdittext.setBackgroundResource(R.color.transparent);
                textBinding.textSize.setBackgroundResource(R.color.transparent);
                textBinding.textStyle.setBackgroundResource(R.color.transparent);
            }
        });

        textFontLayoutBinding.textfontwrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setTypeface(defaulttextfont_typface);
                binding.mainEditLayout.setVisibility(View.GONE);
                binding.superEditLayout.setVisibility(View.VISIBLE);

                textBinding.textfontStyle.setBackgroundResource(R.color.transparent);
            }
        });
        textFontLayoutBinding.textfontright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.superEditLayout.setVisibility(View.VISIBLE);
                binding.mainEditLayout.setVisibility(View.GONE);
                defaulttextfont_typface = binding.inclay.mainTextPoetry.getTypeface();

                textBinding.textfontStyle.setBackgroundResource(R.color.transparent);
            }
        });

    }
    private void textsizeallwork() {

        textSizeLayoutBinding = TextSizeLayoutBinding.inflate(getLayoutInflater());
        float gettextsize = binding.inclay.mainTextPoetry.getTextSize();
        textSizeLayoutBinding.textsizeSeekbar.setMax(40);
        textSizeLayoutBinding.textsizeSeekbar.setProgress(20);
        textSizeLayoutBinding.textsizetextView.setText(String.valueOf(20));

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                textSizeLayoutBinding.textsizeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        textSizeLayoutBinding.textsizetextView.setText(String.valueOf(progress));
                        binding.inclay.mainTextPoetry.setTextSize((float) progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });


        textBinding.textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mainEditLayout.setVisibility(View.VISIBLE);
                binding.mainEditLayout.removeAllViewsInLayout();
                binding.mainEditLayout.addView(textSizeLayoutBinding.getRoot());
                binding.superEditLayout.setVisibility(View.GONE);

                textBinding.textSize.setBackgroundResource(R.color.clickhold);

                textBinding.textStyle.setBackgroundResource(R.color.transparent);
                textBinding.textColor.setBackgroundResource(R.color.transparent);
                textBinding.textEdittext.setBackgroundResource(R.color.transparent);
                textBinding.textfontStyle.setBackgroundResource(R.color.transparent);
            }
        });

        textSizeLayoutBinding.textsizewrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setTextSize((float) defaulttext_size);
                textSizeLayoutBinding.textsizetextView.setText(String.valueOf(defaulttext_size));
                textSizeLayoutBinding.textsizeSeekbar.setProgress(defaulttext_size);

                binding.mainEditLayout.setVisibility(View.GONE);
                textBinding.textSize.setBackgroundResource(R.color.transparent);
                binding.superEditLayout.setVisibility(View.VISIBLE);

            }
        });

        textSizeLayoutBinding.textsizeright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gettext = textSizeLayoutBinding.textsizetextView.getText().toString();
                int textsize = Integer.parseInt(gettext);
                defaulttext_size = textsize;

                binding.mainEditLayout.setVisibility(View.GONE);
                binding.superEditLayout.setVisibility(View.VISIBLE);
                textBinding.textSize.setBackgroundResource(R.color.transparent);

            }
        });

    }
    private void textstyleallwork() {
        textStyleLayoutBinding = TextStyleLayoutBinding.inflate(getLayoutInflater());
        defaulttextfont_typface = binding.inclay.mainTextPoetry.getTypeface();
        textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.clickhold);

        textBinding.textStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mainEditLayout.setVisibility(View.VISIBLE);
                binding.mainEditLayout.removeAllViewsInLayout();
                binding.mainEditLayout.addView(textStyleLayoutBinding.getRoot());
                binding.superEditLayout.setVisibility(View.GONE);
                textBinding.textStyle.setBackgroundResource(R.color.clickhold);

                textBinding.textSize.setBackgroundResource(R.color.transparent);
                textBinding.textColor.setBackgroundResource(R.color.transparent);
                textBinding.textfontStyle.setBackgroundResource(R.color.transparent);
                textBinding.textEdittext.setBackgroundResource(R.color.transparent);

                if (binding.inclay.mainTextPoetry.getTypeface() == Typeface.create(defaulttextfont_typface, Typeface.NORMAL)) {
                    textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.clickhold);

                    textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.transparent);

                } else if (binding.inclay.mainTextPoetry.getTypeface() == Typeface.create(defaulttextfont_typface, Typeface.ITALIC)) {
                    textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.clickhold);

                    textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.transparent);

                } else if (binding.inclay.mainTextPoetry.getTypeface() == Typeface.create(defaulttextfont_typface, Typeface.BOLD)) {
                    textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.clickhold);

                    textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.transparent);

                } else if (binding.inclay.mainTextPoetry.getTypeface() == Typeface.create(defaulttextfont_typface, Typeface.BOLD_ITALIC)) {
                    textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.clickhold);

                    textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.transparent);
                    textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.transparent);
                }
            }
        });

        textStyleLayoutBinding.textstylewrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.superEditLayout.setVisibility(View.VISIBLE);
                binding.mainEditLayout.setVisibility(View.GONE);
                textBinding.textStyle.setBackgroundResource(R.color.clickhold);
                binding.inclay.mainTextPoetry.setTypeface(defaulttextstyle_font);

                textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.transparent);
                textBinding.textStyle.setBackgroundResource(R.color.transparent);

            }
        });

        textStyleLayoutBinding.textstyleright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.superEditLayout.setVisibility(View.VISIBLE);
                binding.mainEditLayout.setVisibility(View.GONE);
                textBinding.textStyle.setBackgroundResource(R.color.clickhold);
                textBinding.textStyle.setBackgroundResource(R.color.transparent);
                defaulttextstyle_font = binding.inclay.mainTextPoetry.getTypeface();
            }
        });

        textStyleLayoutBinding.textstylenormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setTypeface(defaulttextfont_typface, Typeface.NORMAL);
                textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.clickhold);

                textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.transparent);
            }
        });
        textStyleLayoutBinding.textstyleitalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setTypeface(defaulttextfont_typface, Typeface.ITALIC);
                textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.clickhold);

                textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.transparent);
            }
        });
        textStyleLayoutBinding.textstylebold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setTypeface(defaulttextfont_typface, Typeface.BOLD);
                textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.clickhold);

                textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.transparent);
            }
        });
        textStyleLayoutBinding.textstyleitalicbold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inclay.mainTextPoetry.setTypeface(defaulttextfont_typface, Typeface.BOLD_ITALIC);
                textStyleLayoutBinding.textstyleitalicbold.setBackgroundResource(R.color.clickhold);

                textStyleLayoutBinding.textstylebold.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstyleitalic.setBackgroundResource(R.color.transparent);
                textStyleLayoutBinding.textstylenormal.setBackgroundResource(R.color.transparent);
            }
        });
    }

    private void maingradientallwork() {
        default_gradient = binding.inclay.mainLayout.getBackground();
        GetAllGradient getAllGradient = new GetAllGradient();
        int[] gradarray = getAllGradient.getGradientArray();

        GradientAdapter gadapter = new GradientAdapter(binding.inclay.mainLayout, StatusViewActivity.this, gradarray);
        LinearLayoutManager gradientLM = new LinearLayoutManager(StatusViewActivity.this, RecyclerView.HORIZONTAL, false);
        gradientBinding.gradientrecyclerview.setAdapter(gadapter);
        gradientBinding.gradientrecyclerview.setLayoutManager(gradientLM);

        gradientBinding.gradientwrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.GONE);
                binding.btnGradient.setBackgroundResource(R.color.transparent);
                binding.inclay.mainLayout.setBackground(default_gradient);
            }
        });

        gradientBinding.gradientright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.GONE);
                binding.btnGradient.setBackgroundResource(R.color.transparent);
                default_gradient = binding.inclay.mainLayout.getBackground();

            }
        });

    }
    private void mainbackroundallwork() {
        backroundDrawable = binding.inclay.mainLayout.getBackground();
        GetAllBackround getAllBackround = new GetAllBackround();
        BackroundAdapter bgadapter = new BackroundAdapter(binding.inclay.mainLayout, StatusViewActivity.this, getAllBackround.allBackround());
        LinearLayoutManager bgLM = new LinearLayoutManager(StatusViewActivity.this, RecyclerView.HORIZONTAL, false);
        backroundBinding.backroundRecyclerView.setAdapter(bgadapter);
        backroundBinding.backroundRecyclerView.setLayoutManager(bgLM);

        backroundBinding.backroundWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.GONE);
                binding.btnBackround.setBackgroundResource(R.color.transparent);
                binding.inclay.mainLayout.setBackground(default_gradient);
            }
        });

        backroundBinding.backroundRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editLayout.setVisibility(View.GONE);
                binding.btnBackround.setBackgroundResource(R.color.transparent);
                default_gradient = binding.inclay.mainLayout.getBackground();

            }
        });

        backroundBinding.idbackroundaddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(StatusViewActivity.this)
                        .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                    Intent imageAddIntent = new Intent(Intent.ACTION_PICK);
                                    imageAddIntent.setType("image/*");
                                    startActivityForResult(imageAddIntent, BACKROUND_IMAGE_ADD_REQUESTCODE);

                                } else {
                                    finish();
                                    Toast.makeText(StatusViewActivity.this, "Allow Permission", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        })
                        .check();
            }
        });

    }
    private void mainborderallwork() {
        default_borderpadding = 0;

        bordercolorpickerdialog();
        borderBinding.borderSeekbar.setMax(100);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                borderBinding.borderSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        borderBinding.borderTextview.setText(String.valueOf(progress));
                        binding.inclay.downloadLayout.setPadding(progress, progress, progress, progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });

        borderBinding.borderWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnBorder.setBackgroundResource(R.color.transparent);
                binding.editLayout.setVisibility(View.GONE);

                binding.inclay.downloadLayout.setPadding(default_borderpadding, default_borderpadding, default_borderpadding, default_borderpadding);
                borderBinding.borderTextview.setText(String.valueOf(default_borderpadding));
                borderBinding.borderSeekbar.setProgress(default_borderpadding);
            }
        });
        borderBinding.borderright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnBorder.setBackgroundResource(R.color.transparent);
                binding.editLayout.setVisibility(View.GONE);

                default_borderpadding = binding.inclay.downloadLayout.getPaddingTop();
                borderBinding.borderTextview.setText(String.valueOf(default_borderpadding));
                borderBinding.borderSeekbar.setProgress(default_borderpadding);
            }
        });

    }
    private void bordercolorpickerdialog() {
        colorpickerDialogBinding = ColorpickerDialogBinding.inflate(getLayoutInflater());
        borderalertdialog = new AlertDialog.Builder(StatusViewActivity.this)
                .setView(colorpickerDialogBinding.getRoot()).create();
        borderalertdialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

        colorpickerDialogBinding.borderdialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borderalertdialog.dismiss();
            }
        });

        borderBinding.btnColorDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borderalertdialog.show();

            }
        });

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                colorpickerDialogBinding.colorPickerView.setColorListener(new ColorEnvelopeListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                        colorpickerDialogBinding.card465.setCardBackgroundColor(envelope.getColor());
                        okbordercolor = envelope.getColor();
                        binding.inclay.downloadLayout.setBackgroundColor(envelope.getColor());
                        borderBinding.colorPutcard.setCardBackgroundColor(envelope.getColor());
                    }
                });
            }
        });

    }
    private void mainsizeallwork() {
        default_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        cropBinding.cropfullsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropBinding.cropfullsize.setBackgroundResource(R.color.clickhold);
                cropBinding.crophalfsize.setBackgroundResource(R.color.transparent);

                binding.inclay.downloadLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            }
        });
        cropBinding.crophalfsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropBinding.cropfullsize.setBackgroundResource(R.color.transparent);
                cropBinding.crophalfsize.setBackgroundResource(R.color.clickhold);

                binding.inclay.downloadLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        });


        cropBinding.cropWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnSize.setBackgroundResource(R.color.transparent);
                binding.editLayout.setVisibility(View.GONE);

                binding.inclay.downloadLayout.setLayoutParams(default_params);
            }
        });
        cropBinding.cropRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnSize.setBackgroundResource(R.color.transparent);
                binding.editLayout.setVisibility(View.GONE);
                default_params = (LinearLayout.LayoutParams) binding.inclay.downloadLayout.getLayoutParams();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == BACKROUND_IMAGE_ADD_REQUESTCODE) {
            if (data.getData() != null) {
                Uri selectedImagePath = data.getData();
                File f = new File(getRealPathFromURI(selectedImagePath));
                backroundDrawable = Drawable.createFromPath(f.getAbsolutePath());
                binding.inclay.mainLayout.setBackground(backroundDrawable);
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    private void getsharedprefrence() {
        SharedPreferences getshared = getSharedPreferences(getString(R.string.dbTheme), MODE_PRIVATE);
        gettheme = getshared.getString(getString(R.string.backroundKey), "theme1");
    }

    private void themesettingsharedprefrencechack() {
        switch (gettheme) {
            case "theme1":
                binding.editparentLayout.setBackgroundResource(R.drawable.main_back);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor1));
                break;
            case "theme2":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_2);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor2));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor2));
                break;
            case "theme3":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_3);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor3));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor3));
                break;
            case "theme4":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_4);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor4));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor4));
                break;
            case "theme5":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_5);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor5));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor5));
                break;
            case "theme6":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_6);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor6));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor6));
                break;
            case "theme7":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_7);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor7));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor7));
                break;
            case "theme8":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_8);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor8));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor8));
                break;
            case "theme9":
                binding.editparentLayout.setBackgroundResource(R.drawable.backround_9);
                getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor9));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.navcolor9));
                break;
        }
    }

    private void fontchackinSharedPrefrence() {
        SharedPreferences fontPref = getSharedPreferences(getString(R.string.dbfont), MODE_PRIVATE);
        String getfont = fontPref.getString(getString(R.string.fontKey), "font1");
        switch (getfont) {
            case "font1":
                Typeface typeface1 = ResourcesCompat.getFont(StatusViewActivity.this, R.font.anonymous_pro);
                binding.inclay.mainTextPoetry.setTypeface(typeface1);
                break;
            case "font2":
                Typeface typeface2 = ResourcesCompat.getFont(StatusViewActivity.this, R.font.amarante);
                binding.inclay.mainTextPoetry.setTypeface(typeface2);
                break;
            case "font3":
                Typeface typeface3 = ResourcesCompat.getFont(StatusViewActivity.this, R.font.love_ya_like_a_sister);
                binding.inclay.mainTextPoetry.setTypeface(typeface3);
                break;
            case "font4":
                Typeface typeface4 = ResourcesCompat.getFont(StatusViewActivity.this, R.font.alike_angular);
                binding.inclay.mainTextPoetry.setTypeface(typeface4);
                break;
            case "font5":
                Typeface typeface5 = ResourcesCompat.getFont(StatusViewActivity.this, R.font.special_elite);
                binding.inclay.mainTextPoetry.setTypeface(typeface5);
                break;
            case "font6":
                Typeface typeface6 = ResourcesCompat.getFont(StatusViewActivity.this, R.font.short_stack);
                binding.inclay.mainTextPoetry.setTypeface(typeface6);
                break;

        }

    }

    private void createbackbuttonDialog(){
        backDialog = new AlertDialog.Builder(StatusViewActivity.this)
                .setView(backBinding.getRoot()).create();
        backDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    @Override
    public void onBackPressed() {
        if (!onBackpressed) {
            backBinding.dIcon.setImageResource(R.drawable.icon_exit);
            backBinding.dTitle.setText("Exit");
            backBinding.dMessage.setText(getString(R.string.backMessage));
            backBinding.btndCancel.setText("Continue");
            backBinding.btndDelete.setText("Discard");

            backBinding.btndDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            backBinding.btndCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backDialog.dismiss();
                }
            });
            backDialog.show();
        }else {
            finish();
        }
    }

    private void loadinterstitialads() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getString(R.string.interestialAds), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAd.show(StatusViewActivity.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Toast.makeText(StatusViewActivity.this, loadAdError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}