package com.xiao.bsgen;

import android.app.Application;

/**
 * Created by Xiao on 6/30/13.
 */
public class BSGenApplication extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        initSingletons();
    }
    private void initSingletons()
    {
        BSGenerator.init(this);
    }
}
