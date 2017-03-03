package com.example.jant.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jant on 2017/3/3.
 */

class MyView extends View {


    private Bitmap mBitmap;
    private Paint paint = new Paint();
    public MyView(Context context) {
        super(context);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search);

    }


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search);
        new Thread() {
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // invalidate():在UI线程中使用
                    // postInvalidate()：在非UI线程中使用
                    postInvalidate();
                }

            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Matrix m = new Matrix();

        m.setScale(1, -1);
        m.setScale(-1, 1);

//      cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()),new Rect(0, 0, w, h), null);
        canvas.drawBitmap(mBitmap, m, paint);
    }
}
