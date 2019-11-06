package com.kylestrait.codechallenge.di;

import com.kylestrait.codechallenge.MainApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class,
        ActivityBuilderModule.class})
public interface AppComponent extends AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainApplication> {
        public abstract AppComponent build();
    }

    void inject(MainApplication mainApplication);
}
