package com.example.prototype.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.prototype.R;
import com.example.prototype.logics.DataHandler;

/**
 * Created by Patrick on 15.03.2016.
 */
public abstract class HandlerActivity extends Activity implements DataHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        TextView header = (TextView) findViewById(R.id.header_data);
        header.setText(getHandlerTag() + " " + header.getText());
    }

    public abstract String getHandlerTag();
}
