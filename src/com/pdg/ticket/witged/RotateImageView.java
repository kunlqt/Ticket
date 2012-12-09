
package com.pdg.ticket.witged;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RotateImageView extends ImageView {

    private Bitmap mBitmap;

    private int mDegree;

    public RotateImageView(Context context) {
        super(context);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        mBitmap = bm;
        mDegree = 0;
        super.setImageBitmap(bm);
    }

    public void init(Bitmap bm, int degree) {
        mBitmap = bm;
        mDegree = degree;
                drawMatrix();
//        rotate();
//        setImageBitmap(mBitmap);
    }

    private void drawMatrix() {

        Matrix matrix = new Matrix();
        matrix.postRotate(mDegree);

        int bmpWidth = mBitmap.getWidth();
        int bmpHeight = mBitmap.getHeight();

        Bitmap resizedBitmap = Bitmap
                .createBitmap(mBitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);

        setImageBitmap(resizedBitmap);
    }

    private void rotate() {
        Bitmap targetBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        Matrix matrix = new Matrix();
        matrix.setRotate(mDegree, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, matrix, new Paint());
    }

}
