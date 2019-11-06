package com.kylestrait.codechallenge.login;

import com.kylestrait.codechallenge.MainViewModel;
import com.kylestrait.codechallenge.data.Login;
import com.kylestrait.codechallenge.util.LoginValidator;
import com.kylestrait.codechallenge.util.SharedPreferencesManager;
import com.kylestrait.codechallenge.widget.BaseViewModel;
import com.kylestrait.codechallenge.widget.lifecycle.SingleLiveEvent;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class LoginViewModel extends BaseViewModel<LoginViewModel> {

    private LoginValidator loginValidator;
    private SharedPreferencesManager sharedPreferencesManager;

    private SingleLiveEvent<Boolean> helpClicked = new SingleLiveEvent<>();
    private SingleLiveEvent<Boolean> loginClicked = new SingleLiveEvent<>();

    @Inject
    LoginViewModel(LoginValidator loginValidator, SharedPreferencesManager sharedPreferencesManager) {
        this.loginValidator = loginValidator;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }


    @Override
    public void resume() {

    }

    public void setHelpClicked(Boolean clicked) {
        helpClicked.setValue(clicked);
    }

    public LiveData<Boolean> getHelpClicked() {
        return helpClicked;
    }

    public void onLoginClicked() {
        loginClicked.setValue(true);
    }

    public LiveData<Boolean> getLoginClicked() {
        return loginClicked;
    }

    public Login getUserInfo() {
        return sharedPreferencesManager.getLoginInfo();
    }

    public Boolean validate(String email, String password) {
        if (email != null && password != null) {
            if (loginValidator.validateEmail(email) && loginValidator.validatePassword(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void onLoginSuccess(Login user) {
        sharedPreferencesManager.putLoginInfo(user);
        mainViewModel.setMainView(MainViewModel.VIEW_REPO_LIST);
    }
}
