package com.dlpruniqe.beststatus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dlpruniqe.beststatus.R;
import com.dlpruniqe.beststatus.models.MyStatusModels;
import com.dlpruniqe.beststatus.other.CToast;

import java.util.ArrayList;

public class SQlHelper extends SQLiteOpenHelper {
    Context context;
    ArrayList<MyStatusModels> statusList;
    private static final int DATABASE_VIRSION = 1;
    private static final String DATABASE_NAME = "mystatus.db";

    private static final String TABLE_NAME = "tablemystatus";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "status TEXT,"+
                    "date TEXT)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VIRSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void addStatus(String status, String date){

        SQLiteDatabase adddata = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("date", date );
        adddata.insert(TABLE_NAME ,null, values);
        adddata.close();
    }

    public ArrayList<MyStatusModels> readStatusList(){
        statusList = new ArrayList<>();
        SQLiteDatabase readdata = this.getReadableDatabase();
        Cursor cursor = readdata.rawQuery("select * from "+ TABLE_NAME+" ORDER BY id DESC", null);

        if (cursor.moveToFirst()){
            do {
                MyStatusModels models = new MyStatusModels(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                statusList.add(models);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        return statusList;
    }

    public void updateStatus(String id, String upStatus, String upDate){
        SQLiteDatabase updatedata = this.getWritableDatabase();
        ContentValues upVlaues = new ContentValues();
        upVlaues.put("status", upStatus);
        upVlaues.put("date", upDate);
        updatedata.update(TABLE_NAME, upVlaues, "id=?", new String[]{id});
    }

    public void deleteStatus(String id){
        SQLiteDatabase deletedata = this.getWritableDatabase();
        deletedata.delete(TABLE_NAME, "id=?", new String[]{id});
        CToast cToast = new CToast(context);
        cToast.setText("Successfully deleted");
        cToast.iconVisible(true);
        cToast.setIcon(R.drawable.icon_right);
        cToast.show();
    }


}
