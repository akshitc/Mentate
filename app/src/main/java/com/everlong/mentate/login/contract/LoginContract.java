package com.everlong.mentate.login.contract;

/**
 * Created by akshit on 3/17/17.
 */

public interface LoginContract {

    interface View {

        void setLoginView();

        void launchHomeActivity();

        void signInUser();

        String getName();

        String getAge();

        void showMissingInfoToast();

        void showProgressDialog();
    }

    interface Presenter {

        void setGender(String gender);

        void saveUserDetails();

        void checkForLogin(boolean isLoggedIn);

        void validateUserDetails();
    }
}
