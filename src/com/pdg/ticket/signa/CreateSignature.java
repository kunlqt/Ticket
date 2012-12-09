
package com.pdg.ticket.signa;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pdg.ticket.R;
import com.pdg.ticket.StandarTicket;
import com.pdg.ticket.WaterTicketActivity;

public class CreateSignature extends GraphicsActivity implements
        ColorPickerDialog.OnColorChangedListener, OnClickListener {
    public MyView myView;

    public int nameImage;

    private FrameLayout layout;

    private TextView tvsignhere;

    private Button btaccept;

    private Button btclear;

    private Button btcancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature);

        layout = (FrameLayout) findViewById(R.id.layoutSignature);
        btaccept = (Button) findViewById(R.id.btaccept);
        btcancel = (Button) findViewById(R.id.btcancel);
        btclear = (Button) findViewById(R.id.btclear);
        tvsignhere = (TextView) findViewById(R.id.tvSignhere);
        tvsignhere.setHint("SIGN HERE");

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6);

        btaccept.setOnClickListener(this);
        btcancel.setOnClickListener(this);
        btclear.setOnClickListener(this);

        mEmboss = new EmbossMaskFilter(new float[] {
                1, 1, 1
        }, 0.4f, 6, 3.5f);

        mBlur = new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL);
        nameImage = this.getIntent().getExtras().getInt("sig");

        myView = new MyView(this);
        layout.addView(myView);
    }

    private Paint mPaint;

    private MaskFilter mEmboss;

    private MaskFilter mBlur;

    public void colorChanged(int color) {
        mPaint.setColor(color);
    }

    public class MyView extends View {

        private static final float MINP = 0.25f;

        private static final float MAXP = 0.75f;

        private Bitmap mBitmap;

        private Canvas mCanvas;

        private Path mPath;

        private Paint mBitmapPaint;

        public MyView(Context c) {
            super(c);

            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(0x0);

            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

            canvas.drawPath(mPath, mPaint);
        }

        private float mX, mY;

        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            tvsignhere.setHint("");
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }

    private static final int COLOR_MENU_ID = Menu.FIRST;

    private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;

    private static final int BLUR_MENU_ID = Menu.FIRST + 2;

    private static final int ERASE_MENU_ID = Menu.FIRST + 3;

    private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, COLOR_MENU_ID, 0, "Cancel").setShortcut('3', 'c');
        menu.add(0, EMBOSS_MENU_ID, 0, "Clear").setShortcut('4', 's');
        menu.add(0, BLUR_MENU_ID, 0, "Appect").setShortcut('5', 'z');
        // menu.add(0, ERASE_MENU_ID, 0, "Erase").setShortcut('5', 'z');
        // menu.add(0, SRCATOP_MENU_ID, 0, "SrcATop").setShortcut('5', 'z');

        /****
         * Is this the mechanism to extend with filter effects? Intent intent =
         * new Intent(null, getIntent().getData());
         * intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
         * menu.addIntentOptions( Menu.ALTERNATIVE, 0, new ComponentName(this,
         * NotesList.class), null, intent, 0, null);
         *****/
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xFF);

        switch (item.getItemId()) {
            case COLOR_MENU_ID:
                // new ColorPickerDialog(this, this, mPaint.getColor()).show();
                this.finish();
                return true;
            case EMBOSS_MENU_ID:
                // if (mPaint.getMaskFilter() != mEmboss) {
                // mPaint.setMaskFilter(mEmboss);
                // } else {
                // mPaint.setMaskFilter(null);
                // }
                myView = null;
                setContentView(myView = new MyView(this));

                return true;
            case BLUR_MENU_ID:
                myView.setDrawingCacheEnabled(true);
                myView.buildDrawingCache(true);
                Bitmap bm = Bitmap.createBitmap(myView.getDrawingCache());
                // mainScrollView.setDrawingCacheEnabled(false); //

                if (bm != null) {
                    try {
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG, 50, bs);

                        Intent intent2 = new Intent(this, StandarTicket.class);
                        switch (nameImage) {
                            case 1:
                                intent2.putExtra("byteArray1", bs.toByteArray());
                                StandarTicket.signature1.setImageBitmap(bm);
                                StandarTicket.isSig1 = false;
                                break;
                            case 2:
                                intent2.putExtra("byteArray2", bs.toByteArray());
                                StandarTicket.signature2.setImageBitmap(bm);
                                StandarTicket.isSig2 = false;
                                break;
                            case 3:
                                intent2.putExtra("byteArray3", bs.toByteArray());
                                StandarTicket.signature3.setImageBitmap(bm);
                                StandarTicket.isSig3 = false;
                                break;
                            case 4:
                                intent2.putExtra("byteArray4", bs.toByteArray());
                                StandarTicket.signature4.setImageBitmap(bm);
                                StandarTicket.isSig4 = false;
                                break;
                            case 5: //Water signature
                                Log.e("water ticket ", "sign");
                                Intent intent3 = new Intent(this, WaterTicketActivity.class);
                                intent3.putExtra("byteArray5", bs.toByteArray());
                                WaterTicketActivity.signature.setImageBitmap(bm);
                                break;
                            default:
                                break;
                        }
                        this.finish();
                        // startActivity(intent2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
                // case ERASE_MENU_ID:
                // mPaint.setXfermode(new PorterDuffXfermode(
                // PorterDuff.Mode.CLEAR));
                // return true;
                // case SRCATOP_MENU_ID:
                // mPaint.setXfermode(new PorterDuffXfermode(
                // PorterDuff.Mode.SRC_ATOP));
                // mPaint.setAlpha(0x80);
                // return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btaccept:
                myView.setDrawingCacheEnabled(true);
                myView.buildDrawingCache(true);
                Bitmap bm = Bitmap.createBitmap(myView.getDrawingCache());
                // mainScrollView.setDrawingCacheEnabled(false); //

                if (bm != null) {
                    try {
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG, 50, bs);

                        Intent intent2 = new Intent(this, StandarTicket.class);
                        switch (nameImage) {
                            case 1:
                                intent2.putExtra("byteArray1", bs.toByteArray());
                                StandarTicket.signature1.setImageBitmap(bm);
                                StandarTicket.isSig1 = false;
                                break;
                            case 2:
                                intent2.putExtra("byteArray2", bs.toByteArray());
                                StandarTicket.signature2.setImageBitmap(bm);
                                StandarTicket.isSig2 = false;
                                break;
                            case 3:
                                intent2.putExtra("byteArray3", bs.toByteArray());
                                StandarTicket.signature3.setImageBitmap(bm);
                                StandarTicket.isSig3 = false;
                                break;
                            case 4:
                                intent2.putExtra("byteArray4", bs.toByteArray());
                                StandarTicket.signature4.setImageBitmap(bm);
                                StandarTicket.isSig4 = false;
                                break;
                            case 5: //Water signature
                                Log.e("water ticket ", "sign");
                                Intent intent3 = new Intent(this, WaterTicketActivity.class);
                                intent3.putExtra("byteArray5", bs.toByteArray());
                                WaterTicketActivity.signature.setImageBitmap(bm);
                                break;
                            default:
                                break;
                        }
                        this.finish();
                        // startActivity(intent2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.btclear:
                layout.removeView(myView);
                myView = new MyView(this);
                layout.addView(myView);
                break;
            case R.id.btcancel:
                this.finish();
                break;
            default:
                break;
        }

    }
}
