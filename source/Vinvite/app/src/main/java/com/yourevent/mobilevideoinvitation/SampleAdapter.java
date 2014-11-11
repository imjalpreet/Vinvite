package com.yourevent.mobilevideoinvitation;

/**
 * Created by imjalpreet on 09-10-2014.
 */
import java.util.ArrayList;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.etsy.android.grid.util.DynamicHeightTextView;
import android.graphics.Color;

/***
 * ADAPTER
 */
public class SampleAdapter extends ArrayAdapter<String> {
    // --Commented out by Inspection (06-11-2014 16:59):private static final String TAG = "SampleAdapter";

    static class ViewHolder {
        DynamicHeightTextView txtLineOne;
    }

    private final LayoutInflater mLayoutInflater;
    private final ArrayList<Integer> mBackgroundColors;

    public SampleAdapter(final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        mBackgroundColors = new ArrayList<Integer>();
        mBackgroundColors.add(R.drawable.screen2_birthday);
        mBackgroundColors.add(R.drawable.screen2_wedding);
        mBackgroundColors.add(R.drawable.screen2_engagement);
        mBackgroundColors.add(R.drawable.screen2_reception);
        mBackgroundColors.add(R.drawable.screen2_housewarming);
        mBackgroundColors.add(R.drawable.screen2_anniversary);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_sample, parent, false);
            vh = new ViewHolder();
            vh.txtLineOne = (DynamicHeightTextView) convertView.findViewById(R.id.txt_line1);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        int backgroundIndex = position >= mBackgroundColors.size() ?
                position % mBackgroundColors.size() : position;
        convertView.setBackgroundResource(mBackgroundColors.get(backgroundIndex));
        //vh.txtLineOne.setHeightRatio(0.47);
        //vh.txtLineOne.setTextSize(22);
        //vh.txtLineOne.setTextColor(Color.parseColor("#FFFFFF"));
        //vh.txtLineOne.setText(getItem(position));
        return convertView;
    }
}