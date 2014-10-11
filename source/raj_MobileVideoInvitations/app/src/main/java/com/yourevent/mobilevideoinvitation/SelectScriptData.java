package com.yourevent.mobilevideoinvitation;

import java.util.ArrayList;

/**
 * Created by raj on 12-10-2014.
 */
public class SelectScriptData {
    public static final int SAMPLE_DATA_ITEM_COUNT = 3;
    public static String[] scripts = {"Formal", "Family", "Informal"};
    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);
        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            data.add(scripts[i]);
        }
        return data;
    }
}
