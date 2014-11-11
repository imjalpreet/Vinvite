package com.yourevent.mobilevideoinvitation;

import java.util.ArrayList;

/**
 * Created by imjalpreet on 08-11-2014.
 */
public class UserMenuData {
    public static final int SAMPLE_DATA_ITEM_COUNT = 4;

    public static ArrayList<String> generateSampleData() {
        String[] events = {"Previous Events", "Settings", "About Us", "Log Out"};
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            data.add(events[i]);
        }
        return data;
    }
}
