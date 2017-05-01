package com.everlong.mentate;

import android.app.Application;

import com.everlong.mentate.dependencies.ApplicationComponent;
import com.everlong.mentate.dependencies.ApplicationModule;
import com.everlong.mentate.dependencies.DaggerApplicationComponent;

/**
 * Created by akshit on 3/16/17.
 */

public class MainApplication extends Application {

    private ApplicationComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApiComponent;
    }
}
