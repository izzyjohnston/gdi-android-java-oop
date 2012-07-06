package com.girldevelopit.android.utils;

import android.content.Context;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.models.ImageModel;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: izzyjohnston
 * Date: 7/6/12
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataStore {
    private static final String PREFS_NAME = "girlDevelopItPrefs";

    private Context context;

    public DataStore(Context theContext)
    {
        context = theContext;
    }

    public void saveExternalImageData(ArrayList<ImageModel> requestDraftVOs){
        final File suspend_f=new File(GirlDevelopIt.cacheDir, "images");

        FileOutputStream fos  = null;
        ObjectOutputStream oos  = null;
        boolean            keep = true;

        try {
            fos = new FileOutputStream(suspend_f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(requestDraftVOs);
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
        ArrayList<ImageModel> requestDraftVOs = new ArrayList<ImageModel>();
        final File suspend_f=new File(GirlDevelopIt.cacheDir, "user_drafts");

        FileInputStream fis = null;
        ObjectInputStream is = null;
        // boolean            keep = true;

        try {

            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            requestDraftVOs  = (ArrayList<ImageModel>) is.readObject();
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
        return requestDraftVOs;
    }
}
