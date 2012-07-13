package com.girldevelopit.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.models.ImageModel;

import java.io.*;
import java.util.ArrayList;

public class DataStore {
    private static final String PREFS_NAME = "girlDevelopItPrefs";

    private Context context;
    private GirlDevelopIt app;

    public DataStore(Context theContext)
    {
        context = theContext;
        this.app = (GirlDevelopIt)context.getApplicationContext();
    }

    public void saveToPrefs(String key, String value){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key, value);

        editor.commit();
    }

    public String getFromPrefs(String key, String defaultValue){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return prefs.getString(key, defaultValue);
    }

    public void saveExternalImageData(ArrayList<ImageModel> imagesList){
        final File suspend_f=new File(app.getCacheDir(), "images");

        FileOutputStream fos  = null;
        ObjectOutputStream oos  = null;
        boolean            keep = true;

        try {
            fos = new FileOutputStream(suspend_f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(imagesList);
        }
        catch (Exception e) {
            keep = false;


        }
        finally {
            try {
                if (oos != null)   oos.close();
                if (fos != null)   fos.close();
                if (keep == false) suspend_f.delete();
            }
            catch (Exception e) { /* do nothing */ }
        }
    }
    public ArrayList<ImageModel> getExternalImageData(){
        ArrayList<ImageModel> imagesList = new ArrayList<ImageModel>();
        final File suspend_f=new File(app.getCacheDir(), "images");

        FileInputStream fis = null;
        ObjectInputStream is = null;
        // boolean            keep = true;

        try {

            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            imagesList  = (ArrayList<ImageModel>) is.readObject();
        }catch(Exception e)
        {
            String val= e.getMessage();


        }finally {
            try {
                if (fis != null)   fis.close();
                if (is != null)   is.close();

            }
            catch (Exception e) { }
        }
        return imagesList;
    }
}
