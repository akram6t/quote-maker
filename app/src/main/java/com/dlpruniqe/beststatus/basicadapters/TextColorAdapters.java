package com.dlpruniqe.beststatus.basicadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.databinding.LayoutTextColorItem2Binding;
import com.dlpruniqe.beststatus.databinding.LayoutTextColorItemBinding;
import com.dlpruniqe.beststatus.databinding.TextColorPickerBinding;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class TextColorAdapters extends RecyclerView.Adapter{
    Context context;
    TextView textView;
    int [] colorlist;
    AlertDialog textcolordialog;
    int firstView = 1, secondView = 2;
    TextColorPickerBinding textColorPickerBinding;

    public TextColorAdapters(Context context, TextView textView, int[] colorlist, AlertDialog textcolordialog, TextColorPickerBinding textColorPickerBinding) {
        this.context = context;
        this.textView = textView;
        this.colorlist = colorlist;
        this.textcolordialog = textcolordialog;
        this.textColorPickerBinding = textColorPickerBinding;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View textcolorView2 = LayoutInflater.from(context).inflate(R.layout.layout_text_color_item2,null);
        View textcolorView1 = LayoutInflater.from(context).inflate(R.layout.layout_text_color_item,null);
        if (viewType == firstView){
            return new TextColorHolder1(textcolorView1);
        }else {
            return new TextColorHolder2(textcolorView2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        textcolordialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

        if (holder.getClass() == TextColorHolder1.class){
            TextColorHolder1 textColorHolder1 = (TextColorHolder1) holder;
            textColorHolder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textcolordialog.show();
                }
            });

            textColorPickerBinding.textcolorPickerView.setColorListener(new ColorEnvelopeListener() {
                @Override
                public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                    textColorHolder1.textColorItemBinding.dtextcoloritem.setBackgroundColor(envelope.getColor());
                    textView.setTextColor(envelope.getColor());
                    textColorPickerBinding.textcolorcard.setCardBackgroundColor(envelope.getColor());
                }
            });

        }else {
            if (holder.getClass() == TextColorHolder2.class){
                TextColorHolder2 textColorHolder2 = (TextColorHolder2) holder;
                textColorHolder2.textColorItem2Binding.textcoloritem.setBackgroundColor(colorlist[pos]);

                textColorHolder2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setTextColor(colorlist[pos]);
                    }
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (colorlist[position] == R.drawable.icon_more_color){
            return firstView;
        }else {
            return secondView;
        }
    }

    @Override
    public int getItemCount() {
        return colorlist.length;
    }



     class TextColorHolder1 extends RecyclerView.ViewHolder {
        LayoutTextColorItemBinding textColorItemBinding;
        public TextColorHolder1(@NonNull View itemView) {
            super(itemView);
            textColorItemBinding = LayoutTextColorItemBinding.bind(itemView);
        }
    }
     class TextColorHolder2 extends RecyclerView.ViewHolder {
        LayoutTextColorItem2Binding textColorItem2Binding;
        public TextColorHolder2(@NonNull View itemView) {
            super(itemView);
            textColorItem2Binding = LayoutTextColorItem2Binding.bind(itemView);
        }
    }

}
