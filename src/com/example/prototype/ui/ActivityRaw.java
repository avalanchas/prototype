package com.example.prototype.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.prototype.R;
import com.example.prototype.logics.DataHandler;
import com.example.prototype.utils.FileHandler;

import java.io.*;
import java.net.URLConnection;
import java.util.List;

/**
 * Activity for handling file data as raw data packages without caring about their information content other than some
 * of its file (meta-) properties. Will display the input information as a list of string blocks and nothing more
 * <p/>
 * Created by Patrick on 15.03.2016.
 */
public class ActivityRaw extends Activity implements DataHandler {

    private static final String LOG_TAG = ActivityRaw.class.getSimpleName();

    private ViewGroup mHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        // Setup the host for later filling with input
        mHost = (ViewGroup) findViewById(R.id.output_host);

        // Get all input that has been placed at the interface from a FileHandler
        List<File> inputFiles = new FileHandler(this).findInputFiles();
        handleData(inputFiles);
    }

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
     * @return
     */
    private TextView getRawTextView(String name, String size, String encoding, String extension) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView result = new TextView(this);
        result.setLayoutParams(params);

        result.setText("New input data found.\nName: " + name + "\nFile size: " + size + "\nFile encoding: " +
                encoding + "\nFile extension: " + extension);
        return result;
    }

    private String guessMimeType(File file) {
        InputStream stream;
        try {
            stream = new BufferedInputStream(new FileInputStream(file));
            return URLConnection.guessContentTypeFromStream(stream);
        } catch (IOException e) {
            Log.d(LOG_TAG, "Mime type not determined", e);
            return null;
        }
    }

    private String getExtension(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }
}
