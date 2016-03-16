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
 * image on the screen. Therefore, use this handler whenever you're quite sure the data is a bitmap. Falls back to
 * showing a warning String if the data could not be read as a bitmap image.
 * <p/>
 * Created by Patrick
 */
public class BitmapHandler implements DataHandler {

    @Override
    public View handleData(Context context, File data) {
        // We're a Bitmap handler, we'll simply assume that incoming data is an image
        Bitmap bitmap = BitmapFactory.decodeFile(data.getPath());

        if (bitmap != null) {
            // Data was an image, prepare it for display
            ImageView view = new ImageView(context);
            view.setImageBitmap(bitmap);
            return view;
        } else {
            // Data was not an image, this is the wrong handler to use, show a warning
            TextView view = new TextView(context);
            view.setText("Note, data in file '" + data.getName() + "' is not adequate for BitmapHandler!");
            return view;
        }
    }
}
