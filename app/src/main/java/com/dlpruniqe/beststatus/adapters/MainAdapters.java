package com.dlpruniqe.beststatus.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.dlpruniqe.beststatus.activities.StatusListActivity;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.models.MainModels;
import java.util.ArrayList;

public class MainAdapters extends RecyclerView.Adapter<MainAdapters.mHolder>{
    Context context;
    ArrayList<MainModels> mainList;
    public MainAdapters(Context context, ArrayList<MainModels> mainList){
        this.mainList = mainList;
        this.context = context;
    }

    @NonNull
    @Override
    public mHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_item_recycler,parent, false);
        return new mHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mHolder holder, int position) {
        MainModels mainModels = mainList.get(position);
        holder.categoryname.setText(mainModels.getName());
        holder.clickitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent = new Intent(context, StatusListActivity.class);
                mintent.putExtra("name",mainModels.getName().replace("\n", " "));
                mintent.putExtra("key",mainModels.getKey());
                context.startActivity(mintent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class mHolder extends RecyclerView.ViewHolder {
        TextView categoryname;
        ConstraintLayout clickitem;
        public mHolder(@NonNull View itemView) {
            super(itemView);
            categoryname = itemView.findViewById(R.id.categoryname);
            clickitem = itemView.findViewById(R.id.clickview);
        }
    }
}


