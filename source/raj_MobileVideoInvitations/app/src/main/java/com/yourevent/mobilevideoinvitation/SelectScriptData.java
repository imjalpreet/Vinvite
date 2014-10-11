package com.yourevent.mobilevideoinvitation;

import java.util.ArrayList;

/**
 * Created by imjalpreet on 11-10-2014.
 */
public class SelectScriptData {
    public static final int SAMPLE_DATA_ITEM_COUNT = 3;
    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);
        //for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
        data.add(0, "Formal");
        data.add(1, "Family");
        data.add(2, "Informal");
        return data;
    }
}
