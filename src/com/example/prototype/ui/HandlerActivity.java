package com.example.prototype.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.prototype.R;
import com.example.prototype.utils.FileHandler;

import java.io.File;
import java.util.List;

/**
 * Created by Patrick on 15.03.2016.
 */
public abstract class HandlerActivity extends Activity {

    protected ViewGroup mHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        TextView header = (TextView) findViewById(R.id.header_data);
        header.setText(getHandlerTag() + " " + header.getText());

        // Setup the host for later filling with input by child classes
        mHost = (ViewGroup) findViewById(R.id.output_host);

        // Get all input that has been placed at the interface from a FileHandler
        List<File> inputFiles = new FileHandler(this).findInputFiles();

        // Make the child implementation handle the prototyping approach for which it is responsible
        handleData(inputFiles);
    }

    public abstract String getHandlerTag();

    public abstract void handleData(List<File> data);

    protected <V extends View> V prepareView(V view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 16);
        if (view instanceof TextView) {
            ((TextView) view).setGravity(Gravity.CENTER);
        }

        view.setLayoutParams(params);

        return view;
    }
}
