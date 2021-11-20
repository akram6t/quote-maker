package com.dlpruniqe.beststatus.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.databinding.ToastLayoutBinding;

public class CToast {
    private final ToastLayoutBinding toastLayoutBinding;
    private final Toast toast;

    public CToast(Context context){

        View tView = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        toastLayoutBinding = ToastLayoutBinding.bind(tView);
        toastLayoutBinding.toastIcon.setVisibility(View.GONE);
        toastLayoutBinding.toastIcon.setVisibility(View.GONE);

        toast = new Toast(context);
        toast.setView(toastLayoutBinding.getRoot());
    }

    public void setText(String text){
        toastLayoutBinding.toastText.setText(text);
    }
    public void setIcon(@DrawableRes int icon){
        toastLayoutBinding.toastIcon.setImageResource(icon);
    }
    public void iconVisible(boolean isVisible){
        if (isVisible){
            toastLayoutBinding.toastIcon.setVisibility(View.VISIBLE);
        }else {
            toastLayoutBinding.toastIcon.setVisibility(View.GONE);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void setTextColor(int color){
        toastLayoutBinding.toastText.setTextColor(color);
    }
    public void setBackroundColor(int color){
        toastLayoutBinding.backroundLayout.setBackgroundResource(color);
    }
    public void show(){
        toast.show();
    }

}
