package com.girldevelopit.android.views;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.R;
import com.girldevelopit.android.models.ImageModel;

import android.text.format.DateFormat;
import com.girldevelopit.android.utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewPictureActivity extends Activity {
    private GirlDevelopIt app;
    private TextView username;
    private TextView date;
    private TextView description;
    private ImageView image;
    private int imagePosition;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpicture);
        this.app = (GirlDevelopIt)getApplicationContext();
        Bundle b;
        b = getIntent().getExtras();
        imagePosition = b.getInt("image_position");
        initElements();
    }

    private void initElements(){
        username = (TextView)this.findViewById(R.id.username);
        date = (TextView)this.findViewById(R.id.date);
        description = (TextView)this.findViewById(R.id.description);
        image = (ImageView)this.findViewById(R.id.image);

        ImageModel imageModel = app.getImages().get(imagePosition);

        if(imageModel.getUsername()!=null) username.setText(imageModel.getUsername());

        SimpleDateFormat sdf = new SimpleDateFormat("MM dd, yyyy HH:mm", Locale.getDefault());
        Date d = new Date(imageModel.getDateCreated());
        date.setText(sdf.format(d));

        if(imageModel.getDescription()!=null) description.setText(imageModel.getDescription());

        String pathToImage = imageModel.getPathToImage();
        //get the file on the phone associated with that path name
        File selFile=new File(pathToImage);
        //make a bitmap out of the raw data of the file (remember computers still think everything is
        //all ones and zeroes so we have to explicitly tell it that our takepicture is in fact a takepicture
        Bitmap thumbnailBmp = Utils.decodeFile(selFile);
        //we set the bitmap of our image view as the bitmap we just made
        image.setImageBitmap(thumbnailBmp);
        //now, we set the scale type. because this is only a thumbnail, the thumbnail won't be able to
        //show the whole takepicture clearly so we crop it to just show the center square
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}