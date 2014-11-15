package com.yourevent.mobilevideoinvitation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by raj on 13/11/14.
 */
public class ScriptView extends View {
    private String finalscript = "Hello";

    public ScriptView(Context context, String script) {
        super(context);
        finalscript=script;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paintobj = null;
        assert paintobj != null;
        paintobj.setARGB(255, 255, 255, 255);
        canvas.drawText(finalscript, 0, 0, paintobj);

    }



}
