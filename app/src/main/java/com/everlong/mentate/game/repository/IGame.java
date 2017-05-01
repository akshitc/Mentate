package com.everlong.mentate.game.repository;


import com.everlong.mentate.game.models.ScoreModel;

import rx.Observable;

/**
 * Created by akshit on 3/25/17.
 */

public interface IGame {
    Observable getGameData();

    Observable getScoreData();

    void saveScore(ScoreModel scoreModel);
}
