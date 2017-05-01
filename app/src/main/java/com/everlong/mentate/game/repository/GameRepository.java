package com.everlong.mentate.game.repository;

import com.everlong.mentate.game.models.ScoreModel;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by akshit on 3/25/17.
 */

public class GameRepository implements IGame {

    private DatabaseReference databaseReference;

    public GameRepository(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Observable getGameData() {
        Query query = databaseReference.child("data");

        return observe(query);
    }

    @Override
    public Observable getScoreData() {
        Query query = databaseReference.child("scores").orderByChild("score").startAt(3);

        return observe(query);
    }

    @Override
    public void saveScore(ScoreModel scoreModel) {
        String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("scores").child(uId).setValue(scoreModel);
    }

    private Observable<DataSnapshot> observe(final Query ref) {

        return Observable.create(new Action1<Emitter<DataSnapshot>>() {
            @Override
            public void call(final Emitter<DataSnapshot> emitter) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        emitter.onNext(dataSnapshot);
                        emitter.onCompleted();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });
            }
        }, Emitter.BackpressureMode.NONE);
    }
}
