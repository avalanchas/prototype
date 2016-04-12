package com.example.prototype.ui;

import com.example.prototype.DataKeys;
import com.example.prototype.logics.DataHandler;
import com.example.prototype.logics.DataHandlerFactory;

import java.io.File;
import java.util.List;

/**
 * Activity for handling file data in a more advanced manner. A {@link DataHandlerFactory} will be invoked to find a
 * befitting implementation for the handling. That implementation will then process the files <b>specialised onto that
 * particular situation</b>. The processed data will then be laid onto a <b>specialised</b> view, decided also by the
 * {@link DataHandler} implementation! The data handler implementation may return any child class of {@link
 * android.view.View}
 * <p/>
 * Created by Patrick
 */
public class ActivityFactory extends PrototypeActivity {


    @Override
    public void inputFound(List<File> data) {
        // At this point, file scanning could be implemented to get better guesses of what the input actually is
        DataHandler handler = new DataHandlerFactory().getHandlerInstance(DataKeys.TYPE);
        for (File file : data) {
            // Currently, simply take the data and pass it on to whichever implementation the DataKey.TYPE specifies
            mHost.addView(prepareView(handler.handleData(this, file)));
        }
    }

    @Override
    public String getHandlerTag() {
        return "Factorised";
    }

}
