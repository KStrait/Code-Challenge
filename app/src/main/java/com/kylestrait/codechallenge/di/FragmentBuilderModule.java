package com.kylestrait.codechallenge.di;

import com.kylestrait.codechallenge.login.LoginFragment;
import com.kylestrait.codechallenge.reposlist.RepoWebViewFragment;
import com.kylestrait.codechallenge.reposlist.ReposFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract ReposFragment bindReposFragment();

    @ContributesAndroidInjector
    abstract RepoWebViewFragment bindRepoWebViewFragment();
}
