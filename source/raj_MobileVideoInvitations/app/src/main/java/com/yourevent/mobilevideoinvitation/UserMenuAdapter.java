package com.yourevent.mobilevideoinvitation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.etsy.android.grid.util.DynamicHeightTextView;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by imjalpreet on 08-11-2014.
 */
public class UserMenuAdapter extends ArrayAdapter<String>{
    static class ViewHolder {
        DynamicHeightTextView txtLineOne;
    }

    private final LayoutInflater mLayoutInflater;
    private final ArrayList<Integer> mBackgroundColors;

    public UserMenuAdapter(final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        mBackgroundColors = new ArrayList<Integer>();
        mBackgroundColors.add(R.color.grey);
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
        vh.txtLineOne.setHeightRatio(0.3);
        vh.txtLineOne.setTextSize(22);
        vh.txtLineOne.setTextColor(Color.parseColor("#FFFFFF"));
        vh.txtLineOne.setText(getItem(position));
        return convertView;
    }
}
