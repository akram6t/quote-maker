package com.dlpruniqe.beststatus.adapters;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.activities.StatusViewActivity;
import com.dlpruniqe.beststatus.database.SQlHelper;
import com.dlpruniqe.beststatus.databinding.DeleteOrNotdeleteLayoutBinding;
import com.dlpruniqe.beststatus.databinding.MystatusEditLayoutBinding;
import com.dlpruniqe.beststatus.databinding.MystatusListItemBinding;
import com.dlpruniqe.beststatus.models.MyStatusModels;
import com.dlpruniqe.beststatus.other.CToast;
import com.dlpruniqe.beststatus.other.GetAllGradient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MyStatusAdapter extends RecyclerView.Adapter<MyStatusAdapter.statusHolder>{
    private Context context;
    private ArrayList<MyStatusModels> modelsList;
    private AlertDialog dDialog;
    private DeleteOrNotdeleteLayoutBinding deleteBinding;
    MystatusEditLayoutBinding editBind;
    AlertDialog editdialog;

    public MyStatusAdapter(Context context, ArrayList<MyStatusModels> modelsList, AlertDialog dialog, DeleteOrNotdeleteLayoutBinding deleteBinding, MystatusEditLayoutBinding editBind, AlertDialog editdialog) {
        this.context = context;
        this.modelsList = modelsList;
        this.dDialog = dialog;
        this.deleteBinding = deleteBinding;
        this.editBind = editBind;
        this.editdialog = editdialog;
    }

    @NonNull
    @Override
    public statusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View statusListView = LayoutInflater.from(context).inflate(R.layout.mystatus_list_item, parent, false);
        return new statusHolder(statusListView);
    }

    @Override
    public void onBindViewHolder(@NonNull statusHolder holder, int position) {
        MyStatusModels models = modelsList.get(position);
        holder.statusbind.myTextView.setText(models.getStatus());
        holder.statusbind.statusDate.setText(models.getDate());
        holder.statusbind.myGradLayout.setBackgroundResource(new GetAllGradient().getGradientArray()[new Random().nextInt(new GetAllGradient().getGradientArray().length)]);

        holder.itemView.setTranslationX(-500f);
        holder.itemView.animate().translationX(0).setDuration(1000).start();

        holder.statusbind.clickitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sintent = new Intent(context, StatusViewActivity.class);
                sintent.putExtra(context.getString(R.string.ismystatus), true);
                sintent.putExtra(context.getString(R.string.mystatustext), models.getStatus());
                context.startActivity(sintent);
            }
        });


        holder.statusbind.btnmystatusDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dDialog.show();
                deleteBinding.btndCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dDialog.dismiss();
                    }
                });
                deleteBinding.btndDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQlHelper dsQlHelper = new SQlHelper(context);
                        dsQlHelper.deleteStatus(String.valueOf(models.getId()));
                        CToast cToast = new CToast(context);
                        cToast.setText("Successfully Deleted");
                        cToast.iconVisible(true);
                        cToast.setIcon(R.drawable.icon_right);
                        cToast.show();
                        dDialog.dismiss();
                    }
                });
            }
        });

        holder.statusbind.myStatusCopied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager mclm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mclpData = ClipData.newPlainText("textCopied", models.getStatus());
                mclm.setPrimaryClip(mclpData);
                CToast cToast = new CToast(context);
                cToast.iconVisible(true);
                cToast.setIcon(R.drawable.icon_copy);
                cToast.setText("Copied...");
                cToast.show();
            }
        });

        holder.statusbind.btnmystatusEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBind.editdialogEdittext.setText(models.getStatus());
                editBind.editdialogEdittext.requestFocus();
                editBind.editdialogEdittext.setSelection(editBind.editdialogEdittext.getText().toString().length());
                editdialog.show();
            }
        });
        editBind.editdialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editdialog.dismiss();
            }
        });

        editBind.editdialogRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQlHelper esqlHelper = new SQlHelper(context);
                String upStatus = editBind.editdialogEdittext.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String upDate = sdf.format(new Date());
                esqlHelper.updateStatus(String.valueOf(models.getId()), upStatus, upDate);
                CToast cToast = new CToast(context);
                cToast.setText(context.getString(R.string.updateinmyStatus));
                cToast.setIcon(R.drawable.icon_edit);
                cToast.iconVisible(true);
                cToast.show();
                editdialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelsList.size();
    }

    public class statusHolder extends RecyclerView.ViewHolder {
        MystatusListItemBinding statusbind;
        public statusHolder(@NonNull View sitemView) {
            super(sitemView);
            statusbind = MystatusListItemBinding.bind(sitemView);
        }
    }
}
