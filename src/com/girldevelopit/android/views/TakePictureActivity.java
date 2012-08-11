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

    //when we take or select a photo, we have to do something with the takepicture we get back
    //these integers are our way of knowing if we took a takepicture or selected an existing takepicture

    //there are four elements in our layout that we will want to get data from, we declare them here and initialize them below

    //in order to save our image, we need to know the file name


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

    }

    private void initElements(){

    }

    public void takePicture(View view){

    }

    public void choosePicture(View view){
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }

    public void save(View v){

    }
}
