
package com.pdg.ticket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.witged.RotateImageView;

public class RailTicket extends Activity implements OnClickListener {
    private Button btTakePhoto;

    private Button btRetake;

    private Button btSave;

    private FrameLayout cameraFrame;

    private Camera mCamera;

    private CameraPreview mPreview;

    protected File pictureFile;

    private int idRunlog;

    private int idTicket;

    private AlertDialog dialog;

    Bitmap bm;

    private String nameRunlog;

    private int ticketType;

    private boolean callFromOption;

    private boolean callFromReview;

    private boolean callFromArchivedTicketSet;

    private int position;

    private String urlPicture;

    private LinearLayout llMain;

    private boolean isClickRetak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_rail_ticket);
        btTakePhoto = (Button) findViewById(R.id.btTakePhoto);
        cameraFrame = (FrameLayout) findViewById(R.id.cameraFrame);
        btRetake = (Button) findViewById(R.id.btReTakePhoto);
        btSave = (Button) findViewById(R.id.btSavePhoto);
        btTakePhoto.setOnClickListener(this);
        btRetake.setOnClickListener(this);
        btSave.setOnClickListener(this);
        dialog = new ProgressDialog(this);
        // Create an instance of Camera
        llMain = (LinearLayout) findViewById(R.id.linearRailMain);

        try {
            idRunlog = getIntent().getExtras().getInt("idRunLog", 0);
            idTicket = getIntent().getExtras().getInt("idTicket", 0);
            ticketType = getIntent().getExtras().getInt("ticketType", 0);
            nameRunlog = getIntent().getExtras().getString("nameRunlog");
            callFromOption = getIntent().getExtras().getBoolean("callFromOption", false);
            callFromReview = getIntent().getExtras().getBoolean("callFromReview", false);
            callFromArchivedTicketSet = this.getIntent().getExtras()
                    .getBoolean("callFromArchivedTicketSet", false);
            position = this.getIntent().getExtras().getInt("position", -1);
            urlPicture = this.getIntent().getExtras().getString("picture");

        } catch (Exception e) {
            // TODO: handle exception
        }

        if (callFromReview) {
            new loadPhotoTask(urlPicture).execute();
            btTakePhoto.setText("Confirm");
            btSave.setVisibility(View.INVISIBLE);
            btRetake.setVisibility(View.INVISIBLE);
        } else {
            if (callFromArchivedTicketSet) {
                new loadPhotoTask(urlPicture).execute();
                btTakePhoto.setText("Back");
                btSave.setVisibility(View.INVISIBLE);
                btRetake.setVisibility(View.INVISIBLE);
            } else {
                if (callFromOption) {
                    new loadPhotoTask(urlPicture).execute();
                    btTakePhoto.setText("Retake");
                    btSave.setVisibility(View.INVISIBLE);
                    btRetake.setVisibility(View.INVISIBLE);
                } else {

                    mCamera = getCameraInstance();

                    // Create our Preview view and set it as the content of our
                    // activity.
                    mPreview = new CameraPreview(this, mCamera);
                    cameraFrame.addView(mPreview);
                    btSave.setVisibility(View.GONE);
                    btRetake.setVisibility(View.GONE);
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btTakePhoto) {
            if (callFromReview) {
                Intent intent = new Intent(this, ReviewRunlog.class);
                if (position != -1) {
                    intent.putExtra("position", position);
                }
                setResult(Activity.RESULT_OK, intent);
                RailTicket.this.finish();
            } else {
                if (callFromArchivedTicketSet) {
                    RailTicket.this.finish();
                } else {
                    if (callFromOption) {
                        if (!isClickRetak) {
                            mCamera = getCameraInstance();

                            // Create our Preview view and set it as the content
                            // of our
                            // activity.
                            cameraFrame.removeAllViews();
                            mPreview = new CameraPreview(this, mCamera);
                            cameraFrame.addView(mPreview);
                            btTakePhoto.setText("Take Photo");
                            btSave.setVisibility(View.GONE);
                            btRetake.setVisibility(View.GONE);
                            isClickRetak = true;
                        } else {
                            mCamera.takePicture(null, null, mPicture);
                            btSave.setVisibility(View.VISIBLE);
                            btRetake.setVisibility(View.VISIBLE);
                            btTakePhoto.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        mCamera.takePicture(null, null, mPicture);
                        btSave.setVisibility(View.VISIBLE);
                        btRetake.setVisibility(View.VISIBLE);
                        btTakePhoto.setVisibility(View.INVISIBLE);
                    }
                }
            }

        }
        if (v == btRetake) {
            btSave.setVisibility(View.INVISIBLE);
            btRetake.setVisibility(View.INVISIBLE);
            btTakePhoto.setVisibility(View.VISIBLE);
            mCamera.startPreview();
        }
        if (v == btSave) {
            new UploadPhotoTask().execute();
            //			// custom dialog
            //			final Dialog dialog = new Dialog(this);
            //			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //			dialog.setContentView(R.layout.railoption);
            //			// set the custom dialog components - text, image and button
            //			Button btok = (Button) dialog.findViewById(R.id.btrailyes);
            //			Button btno = (Button) dialog.findViewById(R.id.btrailno);
            //			// if button is clicked, close the custom dialog
            //			btok.setOnClickListener(new OnClickListener() {
            //				@Override
            //				public void onClick(View v) {
            //					dialog.dismiss();
            //					
            //
            //				}
            //			});
            //			
            //			btno.setOnClickListener(new OnClickListener() {
            //				@Override
            //				public void onClick(View v) {
            //					dialog.dismiss();
            //				}
            //			});
            //
            //			dialog.show();
        }
    }

    public String executeMultipartPost() throws Exception {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(CompressFormat.JPEG, 75, bos);
            byte[] data = bos.toByteArray();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(Domain.SERVICES_URL + "save_rail_ticket/"
                    + idTicket);
            Log.e("idTicket====", idTicket + "");
            ByteArrayBody bab = new ByteArrayBody(data, "rail" + idTicket + ".jpg");
            // File file= new File("/mnt/sdcard/forest.png");
            // FileBody bin = new FileBody(file);
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("filedata", bab);
            reqEntity.addPart("photoCaption", new StringBody("railticket" + idTicket));
            postRequest.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(postRequest);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "UTF-8"));
            String sResponse;
            StringBuilder s = new StringBuilder();

            while ((sResponse = reader.readLine()) != null) {
                s = s.append(sResponse);
                Log.d("KUNLQT---", "Response: " + s);
                return s.toString();
            }

            // System.out.println("Response: " + s);
        } catch (Exception e) {
            // handle exception here
            Log.e(e.getClass().getName(), e.getMessage());
            return null;
        }
        return null;
    }

    /** A basic Camera preview class */
    public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;

        private Camera mCamera;

        public CameraPreview(Context context, Camera camera) {
            super(context);
            mCamera = camera;

            // Install a SurfaceHolder.Callback so we get notified when the
            // underlying surface is created and destroyed.
            mHolder = getHolder();
            mHolder.addCallback(this);
            // deprecated setting, but required on Android versions prior to 3.0
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            // The Surface has been created, now tell the camera where to draw
            // the preview.
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            } catch (IOException e) {
                Log.d("erro", "Error setting camera preview: " + e.getMessage());
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // empty. Take care of releasing the Camera preview in your
            // activity.
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            // If your preview can change or rotate, take care of those events
            // here.
            // Make sure to stop the preview before resizing or reformatting it.

            if (mHolder.getSurface() == null) {
                // preview surface does not exist
                return;
            }

            // stop preview before making changes
            try {
                mCamera.stopPreview();
            } catch (Exception e) {
                // ignore: tried to stop a non-existent preview
            }

            // set preview size and make any resize, rotate or
            // reformatting changes here

            // start preview with new settings
            try {
                Camera.Parameters parameters = mCamera.getParameters();
                List<Size> sizes = parameters.getSupportedPreviewSizes();
                Size optimalSize = getOptimalPreviewSize(sizes, w, h);
                parameters.setPreviewSize(optimalSize.width, optimalSize.height);
                parameters.setPictureFormat(PixelFormat.JPEG);
                if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                    parameters.set("orientation", "portrait");
                    mCamera.setDisplayOrientation(90);
                } else {
                    parameters.set("orientation", "landscape");
                    mCamera.setDisplayOrientation(0);
                }
                mCamera.setParameters(parameters);
                mCamera.startPreview();
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();

            } catch (Exception e) {
                Log.d("", "Error starting camera preview: " + e.getMessage());
            }
        }
    }

    private PictureCallback mPicture = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            pictureFile = getOutputMediaFile(1);
            if (pictureFile == null) {
                Log.d("", "Error creating media file, check storage permissions: " + "");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d("", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("", "Error accessing file: " + e.getMessage());
            }
        }
    };

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == 1) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp
                    + ".jpg");
        } else if (type == 2) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp
                    + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.05;
        double targetRatio = (double) w / h;
        if (sizes == null)
            return null;

        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
            int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    private class UploadPhotoTask extends AsyncTask<Void, Void, Void> {
        private String result;

        private String result1;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //bm = BitmapFactory.decodeFile(pictureFile.getPath(), options);// ("/sdcard/DCIM/forest.png");
                bm = decodeSampledBitmapFromResource(pictureFile.getPath(), 480, 800);

                result = executeMultipartPost();
                if (result != null && !result.equals("")) {
                    JSONObject jsonOBJ = new JSONObject(result);
                    if (jsonOBJ.getBoolean("status")) {
                        String url = jsonOBJ.getString("picture");
                        result1 = UpdateRailInRunlog(url);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("saving...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (result1 != null && !result1.equals("")) {
                    JSONObject jsonOBJ = new JSONObject(result1);
                    if (jsonOBJ.getBoolean("status")) {
                        if (!callFromOption) {
                            RailTicket.this.finish();
                            Intent intent = new Intent(RailTicket.this, NewRunLog.class);
                            intent.putExtra("idRunLog", idRunlog);
                            intent.putExtra("ticketType", ticketType);
                            intent.putExtra("idTicket", idTicket);
                            startActivity(intent);

                        } else {
                            setResult(Activity.RESULT_OK);
                            RailTicket.this.finish();
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }

    private String UpdateRailInRunlog(String url) {
        String dataRL = ServiceRequest.postData("save_ticket", createJsonTicket(url), "ticket");
        return dataRL;

    }

    private JSONObject createJsonTicket(String url) {
        JSONObject tesoroJson = new JSONObject();
        try {
            tesoroJson.put("id", idTicket);
            tesoroJson.put("rail", true);
            tesoroJson.put("picture", url);
            // tesoroJson.put("ticket", createJsonTypeTicket(4));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tesoroJson;

    }

    private Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }

    private class loadPhotoTask extends AsyncTask<Void, Void, Void> {
        private Drawable drawable;

        private String url;

        public loadPhotoTask(String url) {
            this.url = url;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                drawable = LoadImageFromWebOperations(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (drawable != null) {
                    Log.e("drawable", "null");
                    RotateImageView img = new RotateImageView(RailTicket.this);
                    Bitmap bm = ((BitmapDrawable) drawable).getBitmap();
                    img.init(bm, 90);
                    cameraFrame.addView(img, new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.FILL_PARENT,
                            FrameLayout.LayoutParams.FILL_PARENT, Gravity.CENTER));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }
}
