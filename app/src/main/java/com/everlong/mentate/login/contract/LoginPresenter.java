package com.everlong.mentate.login.contract;

import android.text.TextUtils;

import com.everlong.mentate.util.Constants;
import com.everlong.mentate.util.KeyValueStore;

/**
 * Created by akshit on 3/17/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private KeyValueStore keyValueStore;
    private String gender;
    private String name;
    private String age;

    public LoginPresenter(LoginContract.View view, KeyValueStore keyValueStore) {
        this.view = view;
        this.keyValueStore = keyValueStore;
    }

    @Override
    public void checkForLogin(boolean isLoggedIn) {
        if (isLoggedIn) {
            view.launchHomeActivity();
        } else {
            view.setLoginView();
        }
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void validateUserDetails() {
        if (validateData()) {
            view.showProgressDialog();
            view.signInUser();
        } else {
            view.showMissingInfoToast();
        }
    }

    private boolean validateData() {
        name = view.getName();
        age = view.getAge();
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(gender));
    }

    @Override
    public void saveUserDetails() {
        keyValueStore.put(Constants.KEY_NAME, name);
        keyValueStore.put(Constants.KEY_AGE, age);
        keyValueStore.put(Constants.KEY_GENDER, gender);
    }
}
