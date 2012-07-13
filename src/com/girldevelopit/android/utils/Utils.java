package com.girldevelopit.android.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
     * @param uri
     * @param resolver
     * @return
     */
    public static String getPath(Uri uri, ContentResolver resolver) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = resolver.query(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
            int image_size = 400;
            if (o.outHeight > image_size || o.outWidth > image_size) {
                scale = (int)Math.pow(2, (int) Math.round(Math.log(image_size / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }

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
