package com.girldevelopit.android.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.R;
import com.girldevelopit.android.adapters.ImageListAdapter;
import com.girldevelopit.android.models.ImageModel;

import java.util.ArrayList;

/**
 * This is the gallery activity which shows a list of all the pictures we have taken
 */
public class GalleryActivity extends Activity {
    private GirlDevelopIt app;

    //the only element on the page is a listview
    private ListView imageListView;
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
        //set up which view we will be using
        setContentView(R.layout.gallery);
        //initialize our application
        this.app = (GirlDevelopIt)getApplicationContext();
        //initialize the elements in our view
        initElements();
        //populate our list view with the images we have taken
        populateImagesList();
    }

    /**
     * set the value of imageListView to be the list view whose id we set in the layout.
     * set an on click listener to open up the view picture activity when you click on an item in the view
     */
    private void initElements(){
        imageListView = (ListView)this.findViewById(R.id.imageListView);

        imageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //a bundle is extra data we can send between activities
                Bundle b = new Bundle();
                //we are going to send an integer...the position of the image in the list we clicked on
                b.putInt("image_position", position);
                //create an intent that will open up ViewPictureActivity.class from GalleryActivity
                Intent intent = new Intent(GalleryActivity.this, ViewPictureActivity.class);
                //add the extra data to the intent
                intent.putExtras(b);
                //start the activity
                startActivity(intent);
            }
        });
    }

    /**
     * "Inflate" our list using the image list adapter
     */
    private  void populateImagesList(){
        //create an inflater that has the ability to build a view programmatically
        LayoutInflater mLInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //create an imagelistadapter with the arraylist of images we have saved in our app
        ImageListAdapter listAdapter = new ImageListAdapter(this, app.getImages(), mLInflater);
        //set imagelistadapter as the adapter for our image list
        imageListView.setAdapter(listAdapter);
        //the next two lines inform the list view that it should load all the data from the adapter
        imageListView.invalidateViews();
        ((BaseAdapter)imageListView.getAdapter()).notifyDataSetChanged();
    }
}
