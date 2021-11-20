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
import com.dlpruniqe.beststatus.databinding.LayoutGradientItemBinding;

public class GradientAdapter extends RecyclerView.Adapter<GradientAdapter.gradientHolder> {
    LinearLayout gradlayout;
    Context context;
    int [] gradientList;

    public GradientAdapter(LinearLayout gradlayout, Context context, int[] gradientList) {
        this.gradlayout = gradlayout;
        this.context = context;
        this.gradientList = gradientList;
    }

    @NonNull
    @Override
    public gradientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gradView = LayoutInflater.from(context).inflate(R.layout.layout_gradient_item,null);
        return new gradientHolder(gradView);
    }

    @Override
    public void onBindViewHolder(@NonNull gradientHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.gradientItemBinding.drawableLayout.setBackgroundResource(gradientList[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    gradlayout.setBackgroundResource(gradientList[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gradientList.length;
    }

    public class gradientHolder extends RecyclerView.ViewHolder {
        LayoutGradientItemBinding gradientItemBinding;
        public gradientHolder(@NonNull View itemView) {
            super(itemView);
            gradientItemBinding = LayoutGradientItemBinding.bind(itemView);
        }
    }
}
