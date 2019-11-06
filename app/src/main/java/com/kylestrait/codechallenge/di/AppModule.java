package com.kylestrait.codechallenge.di;

import android.content.Context;

import com.google.gson.Gson;
import com.kylestrait.codechallenge.MainApplication;
import com.kylestrait.codechallenge.network.WebService;
import com.kylestrait.codechallenge.util.DateFormatter;
import com.kylestrait.codechallenge.util.LoginValidator;
import com.kylestrait.codechallenge.util.SharedPreferencesManager;
import com.squareup.moshi.Moshi;

import java.net.CookieHandler;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import dagger.Module;
import dagger.Provides;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = {ViewModelModule.class})
class AppModule {

    @Provides
    @Singleton
    Context provideContext(MainApplication application) {
        return application;
    }

    @Provides
    @Singleton
    @Named("mainActivityRunning")
    MutableLiveData<Boolean> provideMainActivityRunning() {
        return new MutableLiveData<>();
    }

    @Provides @Singleton
    Moshi provideMoshi() {
        return new Moshi.Builder()
                .build();
    }

    @Provides @Singleton
    MoshiConverterFactory provideMoshiConverterFactory(Moshi moshi) {
        return MoshiConverterFactory.create(moshi);
    }

    @Provides @Singleton
    OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .cache(cache)
                .cookieJar(new JavaNetCookieJar(CookieHandler.getDefault()))
                .retryOnConnectionFailure(true)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                //BELOW LINE IS FOR LOGGING IF YOU'RE DEBUGGING RETROFIT CALLS
                //Only used for testing
//                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                 semicolon on own line to make commenting above in/out easier
                ;
        return builder.build();
    }

    @Provides @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides @Singleton
    SharedPreferencesManager provideSharedPreferencesManager(Context context, Gson gson) {
        return new SharedPreferencesManager(context, gson);
    }

    @Provides @Singleton
    LoginValidator providesLoginValidator() {
        return new LoginValidator();
    }

    @Provides @Singleton
    DateFormatter providesDateFormatter() {
        return new DateFormatter();
    }

    @Singleton @Provides
    WebService provideWebservice(OkHttpClient okHttpClient, MoshiConverterFactory moshiConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .client(okHttpClient)
                .build()
                .create(WebService.class);
    }

    @Singleton @Provides
    Executor provideHttpExecutor() {
        return Executors.newCachedThreadPool();
    }
}
