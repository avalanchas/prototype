package com.example.prototype.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Util class for file scanning. Interprets all files in {@link #INTERFACE_INPUT} as interface input files coming from
 * "the outside".
 * <p/>
 * Created by Patrick
 */
public class FileHandler {

    private static final String LOG_TAG = FileHandler.class.getSimpleName();

    public static final String INTERFACE_INPUT = "/interface/input/";
    private final Context mContext;

    public FileHandler(Context context) {
        mContext = context;
    }

    /**
     * Finds all input files currently available at the data interface
     *
     * @return A list of Files. Empty list if no files were found in the external files dir (specifically
     * INTERFACE_INPUT)
     */
    public List<File> findInputFiles() {
        File directory = new File(mContext.getExternalFilesDir(null) + INTERFACE_INPUT);
        if (!directory.mkdirs()) {
            Log.d(LOG_TAG, "No directory access to " + INTERFACE_INPUT + ", do you have permissions?");
        }
        return getFilesFromDirectory(directory);
    }

    /**
     * Passes through a directory and returns all files from all subdirectories recursively. Empty directories are
     * ignored. Files are sorted by their filename per subdirectory
     *
     * @param directory The directory you wish to scan
     * @return A list of all files in the directory and all subdirectories
     */
    private List<File> getFilesFromDirectory(File directory) {
        List<File> result = new ArrayList<>();
        if (directory != null) {

            // Get all files and sort them. The default Selector is the filename
            File[] files = directory.listFiles();
            Arrays.sort(files);
            for (final File file : files) {

                // If the file is a directory, call this method again. Add all results to the final list
                if (file.isDirectory()) {
                    result.addAll(getFilesFromDirectory(file));
                } else {
                    result.add(file);
                }
            }
        }
        return result;
    }

}
