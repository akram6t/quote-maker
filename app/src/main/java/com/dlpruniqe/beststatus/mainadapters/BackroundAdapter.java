package com.dlpruniqe.beststatus.mainadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.databinding.LayoutBackroundItemBinding;

public class BackroundAdapter extends RecyclerView.Adapter<BackroundAdapter.backroundHolder> {
    LinearLayout backroundLayout;
    Context bcontext;
    int[] backroundList;

    public BackroundAdapter(LinearLayout backroundLayout, Context bcontext, int[] backroundList) {
        this.backroundLayout = backroundLayout;
        this.bcontext = bcontext;
        this.backroundList = backroundList;
    }

    @NonNull
    @Override
    public backroundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View backroundView = LayoutInflater.from(bcontext).inflate(R.layout.layout_backround_item, null);
        return new backroundHolder(backroundView);
    }

    @Override
    public void onBindViewHolder(@NonNull backroundHolder backroundHolder, @SuppressLint("RecyclerView") int position) {

        backroundHolder.layoutBackroundItemBinding.backroundLayout.setBackgroundResource(backroundList[position]);

        backroundHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    backroundLayout.setBackgroundResource(backroundList[position]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return backroundList.length;
    }

    public class backroundHolder extends RecyclerView.ViewHolder {
        LayoutBackroundItemBinding layoutBackroundItemBinding;

        public backroundHolder(@NonNull View itemView) {
            super(itemView);
            layoutBackroundItemBinding = LayoutBackroundItemBinding.bind(itemView);
        }
    }
}