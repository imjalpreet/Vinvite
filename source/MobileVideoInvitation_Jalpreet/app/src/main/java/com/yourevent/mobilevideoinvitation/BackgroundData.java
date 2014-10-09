package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 09-10-2014.
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
        //for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
        data.add(0, "Background 1");
        data.add(1, "Background 2");
        data.add(2, "Background 3");
        data.add(3, "Background 4");
        data.add(4, "Background 5");
        data.add(5, "Background 6");
        return data;
    }
}