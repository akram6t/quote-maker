package com.dlpruniqe.beststatus.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewToUriConverter {
        private final Context context;
        private final String uriFileName;
        private final View view;
        private File file;

        public ViewToUriConverter(Context context, View view, String uriFileName) {
            this.context = context;
            this.view = view;
            this.uriFileName = uriFileName;
        }

        public Uri getImageuri() {
            Bitmap v2ubitmap = getBitmapFromView(view);
            try {
                file = new File(context.getExternalCacheDir(), uriFileName + ".jpeg");
                FileOutputStream fout = new FileOutputStream(file);
                v2ubitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
                fout.flush();
                fout.close();
                file.setReadable(true, false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Uri.fromFile(file);
        }

        @SuppressLint("ResourceAsColor")
        private Bitmap getBitmapFromView(View view) {
            Bitmap vbitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

            Canvas vcanvas = new Canvas(vbitmap);
            Drawable v2ubg = view.getBackground();
            if (v2ubg != null) {
                v2ubg.draw(vcanvas);
            } else {
                vcanvas.drawColor(android.R.color.white);
            }
            view.draw(vcanvas);
            return vbitmap;
        }

}

