package com.everlong.mentate.home.di;

import com.everlong.mentate.dependencies.ApplicationComponent;
import com.everlong.mentate.home.ui.HomeActivity;
import com.everlong.mentate.util.ActivityScope;

import dagger.Component;

/**
 * Created by akshit on 3/19/17.
 */
@ActivityScope
@Component(modules = HomeModule.class, dependencies = ApplicationComponent.class)
public interface HomeComponent {

    void inject(HomeActivity activity);
}
