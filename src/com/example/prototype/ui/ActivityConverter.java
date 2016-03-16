package com.example.prototype.ui;

import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Activity for handling file data by converting the incoming data to a String, absolutely no matter the content. This
 * will display meaningful results for some inputs but appear as jibberish for non-text files (binary files)
 * <p/>
 * Created by Patrick on 15.03.2016.
 */
public class ActivityConverter extends PrototypeActivity {

    @Override
    public void inputFound(List<File> data) {
        for (File file : data) {
            String text = convertFileToString(file);

            if (text != null) {
                mHost.addView(getContentTextView(text));
            }
        }
    }

    /**
     * Scans the content of a file and returns the whole content as a non-formatted, non-interpreted String
     *
     * @param file The file to convert
     * @return The String representing the file content, null if there was a problem retrieving the content
     */
    private String convertFileToString(File file) {
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            return scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Generates a TextView containing the String passed as the parameter as text
     *
     * @param content The text to set on the TextView
     * @return A fullly initialized, fully formatted TextView, ready for display
     */
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
