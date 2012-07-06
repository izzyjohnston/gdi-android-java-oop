package com.girldevelopit.android.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.R;
import com.girldevelopit.android.models.ImageModel;
import com.girldevelopit.android.utils.DataStore;
import com.girldevelopit.android.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class MainActivity extends Activity
{
    private final int ACTIVITY_TAKE_PHOTO = 1;
    private final int ACTIVITY_SELECT_PHOTO = 2;

    private ImageView pictureFromCamera;
    private EditText titleField;
    private EditText descriptionField;
    private String pathToImage ="";
    DataStore store;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pictureFromCamera = (ImageView)this.findViewById(R.id.pictureFromCamera);
        titleField = (EditText)this.findViewById(R.id.titleField);
        descriptionField = (EditText) this.findViewById(R.id.descriptionField);
        store = new DataStore(this);
        GirlDevelopIt.IMAGES = store.getExternalImageData();
    }

    public void takePicture(View view){
        final Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(Utils.getTempFile(getApplicationContext())));
        startActivityForResult(intent, ACTIVITY_TAKE_PHOTO);
    }

    public void choosePicture(View view){
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), ACTIVITY_SELECT_PHOTO);
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
                pathToImage=Utils.getTempFile(getApplicationContext()).getAbsolutePath();
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
        if(requestCode == ACTIVITY_SELECT_PHOTO){
            try{
                System.gc();
                String filePath = Utils.getPath(data.getData(), getContentResolver());

                pathToImage = new String(filePath);

                File selFile=new File(pathToImage);

                Bitmap thumbnailBmp = Utils.decodeFile(selFile);
                pictureFromCamera.setImageBitmap(thumbnailBmp);
                pictureFromCamera.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            }
            catch(Exception exp){
                Log.e("TakePhoto", exp.getMessage());
            }
        }
    }

    public void save(View v){
        String imageTitle = titleField.getText().toString();
        String imageDescription = descriptionField.getText().toString();
        if(imageTitle.equals("") || imageDescription.equals("") || pathToImage.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Sorry, all fields are required")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else{
            ImageModel imageModel = new ImageModel(imageTitle, GirlDevelopIt.USERNAME, imageDescription, pathToImage, new Date().getTime());
            GirlDevelopIt.IMAGES.add(imageModel);
            store = new DataStore(this);
            store.saveExternalImageData(GirlDevelopIt.IMAGES);
        }
    }
}
