package com.girldevelopit.android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.girldevelopit.android.R;
import com.girldevelopit.android.models.ImageModel;
import com.girldevelopit.android.utils.Utils;

import java.io.File;
import java.util.List;

//base adapter is the class that allows us to build lists
//because we want each row in our list to have special elements
//like the image thumbnail and title of the image, we create a class that
//extends BaseAdapter  
public class ImageListAdapter extends BaseAdapter {
    //our private variables that are used in the methods in our class
    private List<ImageModel> listItems;
    private final Context context;
    private LayoutInflater inflater;

    //the "constructor" method that our activity will call
    //it sets all the values for our private variables
    public ImageListAdapter(Context context, List<ImageModel> listItems, LayoutInflater inflater) {
        this.context = context;
        this.listItems = listItems;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     This method builds each row in the list. Because we sent an array of image models
     It uses the data for each image model sent to build a new row. So we have to get the
     row layout that is in the res folder. Then write code to say which image should go in
     the imagview and then put the text that we saved as the title in the textview
     **/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //this gets the imagemodel associated with the current row
        ImageModel imageModel = listItems.get(position);
        //then we get the layout in our res file
        //inflater is a lot like setContentView in that it says which layout xml we will use
        View newView = inflater.inflate(R.layout.image_list_cell, null);

        //just like in activities, we have to tell the code which elements in our layout to use
        //the only difference is we say newView (our variable above) instead of "this"
        //we do so because we do this because the view we are building is no longer for the whole activity
        ImageView imageThumbnail = (ImageView)newView.findViewById(R.id.imageThumbnail);
        //get the path name of the image from our model
        String pathToImage = imageModel.getPathToImage();
        //get the file on the phone associated with that path name
        File selFile=new File(pathToImage);
        //make a bitmap out of the raw data of the file (remember computers still think everything is
        //all ones and zeroes so we have to explicitly tell it that our takepicture is in fact a takepicture
        Bitmap thumbnailBmp = Utils.decodeFile(selFile);
        //we set the bitmap of our image view as the bitmap we just made
        imageThumbnail.setImageBitmap(thumbnailBmp);
        //now, we set the scale type. because this is only a thumbnail, the thumbnail won't be able to 
        //show the whole takepicture clearly so we crop it to just show the center square
        imageThumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //get the texview in our layout where we want to put our title
        TextView imageTitle = (TextView)newView.findViewById(R.id.imageTitle);
        //set the title to be the title in our image model
        imageTitle.setText(imageModel.getTitle());

        //return the view. remember this is a method that has to return an object
        //the object is our row all built
        //the returned object is then put into the list
        return newView;
    }
}