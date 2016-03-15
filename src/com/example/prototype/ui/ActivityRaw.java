package com.example.prototype.ui;

import android.util.Log;
import android.widget.TextView;

import java.io.*;
import java.net.URLConnection;
import java.util.List;

/**
 * Activity for handling file data as raw data packages without caring about their information content other than some
 * of its file (meta-) properties. Will display the input information as a list of string blocks and nothing more
 * <p/>
 * Created by Patrick on 15.03.2016.
 */
public class ActivityRaw extends HandlerActivity {

    private static final String LOG_TAG = ActivityRaw.class.getSimpleName();


    @Override
    public void handleData(List<File> data) {
        for (File file : data) {
            TextView text = getRawTextView(file.getName(), String.valueOf(file.length()), guessMimeType(file),
                    getExtension(file));
            mHost.addView(text);
        }
    }

    /**
     * Creates a fully initialised TextView containing the raw text specified in the parameters
     *
     * @param name
     * @param size
     * @param encoding
     * @param extension
     * @return
     */
    private TextView getRawTextView(String name, String size, String encoding, String extension) {
        TextView result = prepareView(new TextView(this));
        result.setText("New input data found.\nFile Name: " + name + "\nFile size: " + size + " bytes\nFile encoding:" +
                " " + encoding + "\nFile extension: " + extension);
        return result;
    }

    private String guessMimeType(File file) {
        InputStream stream;
        try {
            stream = new BufferedInputStream(new FileInputStream(file));
            return URLConnection.guessContentTypeFromStream(stream);
        } catch (IOException e) {
            Log.d(LOG_TAG, "Mime type not determined", e);
            return "Unknown";
        }
    }

    private String getExtension(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    @Override
    public String getHandlerTag() {
        return "Raw";
    }
}
