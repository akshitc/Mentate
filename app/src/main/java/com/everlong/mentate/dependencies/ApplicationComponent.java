package com.everlong.mentate.dependencies;

import com.everlong.mentate.game.repository.IGame;
import com.everlong.mentate.util.KeyValueStore;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by akshit on 3/16/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    KeyValueStore keyValueStore();

    FirebaseAuth firebaseAuth();

    IGame repository();
}
