package net.anandarachmat.lab.android.mycameraapp.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Box extends View {

    private Paint paint = new Paint();


    public Box(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) { // Override the onDraw() Method
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);

        //center
        int x0 = canvas.getWidth()/2;
        int y0 = canvas.getHeight()/2;
        int dx = canvas.getHeight()/3;
        int dy = canvas.getHeight()/3;

        //draw guide box
        // canvas.drawRect(x0-dx, y0-dy, x0+dx, y0+dy, paint);
        // canvas.drawRect(-20, 280, 1100, 1400, paint);
        canvas.drawRect(50, 200, 1030, 800, paint);
    }
}
