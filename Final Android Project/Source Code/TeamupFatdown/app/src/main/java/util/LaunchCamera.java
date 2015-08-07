package util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * This class is responsible for launching the camera to take pictures
 * and send back to the class change
 */
public class LaunchCamera extends Activity {
    public static final String IMAGEFILEPATH = "ImageFilePath";
    public static final String IMAGE_PATH = "image_path";
    private static final int GET_IMAGE_REQ = 5000;
    private String mImageFilePath;
    private static Activity mContext;
    private static Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {

            mImageFilePath = savedInstanceState.getString(IMAGEFILEPATH);

            File mFile = new File(IMAGEFILEPATH);
            if (mFile.exists()) {
                Intent rsl = new Intent();
                rsl.putExtra(IMAGE_PATH, mImageFilePath);
                setResult(Activity.RESULT_OK, rsl);
                finish();
            } else {
            }
        }

        mContext = this;
        applicationContext = getApplicationContext();
        if (savedInstanceState == null) {
            initialUI();
        }

    }

    /**
     * Initialize the UI
     */
    public void initialUI() {
        long ts = System.currentTimeMillis();
        mImageFilePath = getCameraPath() + ts + ".jpg";
        File out = new File(mImageFilePath);
        showCamera(out);

    }

    /**
     * Use the camera
     *
     * @param out
     */
    private void showCamera(File out) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out)); // set
        startActivityForResult(intent, GET_IMAGE_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (GET_IMAGE_REQ == requestCode && resultCode == Activity.RESULT_OK) {
            Intent rsl = new Intent();
            rsl.putExtra(IMAGE_PATH, mImageFilePath);
            fileScan(mImageFilePath);
            mContext.setResult(Activity.RESULT_OK, rsl);
            mContext.finish();

        } else {
            mContext.finish();
        }
    }

    /**
     * Scan the file in order to show the picture in the gallery
     *
     * @param filePath
     */
    public void fileScan(String filePath) {
        Uri data = Uri.parse("file://" + filePath);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, data));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ImageFilePath", mImageFilePath + "");

    }

    /**
     * Get the camera path from the sdcard
     *
     * @return
     */
    public static String getCameraPath() {
        String filePath = getImageRootPath() + "/camera/";
        File file = new File(filePath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        file = null;
        return filePath;
    }

    /**
     * Get the root path of image
     *
     * @return
     */
    public static String getImageRootPath() {
        String filePath = getAppRootPath() + "/image";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = null;
        return filePath;
    }

    /**
     * Get the app' root path
     *
     * @return
     */
    public static String getAppRootPath() {
        String filePath = "/b";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            filePath = Environment.getExternalStorageDirectory() + filePath;
        } else {
            filePath = applicationContext.getCacheDir() + filePath;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = null;
        return filePath;
    }

}
