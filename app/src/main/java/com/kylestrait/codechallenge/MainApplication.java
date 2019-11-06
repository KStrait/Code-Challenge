package com.kylestrait.codechallenge;

import android.content.Context;

import com.kylestrait.codechallenge.di.AppComponent;
import com.kylestrait.codechallenge.di.DaggerAppComponent;
import com.kylestrait.codechallenge.widget.binding.AppDataBindingComponent;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

// Main application, handles some Dagger2 things
public class MainApplication extends DaggerApplication {

    @Inject
    AppComponent appComponent;

    @Inject
    AppDataBindingComponent dataBindingComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        // need to set default cookie handler before injection happens in super.onCreate()
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        super.onCreate();
        DataBindingUtil.setDefaultComponent(dataBindingComponent);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .create(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppComponent getAppComponent(Context context) {
        return ((MainApplication)context.getApplicationContext()).getAppComponent();
    }
}
