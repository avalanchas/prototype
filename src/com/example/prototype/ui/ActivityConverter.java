package com.example.prototype.ui;

import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Patrick on 15.03.2016.
 */
public class ActivityConverter extends HandlerActivity {

    @Override
    public void handleData(List<File> data) {
        for (File file : data) {
            String text = convertFileToString(file);

            if (text != null) {
                mHost.addView(getContentTextView(text));
            }
        }
    }

    private String convertFileToString(File file) {
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            return scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private TextView getContentTextView(String content) {
        TextView result = prepareView(new TextView(this));
        result.setText("New input data found.\nContent: " + content);
        return result;

    }

    @Override
    public String getHandlerTag() {
        return "Converted";
    }
}
