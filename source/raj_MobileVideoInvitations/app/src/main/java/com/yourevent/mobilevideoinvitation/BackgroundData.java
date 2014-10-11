package com.yourevent.mobilevideoinvitation;

/**
 * Created by raj on 10/10/14.
 */
import android.renderscript.Script;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
public class BackgroundData{
    public static final int SAMPLE_DATA_ITEM_COUNT = 6;
    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);
        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            data.add("Background " + Integer.toString(i+1));
        }
        return data;
    }
}
