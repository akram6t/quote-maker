package com.dlpruniqe.beststatus.basicadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.databinding.LayoutTextFontItemBinding;

public class TextFontsAdapters extends RecyclerView.Adapter<TextFontsAdapters.TextfontHolder>{
    Context context;
    int [] fontlist;
    TextView textView;

    public TextFontsAdapters(Context context, int[] fontlist, TextView textView) {
        this.context = context;
        this.fontlist = fontlist;
        this.textView = textView;
    }

    @NonNull
    @Override
    public TextfontHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View textfontview = LayoutInflater.from(context).inflate(R.layout.layout_text_font_item,null);
        return new TextfontHolder(textfontview);
    }

    @Override
    public void onBindViewHolder(@NonNull TextfontHolder textfontholder, @SuppressLint("RecyclerView") int position) {
        Typeface tf = ResourcesCompat.getFont(context, fontlist[position]);
        textfontholder.layoutTextFontItemBinding.textfontitem.setTypeface(tf);
        textfontholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTypeface(tf);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fontlist.length;
    }

    public class TextfontHolder extends RecyclerView.ViewHolder {
        LayoutTextFontItemBinding layoutTextFontItemBinding;
        public TextfontHolder(@NonNull View itemView) {
            super(itemView);
            layoutTextFontItemBinding = LayoutTextFontItemBinding.bind(itemView);
        }
    }
}
