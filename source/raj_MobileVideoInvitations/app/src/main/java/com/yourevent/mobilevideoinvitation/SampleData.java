package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 09-10-2014.
 */
import java.util.ArrayList;

public class SampleData {
    public static final int SAMPLE_DATA_ITEM_COUNT = 6;

    public static ArrayList<String> generateSampleData() {
        String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            data.add(events[i]);
        }
        return data;
    }
}