package com.everlong.mentate.score.di;

import com.everlong.mentate.dependencies.ApplicationComponent;
import com.everlong.mentate.score.ui.ScoreActivity;
import com.everlong.mentate.util.ActivityScope;

import dagger.Component;

/**
 * Created by akshit on 4/11/17.
 */
@ActivityScope
@Component(modules = ScoreModule.class, dependencies = ApplicationComponent.class)
public interface ScoreComponent {

    void inject(ScoreActivity activity);
}
