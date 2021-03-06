package com.wwish.frame.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * Created by wwish on 2017/12/13.
 */

public class WaterMarkImage {
    /**
     * 要加水印的图片
     */
    private Bitmap mInitBitmap;
    /**
     * 水印遮罩图片
     */
    private Bitmap mWaterMarkBitmap;
    /**
     * 水印文字
     */
    private String mWaterMarkString;
    /**
     * 水印文字位置
     */
    private TextLocation mWaterMarkTextLocation;

    /**
     * 水印文字旋转角度
     */
    private int mWaterMarkTextRotationAngle;

    /**
     * 水印文字颜色
     */
    private int mWaterMarkTextColor;

    /**
     * 水印文字大小
     */
    private float mWaterMarkTextSize;
    /**
     * 水印文字类型
     */
    private Typeface mWaterMarkTextTypeface;

    /**
     * 处理完的图片
     */
    private Bitmap mResultBitmap;

    /**
     * 处理完的图片
     */
    private Drawable mResultDrawable;


    /**
     * 构造方法
     * @param mInitBitmap
     */
    public WaterMarkImage(Bitmap mInitBitmap) {
        this.mInitBitmap = mInitBitmap;
        init();
    }

    /**
     * 初始化属性
     */
    private void init(){
        mWaterMarkTextSize=40;
        mWaterMarkTextRotationAngle=0;
        mWaterMarkTextLocation=TextLocation.CENTER;
        mWaterMarkTextColor= Color.WHITE;
        mWaterMarkTextTypeface= Typeface.create("宋体",Typeface.BOLD);
    }

    /**
     * 说明 返回处理过的水印图片
     * 作者 郭翰林
     * 创建时间 2017/6/16 下午12:03
     * @return
     */
    public Bitmap getBitmap(){
        int w=mInitBitmap.getWidth();
        int h=mInitBitmap.getHeight();
        mResultBitmap=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_4444);
        Canvas canvas=new Canvas(mResultBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(mInitBitmap,0,0,paint);

        if (mWaterMarkBitmap!=null){
            //加水印图片
            Matrix matrix = compressBitmap(w,h,mWaterMarkBitmap);
            canvas.drawBitmap(mWaterMarkBitmap, matrix, paint);
        }

        if(!TextUtils.isEmpty(mWaterMarkString)){
            //加水印文字
            paint.setColor(mWaterMarkTextColor);
            paint.setTypeface(mWaterMarkTextTypeface);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setAntiAlias(true);
            paint.setTextSize(mWaterMarkTextSize);
            Rect bounds = new Rect();
            paint.getTextBounds(mWaterMarkString, 0, mWaterMarkString.length(), bounds);

            float x=0;
            float y=0;
            //计算中心点位置
            int r = bounds.width() > bounds.height() ? bounds.width() / 2 : bounds.height() / 2;
            switch (mWaterMarkTextLocation){
                case CENTER:
                    x=w/2;
                    y=h/2;
                    break;
                case TOP_LEFT:
                    x=r;
                    y=r;
                    break;
                case TOP_RIGHT:
                    x=w-r;
                    y=r;
                    break;
                case BOTTOM_LEFT:
                    x=r;
                    y=h-r;
                    break;
                case BOTTOM_RIGHT:
                    x=w-r;
                    y=h- r * 0.5f;
                    break;
                default:
                    break;
            }
            //计算绘制路径
            r=r+20;
            Path path=new Path();
            float startX= (float) (x-r*Math.cos(Math.toRadians(mWaterMarkTextRotationAngle)));
            float startY= (float) (y+r*Math.sin(Math.toRadians(mWaterMarkTextRotationAngle)));
            path.moveTo(startX,startY);
            float endX= (float) (x+r*Math.cos(Math.toRadians(mWaterMarkTextRotationAngle)));
            float endY= (float) (y-r*Math.sin(Math.toRadians(mWaterMarkTextRotationAngle)));
            path.lineTo(endX,endY);
            canvas.drawTextOnPath(mWaterMarkString,path,0,0,paint);
        }
        //保存位图
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return mResultBitmap;
    }

    private Matrix compressBitmap(int parentWidth , int parentHeight , Bitmap waterMarkBitmap) {
        int waterBitmapWidth = waterMarkBitmap.getWidth();
        int waterBitmapHeight = waterMarkBitmap.getHeight();

        float scalex = 1f ;
        while(waterBitmapWidth * scalex > parentWidth * 0.3f ) {
            scalex-= 0.1f ;
        }

        float scaley = 1f ;
        while(waterBitmapHeight * scaley>  parentHeight * 0.3f){
            scaley -= 0.05f;
        }

        Matrix matrix = new Matrix();
        //matrix.setTranslate(parentWidth  - waterBitmapWidth * scale , parentHeight  - waterBitmapHeight * scale);
        matrix.preScale(scalex, scaley);
        matrix.postTranslate(parentWidth - waterBitmapWidth * scalex , parentHeight  - waterBitmapHeight * scaley);

        return matrix ;
    }


    /**
     * 说明 返回处理完的图片
     * 作者 郭翰林
     * 创建时间 2017/6/17 下午3:25
     * @return
     */
    public Drawable getDrawable(){
        Bitmap bitmap=getBitmap();
        mResultDrawable=new BitmapDrawable(bitmap);
        return mResultDrawable;
    }

    //赋值
    public WaterMarkImage setWaterMarkBitmap(Bitmap mWaterMarkBitmap) {
        this.mWaterMarkBitmap = mWaterMarkBitmap;
        return this;
    }

    public WaterMarkImage setWaterMarkString(String mWaterMarkString) {
        this.mWaterMarkString = mWaterMarkString;
        return this;
    }

    public WaterMarkImage setWaterMarkTextLocation(TextLocation mWaterMarkTextLocation) {
        this.mWaterMarkTextLocation = mWaterMarkTextLocation;
        return this;
    }

    public WaterMarkImage setWaterMarkTextRotationAngle(int mWaterMarkTextRotationAngle) {
        this.mWaterMarkTextRotationAngle = mWaterMarkTextRotationAngle;
        return this;
    }

    public WaterMarkImage setWaterMarkTextColor(int mWaterMarkTextColor) {
        this.mWaterMarkTextColor = mWaterMarkTextColor;
        return this;
    }

    public WaterMarkImage setWaterMarkTextSize(float mWaterMarkTextSize) {
        this.mWaterMarkTextSize = mWaterMarkTextSize;
        return this;
    }

    public WaterMarkImage setWaterMarkTextTypeface(Typeface mWaterMarkTextTypeface) {
        this.mWaterMarkTextTypeface = mWaterMarkTextTypeface;
        return this;
    }


}
