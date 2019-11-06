package com.kylestrait.codechallenge.widget;

import android.content.Context;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

/**
 * Helper base class for fragments that use a view model requiring access to the {@link com.kylestrait.codechallenge.MainViewModel}.
 *
 * In child classes, following the call to super.onAttach() the viewModel field will be set and, if available,
 * the main view model will be set in the view model. View model classes must still check for null on main view model.
 *
 * Classes extending this should have a no-argument constructor that calls super with the required view model class. For example:
 *
 * public class InfoFragment extends BaseFragment&lt;InfoViewModel&gt; {
 *      public InfoFragment() {
 *          super(InfoViewModel.class);
 *      }
 * }
 *
 * @param <T> View model class used by the fragment.
 */
public abstract class BaseFragment<T extends BaseViewModel> extends DaggerFragment {
    private final Class<T> clazz;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected T viewModel;

    protected BaseFragment(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(clazz);
        if (context instanceof MainViewModelProvider)
            viewModel.setMainViewModel(((MainViewModelProvider)context).provideMainViewModel());
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.pause();
    }
}