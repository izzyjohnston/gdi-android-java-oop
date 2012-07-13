package com.girldevelopit.android;

import android.app.Application;
import com.girldevelopit.android.models.ImageModel;
import com.girldevelopit.android.utils.DataStore;
import java.io.File;
import java.util.ArrayList;


public class GirlDevelopIt extends Application {
    private DataStore store;

    @Override
    public void onCreate()
    {
        super.onCreate();
        store = new DataStore(this);
    }


    public void setImages(ArrayList<ImageModel> img){
        store.saveExternalImageData(img);
    }
    public ArrayList<ImageModel> getImages(){
        return store.getExternalImageData();
    }

    public void setUsername(String name){
        store.saveToPrefs("USERNAME", name);
    }

    public String getUsername(){
        return store.getFromPrefs("USERNAME", "");
    }

    public File getCacheDir(){
        File cachedir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
            cachedir = new File(android.os.Environment.getExternalStorageDirectory(),"GirlDevelopItAndroid");
        }
        else{
            cachedir = getCacheDir();
        }

        if(!cachedir.exists())
            cachedir.mkdirs();
        return  cachedir;
    }
}
