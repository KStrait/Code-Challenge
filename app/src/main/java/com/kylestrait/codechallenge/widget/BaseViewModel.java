package com.kylestrait.codechallenge.widget;

import com.kylestrait.codechallenge.MainViewModel;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;


/**
 * Base class for any view model that needs access to the {@link MainViewModel}.
 *
 * When used in conjunction with {@link BaseFragment} the mainViewModel field will be set automatically.
 *
 * Classes extending this must still check that mainViewModel is not null before using it.
 */
public abstract class BaseViewModel<M> extends ViewModel implements Observable {
    private static final String TAG = BaseViewModel.class.getSimpleName();
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();
    protected M model;
    protected MainViewModel mainViewModel;

    public BaseViewModel() { }

    public abstract void resume();
    public void pause() {}
    public void destroy() {}

    public MainViewModel getMainViewModel(){
        return mainViewModel;
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    protected void notifyPropertyChanged(Observable observable, int property) {
        registry.notifyChange(observable, property);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback);
    }
}