package com.example.prototype.logics;

import android.content.Context;
import android.view.View;

import java.io.File;

/**
 * Data handling definition interface to implement for all factorised handlers. This unifies the processing of the file
 * input from the interface.
 * <p/>
 * Created by Patrick
 */
public interface DataHandler {

    /**
     * Called when it is time to handle input from the interface (in this case: a file from the filesystem). The method
     * must parse the input and give a meaningful view representation (TextView, ImageView...) back to the caller
     *
     * @param context The context of the activity for which a view is to be built
     * @param data    The file that is to be analysed
     * @return A View representation of the file data, according to the type of handler
     */
    View handleData(Context context, File data);
}
