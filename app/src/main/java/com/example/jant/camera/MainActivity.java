package com.example.jant.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    /**
     * 相机绑定的SurfaceView
     */
    private CameraView mCameraView;
    public static int mScreenWidth = 0;
    public static int mScreenHeight = 0;

    private Bitmap mBitmap;
    private Paint mPaint;

    private ImageView mOriginalPicture;
    private ImageView mChangePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        DisplayMetrics mDisplayMetrics = getApplicationContext().getResources()
//                .getDisplayMetrics();
//        MainActivity.mScreenWidth = mDisplayMetrics.widthPixels;
//        MainActivity.mScreenHeight = mDisplayMetrics.heightPixels;
//        mCameraView = (CameraView) findViewById(R.id.cameraView);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search);

        mOriginalPicture = (ImageView) findViewById(R.id.original_picture);
        mChangePicture = (ImageView) findViewById(R.id.change_picture);
        mPaint = new Paint();// 实例一个画笔
//        mPaint.setColor(Color.WHITE);// 设置画笔颜色为白色
        mPaint.setAntiAlias(true);
        mOriginalPicture.setImageResource(R.mipmap.search);

//        mChangePicture.setImageBitmap(mirrorTranslate(mBitmap));
//        mChangePicture.setImageBitmap(scale(mBitmap,2,1));
        mChangePicture.setImageBitmap(convert(mBitmap));

    }


    @Override
    protected void onStart() {
        super.onStart();
//        mCameraView.bindActivity(this);
//        mCameraView.setPictureCallback(pictureCallback);
    }

    private Bitmap mirrorTranslate(Bitmap bmp) {

        Bitmap lBitmap = Bitmap.createBitmap(bmp.getWidth(),
                bmp.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();// 实例一个画笔
        paint.setAntiAlias(true);


        Matrix matrix = new Matrix();
//        matrix.postTranslate(0, 2 * bmp.getHeight());
//        matrix.postScale(-1, 1, 100 + bmp.getWidth() / 2,100 + bmp.getHeight() / 2);

//        matrix.setScale(1, 2);
//        matrix.setScale(-1, 1);
//        matrix.postScale(1, -1);   //镜像垂直翻转
        matrix.postScale(-1, 1);   //镜像水平翻转
//        matrix.postRotate(-90);  //旋转-90度

        Canvas canvas = new Canvas(lBitmap);
        canvas.drawBitmap(bmp, matrix, null);
//        canvas.save();
//        canvas.scale(-1, 1, 100 + bmp.getWidth() / 2,100 + bmp.getHeight() / 2);// scale（）当第一个参数为负数时表示x方向镜像反转，同理第二参数，镜像反转x,镜像反转Y
//        canvas.drawBitmap(bmp, 0, 4 * bmp.getHeight() + 10, paint);
//        canvas.drawBitmap
//        canvas.restore();
        mChangePicture.setImageBitmap(lBitmap);
        return lBitmap;
    }


    private Bitmap scale(Bitmap bmp, float x, float y) {

        Bitmap lBitmap = Bitmap.createBitmap((int) (bmp.getWidth() * x),
                (int) (bmp.getHeight() * y), bmp.getConfig());
        Paint paint = new Paint();// 实例一个画笔

        paint.setAntiAlias(true);


        Matrix matrix = new Matrix();
//        matrix.postTranslate(0, 2 * bmp.getHeight());
        matrix.postScale(-1, 1, 100 + bmp.getWidth() / 2, 100 + bmp.getHeight() / 2);

//        matrix.setScale(1, 2);
//        matrix.setScale(-1, 1);

        matrix.postScale(x, y);

        Canvas canvas = new Canvas(lBitmap);
        canvas.drawBitmap(bmp, matrix, paint);
//        canvas.save();
//        canvas.scale(-1, 1, 100 + bmp.getWidth() / 2,100 + bmp.getHeight() / 2);// scale（）当第一个参数为负数时表示x方向镜像反转，同理第二参数，镜像反转x,镜像反转Y
//        canvas.drawBitmap(bmp, 0, 4 * bmp.getHeight() + 10, paint);
//        canvas.drawBitmap
//        canvas.restore();

        return lBitmap;
    }


    Bitmap convert(Bitmap a) {
        int w = a.getWidth();
        int h = a.getHeight();
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        Matrix m = new Matrix();
//        m.postScale(1, -1);   //镜像垂直翻转
//        m.postScale(-1, 1);   //镜像水平翻转
//        m.postRotate(180);  //旋转-90度

        m.setScale(1, -1);
        m.setScale(-1, 1);
//        m.setRotate(180);

        Bitmap new2 = Bitmap.createBitmap(a, 0, 0, w, h, m, true);

//        cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()),new Rect(0, 0, w, h), null);
        cv.drawBitmap(a, m, null);

//        cv.drawBitmap(new2, 50, 200, null);// 画镜像图
        return newb;

    }


    class MyView extends View {



        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            int w = mBitmap.getWidth();
            int h = mBitmap.getHeight();
            Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
            
            Matrix m = new Matrix();


            m.setScale(1, -1);
            m.setScale(-1, 1);

//        cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()),new Rect(0, 0, w, h), null);
            canvas.drawBitmap(mBitmap, m, null);
            super.onDraw(canvas);
        }
    }


    private final Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(final byte[] data, Camera camera) {
//            mActivity.rest();

//            Log.i(TAG, "pictureCallback");

//            new SavePicTask(data, mCameraView.isBackCamera()).start();
        }
    };

}
