package com.yourevent.mobilevideoinvitation;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.graphics.Color;
import com.etsy.android.grid.util.DynamicHeightTextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by imjalpreet on 11-10-2014.
 */
public class SelectScriptAdapter extends ArrayAdapter<String> {
    static class ViewHolder {
        DynamicHeightTextView txtLineOne;
    }
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<Integer> mBackgroundColors;

    public SelectScriptAdapter(final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        Random mRandom = new Random();
        mBackgroundColors = new ArrayList<Integer>();
        mBackgroundColors.add(R.drawable.screen45);
        /*mBackgroundColors.add(R.color.green);
        mBackgroundColors.add(R.color.blue);
        mBackgroundColors.add(R.color.yellow);
        mBackgroundColors.add(R.color.grey);*/
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_sample, parent, false);
            vh = new ViewHolder();
            vh.txtLineOne = (DynamicHeightTextView) convertView.findViewById(R.id.txt_line1);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }
        int backgroundIndex = position >= mBackgroundColors.size() ?
                position % mBackgroundColors.size() : position;
        convertView.setBackgroundResource(mBackgroundColors.get(backgroundIndex));
        /*vh.txtLineOne.setHeightRatio(0.6481);
        vh.txtLineOne.setText(getItem(position));
        vh.txtLineOne.setTextSize(30);
        vh.txtLineOne.setTextColor(Color.parseColor("#FFFFFF"));
        vh.txtLineOne.setPadding(50,0,0,50);
        vh.txtLineOne.setGravity(Gravity.LEFT | Gravity.BOTTOM);*/
        return convertView;
    }
}
