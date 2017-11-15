package com.example.rxjava;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by T_baby on 17/11/03.
 */

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
