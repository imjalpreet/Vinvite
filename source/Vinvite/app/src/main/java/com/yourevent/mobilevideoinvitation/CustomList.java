package com.yourevent.mobilevideoinvitation;

/**
 * Created by vikram on 17/11/14.
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomList extends ArrayAdapter<String>{
    private Activity context;
    private final String[] web;
    private  String[] imageId;
    public CustomList(Activity context, String[] web, String[] imageId) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
       // TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        //String pathName =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + icon_1.gif";

        BitmapFactory.Options bOptions = new BitmapFactory.Options();
        bOptions.inTempStorage = new byte[16*1024];

        Bitmap b = BitmapFactory.decodeFile(imageId[position],bOptions);

       // Bitmap b = BitmapFactory.decodeFile(imageId[position]);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(b);
       // txtTitle.setText(web[position]);
 //       imageView.setImageResource(imageId[position]);

        return rowView;
    }
}