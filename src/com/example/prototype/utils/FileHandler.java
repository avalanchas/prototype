package com.example.prototype.utils;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 15.03.2016.
 */
public class FileHandler {

    public static final String INTERFACE_INPUT = "/interface/input";
    private final Context mContext;

    public FileHandler(Context context) {
        mContext = context;
    }

    public List<File> findInputFiles() {
        return getFilesFromDirectory(new File(mContext.getFilesDir() + INTERFACE_INPUT));
    }

    private List<File> getFilesFromDirectory(File directory) {
        List<File> result = new ArrayList<>();
        for (final File file : directory.listFiles()) {
            if (file.isDirectory()) {
                result.addAll(getFilesFromDirectory(file));
            } else {
                result.add(file);
            }
        }
        return result;
    }

}
