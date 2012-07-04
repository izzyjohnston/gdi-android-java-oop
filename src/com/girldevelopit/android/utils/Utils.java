package com.girldevelopit.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: izzyjohnston
 * Date: 7/4/12
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    /**
     * For image capture
     * @param cont
     * @return
     */
    public static File getTempFile(Context cont) {
        // it will return /sdcard/image.tmp
        final File path = new File(Environment.getExternalStorageDirectory(), cont.getPackageName());
        if (!path.exists()) {
            path.mkdir();
        }
        return new File(path, "image.tmp");
    }

    /**
     * decode image from file
     * @param f
     * @return
     */
    public static Bitmap decodeFile(File f){
        Bitmap b = null;
        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            FileInputStream fis = new FileInputStream(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();

            int scale = 1;
           /* int image_size = 90;
            if (o.outHeight > image_size || o.outWidth > image_size) {
                scale = (int)Math.pow(2, (int) Math.round(Math.log(image_size / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }   */

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();
        } catch (IOException e) {
        }
        return b;
    }
}
