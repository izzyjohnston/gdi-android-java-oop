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

    /**
     * set the value of imageListView to be the list view whose id we set in the layout.
     * set an on click listener to open up the view picture activity when you click on an item in the view
     */
    private void initElements(){

    }

    /**
     * "Inflate" our list using the image list adapter
     */
    private  void populateImagesList(){

    }
}
