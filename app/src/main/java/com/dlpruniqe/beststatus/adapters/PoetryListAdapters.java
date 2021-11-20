package com.dlpruniqe.beststatus.adapters;

import static android.content.Context.MODE_PRIVATE;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.dlpruniqe.beststatus.activities.StatusViewActivity;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.models.PoetryListModels;
import com.dlpruniqe.beststatus.other.CToast;
import java.util.ArrayList;

public class PoetryListAdapters extends RecyclerView.Adapter<PoetryListAdapters.listHolder>{
    ArrayList<PoetryListModels> list;
    Context context;
    String getfont;
    CToast cToast;

    public PoetryListAdapters(Context context, ArrayList<PoetryListModels> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public listHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poetry_recycler_list,parent,false);
        return new listHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listHolder holder, int position) {
        PoetryListModels models = list.get(position);
        holder.poetryName.setText(models.getPname());
        holder.grad_layout.setBackgroundResource(models.getGradient());

        holder.clickitemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent(context, StatusViewActivity.class);
                viewintent.putExtra("id", models.getId());
                viewintent.putExtra("poetryname",models.getPname());
                viewintent.putExtra("gradient",models.getGradient());
                context.startActivity(viewintent);
            }
        });

        holder.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("CopiedText", models.getPname());
                clipboard.setPrimaryClip(clip);
                cToast = new CToast(context);
                cToast.iconVisible(true);
                cToast.setIcon(R.drawable.icon_copy);
                cToast.setText("Copied ...");
                cToast.show();
            }
        });

        holder.itemView.setTranslationX(1000f);
        holder.itemView.animate().translationX(0).setDuration(1000).start();

        SharedPreferences getfontchackprefrence = context.getSharedPreferences(context.getString(R.string.dbfont),MODE_PRIVATE);
        getfont = getfontchackprefrence.getString(context.getString(R.string.fontKey), "font1");

        switch (getfont){
            case "font1":
                Typeface typeface1 = ResourcesCompat.getFont(context, R.font.anonymous_pro);
                holder.poetryName.setTypeface(typeface1);
                break;
            case "font2":
                Typeface typeface2 = ResourcesCompat.getFont(context, R.font.amarante);
                holder.poetryName.setTypeface(typeface2);
                break;
            case "font3":
                Typeface typeface3 = ResourcesCompat.getFont(context, R.font.love_ya_like_a_sister);
                holder.poetryName.setTypeface(typeface3);
                break;
            case "font4":
                Typeface typeface4 = ResourcesCompat.getFont(context, R.font.alike_angular);
                holder.poetryName.setTypeface(typeface4);
                break;
            case "font5":
                Typeface typeface5 = ResourcesCompat.getFont(context, R.font.special_elite);
                holder.poetryName.setTypeface(typeface5);
                break;
            case "font6":
                Typeface typeface6 = ResourcesCompat.getFont(context, R.font.short_stack);
                holder.poetryName.setTypeface(typeface6);
                break;

        }

    };

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class listHolder extends RecyclerView.ViewHolder {
        TextView poetryName;
        ImageView btnCopy;
        ConstraintLayout grad_layout,clickitemview;
        public listHolder(@NonNull View itemView) {
            super(itemView);
            poetryName = itemView.findViewById(R.id.poetry_text);
            grad_layout = itemView.findViewById(R.id.grad_layout);
            clickitemview = itemView.findViewById(R.id.clickitem);
            btnCopy = itemView.findViewById(R.id.textCopied);
        }
    }
}