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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.R;
import com.girldevelopit.android.models.ImageModel;
import com.girldevelopit.android.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
//this is the activity that the user will use to take a takepicture, add a title and description and save it to our list of pictures
//it extends activity which is the android class that allows you to build any class that the user
//interacts with
public class TakePictureActivity extends Activity
{

    private GirlDevelopIt app;

    //when we take or select a photo, we have to do something with the takepicture we get back
    //these integers are our way of knowing if we took a takepicture or selected an existing takepicture
    private final int ACTIVITY_TAKE_PHOTO = 1;
    private final int ACTIVITY_SELECT_PHOTO = 2;

    //there are four elements in our layout that we will want to get data from, we declare them here and initialize them below
    private ImageView pictureFromCamera;
    private EditText titleField;
    private EditText descriptionField;
    private Button savePicture;
    //in order to save our image, we need to know the file name
    private String pathToImage ="";

    /** Called when the activity is first created.
     The code in here is what the phone goes through first
     The @override is there because onCreate is a function in the Activity class we extended
     we override the default functionality of that method. The default functionality is just to
     create an acitivity that the user can see. we want to do that AND make that activity look
     and act like the one we are trying to build. Every single activity in every single android
     application has this function
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takepicture);
        this.app = (GirlDevelopIt)getApplicationContext();
        initElements();
    }

    private void initElements(){
        pictureFromCamera = (ImageView)this.findViewById(R.id.pictureFromCamera);
        titleField = (EditText)this.findViewById(R.id.titleField);
        descriptionField = (EditText) this.findViewById(R.id.descriptionField);
        savePicture = (Button) this.findViewById(R.id.savePicture);
        if(app.getUsername()==null || app.getUsername().equals("")){
            savePicture.setEnabled(false);
        }
    }

    public void takePicture(View view){
        final Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(Utils.getTempFile(getApplicationContext())));
        startActivityForResult(intent, ACTIVITY_TAKE_PHOTO);
    }

    public void choosePicture(View view){
        final Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, ACTIVITY_SELECT_PHOTO);
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
                pathToImage = Utils.getPath(data.getData(), getContentResolver());

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
            ImageModel imageModel = new ImageModel(imageTitle, app.getUsername(), imageDescription, pathToImage, new Date().getTime());
            ArrayList<ImageModel> imageList = app.getImages();
            imageList.add(0, imageModel);
            app.setImages(imageList);
            Intent intent = new Intent(TakePictureActivity.this, GalleryActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
