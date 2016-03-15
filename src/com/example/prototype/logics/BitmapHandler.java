package com.example.prototype.logics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * DataHandler for all files that can be read as a Bitmap and which consequently have to show the data content as an
 * image on the screen
 * <p/>
 * Created by Patrick on 15.03.2016.
 */
public class BitmapHandler implements DataHandler {

    @Override
    public View handleData(Context context, File data) {
        Bitmap bitmap = BitmapFactory.decodeFile(data.getPath());

        if (bitmap != null) {
            ImageView view = new ImageView(context);
            view.setImageBitmap(bitmap);
            return view;
        } else {
            TextView view = new TextView(context);
            view.setText("Note, data in file '" + data.getName() + "' is not adequate for BitmapHandler!");
            return view;
        }
    }
}
