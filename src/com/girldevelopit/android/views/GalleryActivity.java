package com.girldevelopit.android.views;

import android.app.Activity;
import android.os.Bundle;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.R;


public class GalleryActivity extends Activity {
    private GirlDevelopIt app;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture);
        this.app = (GirlDevelopIt)getApplicationContext();
    }
}
