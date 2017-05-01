package com.everlong.mentate.game.di;

import com.everlong.mentate.dependencies.ApplicationComponent;
import com.everlong.mentate.game.ui.GameActivity;
import com.everlong.mentate.util.ActivityScope;

import dagger.Component;

/**
 * Created by akshit on 3/25/17.
 */
@ActivityScope
@Component(modules = GameModule.class, dependencies = ApplicationComponent.class)
public interface GameComponent {

    void inject(GameActivity activity);
}
