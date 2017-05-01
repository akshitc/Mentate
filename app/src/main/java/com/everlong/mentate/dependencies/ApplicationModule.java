package com.everlong.mentate.dependencies;

import android.app.Application;
import android.support.annotation.NonNull;

import com.everlong.mentate.game.repository.GameRepository;
import com.everlong.mentate.game.repository.IGame;
import com.everlong.mentate.util.KeyValueStore;
import com.everlong.mentate.util.SharedPreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshit on 3/16/17.
 */
@Module
public class ApplicationModule {

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @NonNull
    @Singleton
    Application provideMainApp() {
        return application;
    }

    @Provides
    @NonNull
    @Singleton
    KeyValueStore provideKeyValueStore() {
        return new SharedPreferenceManager(application);
    }

    @Provides
    @NonNull
    @Singleton
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @NonNull
    @Singleton
    IGame providesIGame() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return new GameRepository(databaseReference);
    }
}
