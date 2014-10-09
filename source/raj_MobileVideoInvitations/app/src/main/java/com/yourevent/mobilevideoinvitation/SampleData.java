package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 09-10-2014.
 */
import java.util.ArrayList;
import java.util.List;
public class SampleData {
    public static final int SAMPLE_DATA_ITEM_COUNT = 30;

    public static ArrayList<String> generateSampleData() {
        String[] events = {"Birthday", "Wedding", "Engagement", "Reception", "House Warming", "Anniversary"};

        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);
        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            if(i<6) {
                data.add(events[i]);
            }
            else{
                break;
            }
        }
        return data;
    }
}