package com.kylestrait.codechallenge.di;

import com.kylestrait.codechallenge.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Module to support injecting activities.
 *
 * Bind all activity classes here.
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity bindMainActivity();
}