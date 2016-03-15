package com.example.prototype.ui;

import com.example.prototype.DataKeys;
import com.example.prototype.logics.DataHandler;
import com.example.prototype.logics.HandlerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by Patrick on 15.03.2016.
 */
public class ActivityFactory extends HandlerActivity {


    @Override
    public void handleData(List<File> data) {
        DataHandler handler = HandlerFactory.getHandlerInstance(DataKeys.TYPE);
        for (File file : data) {
            mHost.addView(prepareView(handler.handleData(this, file)));
        }
    }

    @Override
    public String getHandlerTag() {
        return "Factorised";
    }

}
