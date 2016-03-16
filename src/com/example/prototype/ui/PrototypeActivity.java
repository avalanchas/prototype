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
 * Parent activity for all activities that handle approaches of prototyping. Hosts some method for better encapsulation
 * and code reuse
 * <p/>
 * Created by Patrick on 15.03.2016.
 */
public abstract class PrototypeActivity extends Activity {

    protected ViewGroup mHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        // Prepare the header text to have a small indication of what data approach is instantiated
        TextView header = (TextView) findViewById(R.id.header_data);
        header.setText(getHandlerTag() + " " + header.getText());

        // Setup the host for later filling with input by child classes
        mHost = (ViewGroup) findViewById(R.id.output_host);

        // Get all input that has been placed at the interface from a FileHandler
        List<File> inputFiles = new FileHandler(this).findInputFiles();

        // Make the child implementation handle the prototyping approach for which it is responsible
        inputFound(inputFiles);
    }

    /**
     * Return a String tag to name this type of handler. The tag will be shown in the header text of the activity
     *
     * @return A String giving a small indication of what handler is actually instantiated
     */
    public abstract String getHandlerTag();

    /**
     * Invocation called at the moment that data from the interface is found! The implementation must react to this call
     * and process the data appropriately
     *
     * @param data A list of Files found on the filesystem as input
     */
    public abstract void inputFound(List<File> data);

    /**
     * Helper method for formatting and layouting a view in a way that it looks good on display. This method is the only
     * place for formatting so that all views are displayed appropriately
     *
     * @param view The view to format
     * @param <V>  The specific (auto-generified) type of view. This guarantees that if you pass in a TextView you will
     *             get a TextView back (not simply a view)
     * @return The exact view instance you passed in, but formatted and layouted
     */
    protected <V extends View> V prepareView(V view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 16);

        // TextViews get special handling so that text is centered inside them. Takes care of all subclasses (Button...)
        if (view instanceof TextView) {
            ((TextView) view).setGravity(Gravity.CENTER);
        }

        view.setLayoutParams(params);
        return view;
    }
}
