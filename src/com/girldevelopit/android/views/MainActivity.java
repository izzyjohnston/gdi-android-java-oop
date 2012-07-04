package com.girldevelopit.android.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.girldevelopit.android.R;
import com.girldevelopit.android.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends Activity
{
    private final int ACTIVITY_TAKE_PHOTO = 1;
    private ImageView pictureFromCamera;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pictureFromCamera = (ImageView)this.findViewById(R.id.pictureFromCamera);
    }

    public void takePicture(View view){
        final Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(Utils.getTempFile(getApplicationContext())));
        startActivityForResult(intent, ACTIVITY_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        if(requestCode == ACTIVITY_TAKE_PHOTO){
            try{
                File takenFile=Utils.getTempFile(getApplicationContext());
                Bitmap thumbnailBmp = Utils.decodeFile(takenFile);
                pictureFromCamera.setImageBitmap(thumbnailBmp);
                pictureFromCamera.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                try
                {
                    FileOutputStream ostream = new FileOutputStream(takenFile);
                    thumbnailBmp.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                System.gc();

            }
            catch(Exception exp){
                Log.e("TakePhoto", exp.getMessage());
            }
        }
    }
}
