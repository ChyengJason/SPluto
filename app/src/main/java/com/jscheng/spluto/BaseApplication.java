package com.jscheng.spluto;

import android.app.Application;
import android.util.Printer;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;

import java.util.logging.Handler;

/**
 * Created By Chengjunsen on 2018/11/24
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BlockCanary.install(this, new BlockCanaryContext()).start();
    }
}
