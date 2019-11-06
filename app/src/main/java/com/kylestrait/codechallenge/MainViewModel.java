package com.kylestrait.codechallenge;

import com.kylestrait.codechallenge.widget.lifecycle.SingleLiveEvent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public static final int VIEW_LOGIN = 0;
    public static final int VIEW_REPO_LIST = 1;
    public static final int VIEW_WEB = 2;
    public static final int VIEW_NONE = -1;

    public static final int ACTION_GO_BACK = 0;

    private final MutableLiveData<Integer> mainView;
    private final MutableLiveData<Integer> mainAction;

    public String name, url;

    @Inject
    MainViewModel() {
        mainView = new MutableLiveData<>();
        mainView.setValue(VIEW_LOGIN);

        mainAction = new SingleLiveEvent<>();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VIEW_LOGIN, VIEW_REPO_LIST, VIEW_WEB, VIEW_NONE})
    public @interface MainView {
    }

    public void setMainView(@MainView int newMainView) {
        mainView.setValue(newMainView);
    }

    public LiveData<Integer> getMainView() {
        return mainView;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ACTION_GO_BACK})
    public @interface MainAction {
    }

    public void setMainAction(@MainAction int newMainAction) {
        mainAction.setValue(newMainAction);
    }

    @NonNull
    LiveData<Integer> getMainAction() {
        return mainAction;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void goBack() {
        mainAction.setValue(ACTION_GO_BACK);
    }

    public void showRepoWebView(String name, String url) {
        this.name = name;
        this.url = url;
        setMainView(VIEW_WEB);
    }
}
