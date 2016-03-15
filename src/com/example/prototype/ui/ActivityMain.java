package com.example.prototype.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import com.example.prototype.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Starting activity of the application. Shows the selection screen and allows the starting of the data input process
 */
public class ActivityMain extends Activity implements View.OnClickListener {

    private RadioGroup mGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Prepare for clicks on the "start" button and the radio items
        findViewById(R.id.prototype_start).setOnClickListener(this);
        findViewById(R.id.choice_first).setOnClickListener(this);
        findViewById(R.id.choice_second).setOnClickListener(this);
        findViewById(R.id.choice_third).setOnClickListener(this);

        // Save the radio group for future access
        mGroup = (RadioGroup) findViewById(R.id.choice_group);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prototype_start:
                startDataInput();
                break;
        }
    }

    private void startDataInput() {
        HandleType type = HandleType.from(mGroup.getCheckedRadioButtonId());

        Class<?> clazz;
        switch (type) {
            case CONVERTER:
                clazz = ActivityConverter.class;
                break;
            case FACTORY:
                clazz = ActivityFactory.class;
                break;
            default:
                clazz = ActivityRaw.class;
                break;
        }
        startActivity(new Intent(this, clazz));
    }


    /**
     * Utility enum to cleanly handle all input processing types. All types are mapped to their radio button ids for
     * easy constant time static lookup
     */
    private enum HandleType {
        RAW(R.id.choice_first),
        CONVERTER(R.id.choice_second),
        FACTORY(R.id.choice_third);

        private static final Map<Integer, HandleType> lookup = new HashMap<>();

        static {
            for (HandleType type : HandleType.values()) {
                lookup.put(type.getId(), type);
            }
        }

        private final int id;

        HandleType(int id) {
            this.id = id;
        }

        /**
         * Returns the HandleType that is assigned to the specified id. This must be the ID of the radio button that
         * holds that handle type
         *
         * @param id The radio button id for which to find the HandleType
         * @return The HandleType. Null if none is found for the specified id
         */
        public static HandleType from(int id) {
            return lookup.get(id);
        }

        public int getId() {
            return id;
        }
    }

}
