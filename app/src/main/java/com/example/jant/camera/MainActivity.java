package com.example.jant.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
////        DisplayMetrics mDisplayMetrics = getApplicationContext().getResources()
////                .getDisplayMetrics();
////        MainActivity.mScreenWidth = mDisplayMetrics.widthPixels;
////        MainActivity.mScreenHeight = mDisplayMetrics.heightPixels;
////        mCameraView = (CameraView) findViewById(R.id.cameraView);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search);
//        Bitmap mCopyBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search)
//                .copy(Bitmap.Config.ARGB_8888, true);
//        mOriginalPicture = (ImageView) findViewById(R.id.original_picture);
//        mChangePicture = (ImageView) findViewById(R.id.change_picture);
//        mPaint = new Paint();// 实例一个画笔
////        mPaint.setColor(Color.WHITE);// 设置画笔颜色为白色
//        mPaint.setAntiAlias(true);
//        mOriginalPicture.setImageResource(R.mipmap.search);
//
////        mChangePicture.setImageBitmap(mirrorTranslate(mBitmap));
////        mChangePicture.setImageBitmap(scale(mBitmap,2,1));
//        mChangePicture.setImageBitmap(convert(mCopyBitmap));
//
//    }


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


//    private Bitmap scale(Bitmap bmp, float x, float y) {
//
//        Bitmap lBitmap = Bitmap.createBitmap((int) (bmp.getWidth() * x),
//                (int) (bmp.getHeight() * y), bmp.getConfig());
//        Paint paint = new Paint();// 实例一个画笔
//
//        paint.setAntiAlias(true);
//
//
//        Matrix matrix = new Matrix();
////        matrix.postTranslate(0, 2 * bmp.getHeight());
//        matrix.postScale(-1, 1, 100 + bmp.getWidth() / 2, 100 + bmp.getHeight() / 2);
//
////        matrix.setScale(1, 2);
////        matrix.setScale(-1, 1);
//
//        matrix.postScale(x, y);
//
//        Canvas canvas = new Canvas(lBitmap);
//        canvas.drawBitmap(bmp, matrix, paint);
////        canvas.save();
////        canvas.scale(-1, 1, 100 + bmp.getWidth() / 2,100 + bmp.getHeight() / 2);// scale（）当第一个参数为负数时表示x方向镜像反转，同理第二参数，镜像反转x,镜像反转Y
////        canvas.drawBitmap(bmp, 0, 4 * bmp.getHeight() + 10, paint);
////        canvas.drawBitmap
////        canvas.restore();
//
//        return lBitmap;
//    }


    Bitmap convert(Bitmap a) {
        int w = a.getWidth();
        int h = a.getHeight();


        Bitmap new1 = a.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        Matrix m = new Matrix();
//        m.postScale(1, -1);   //镜像垂直翻转
//        m.postScale(-1, 1);   //镜像水平翻转
//        m.postRotate(180);  //旋转-90度

        m.postScale(1, -1);
//        m.postTranslate(-w/2,0);
        m.postTranslate(0, h);
//        m.setScale(-1, 1);
//        m.setRotate(180);
//        cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()),new Rect(0, 0, w, h), null);
//        Bitmap new2 = Bitmap.createBitmap(a, 0, 0, w, h, m, true);
        cv.drawBitmap(new1, m, null);
        mChangePicture.setImageBitmap(newb);
        return newb;

//        cv.drawBitmap(new2, 50, 200, null);// 画镜像图
    }


    public Bitmap bitmapTranslate(Bitmap bitmap, float x, float y) {

        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas lCanvas = new Canvas(newBitmap);
        Matrix lMatrix = new Matrix();
        lMatrix.postTranslate(x, y);
        lCanvas.drawBitmap(bitmap, lMatrix, null);
        return newBitmap;
    }


    public Bitmap bitmapScale(Bitmap bitmap, float x, float y) {

        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas lCanvas = new Canvas(newBitmap);
        Matrix lMatrix = new Matrix();
        lMatrix.postScale(x, y);
//        lMatrix.postTranslate(0,bitmap.getHeight());
        lCanvas.drawBitmap(bitmap, lMatrix, null);
        return newBitmap;
    }

    public Bitmap bitmapRotate(Bitmap bitmap, float degrees) {

        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas lCanvas = new Canvas(newBitmap);
        Matrix lMatrix = new Matrix();
        lMatrix.postRotate(degrees);
        lCanvas.drawBitmap(bitmap, lMatrix, null);
        return newBitmap;
    }

    public Bitmap bitmapSkew(Bitmap bitmap, float x, float y) {

        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas lCanvas = new Canvas(newBitmap);
        Matrix lMatrix = new Matrix();
        lMatrix.postSkew(x, y);
        lCanvas.drawBitmap(bitmap, lMatrix, null);
        return newBitmap;
    }


    private final Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(final byte[] data, Camera camera) {
//            mActivity.rest();

//            Log.i(TAG, "pictureCallback");

//            new SavePicTask(data, mCameraView.isBackCamera()).start();
        }
    };


    private Button btn_scale, btn_rotate, btn_translate, btn_skew,btn_XMirror,btn_YMirror;
    private ImageView iv_base, iv_after;
    private Bitmap baseBitmap;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_skew = (Button) findViewById(R.id.btn_skew);
        btn_XMirror = (Button) findViewById(R.id.btn_x_mirror);
        btn_YMirror = (Button) findViewById(R.id.btn_y_mirror);

        btn_scale.setOnClickListener(click);
        btn_rotate.setOnClickListener(click);
        btn_translate.setOnClickListener(click);
        btn_skew.setOnClickListener(click);
        btn_XMirror.setOnClickListener(click);
        btn_YMirror.setOnClickListener(click);

        iv_base = (ImageView) findViewById(R.id.iv_base);
        iv_after = (ImageView) findViewById(R.id.iv_after);

        baseBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        iv_base.setImageBitmap(baseBitmap);

        // 设置画笔，消除锯齿
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_scale:
                    bitmapScale(2.0f, 4.0f);
                    break;
                case R.id.btn_rotate:
                    bitmapRotate(180);
                    break;
                case R.id.btn_translate:
                    bitmapTranslate(20f, 20f);
                    break;
                case R.id.btn_skew:
                    bitmapSkew(0.2f, 0.4f);
                    break;
                case R.id.btn_x_mirror:
                    bitmapXMirror();
                    break;
                case R.id.btn_y_mirror:
                    bitmapYMirror();
                    break;
                default:
                    break;
            }

        }
    };

    /**
     * 缩放图片
     */
    protected void bitmapScale(float x, float y) {
        // 因为要将图片放大，所以要根据放大的尺寸重新创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * x),
                (int) (baseBitmap.getHeight() * y), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
        matrix.setScale(x, y);
        // 根据缩放比例，把图片draw到Canvas上
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }


    /**
     * x轴镜像
     */
    protected void bitmapXMirror() {
        // 因为要将图片放大，所以要根据放大的尺寸重新创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                baseBitmap.getWidth() ,
                baseBitmap.getHeight() , baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
        matrix.postScale(-1, 1);
        matrix.postTranslate(baseBitmap.getWidth(), 0);
        // 根据缩放比例，把图片draw到Canvas上
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }


    /**
     * y轴镜像
     */
    protected void bitmapYMirror() {

        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
        matrix.postScale(1, -1);
        //根据变换矩阵，绘制新的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,baseBitmap.getWidth(),baseBitmap.getHeight(), matrix, true);
        iv_after.setImageBitmap(afterBitmap);
    }




    /**
     * 倾斜图片
     */
    protected void bitmapSkew(float dx, float dy) {
        // 根据图片的倾斜比例，计算变换后图片的大小，
        Matrix matrix = new Matrix();
        // 设置图片倾斜的比例
        matrix.setSkew(dx, dy);
        //根据变换矩阵，绘制新的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,baseBitmap.getWidth(),baseBitmap.getHeight(), matrix, true);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片移动
     */
    protected void bitmapTranslate(float dx, float dy) {
        // 需要根据移动的距离来创建图片的拷贝图大小
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() + dx),
                (int) (baseBitmap.getHeight() + dy), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置移动的距离
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片旋转
     */
    protected void bitmapRotate(float degrees) {
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 根据原图的中心位置旋转
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2,
                baseBitmap.getHeight() / 2);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }
}
