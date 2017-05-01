package com.everlong.mentate.login.di;

import com.everlong.mentate.dependencies.ApplicationComponent;
import com.everlong.mentate.login.ui.LoginActivity;
import com.everlong.mentate.util.ActivityScope;

import dagger.Component;

/**
 * Created by akshit on 3/18/17.
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = ApplicationComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}
