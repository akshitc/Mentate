package com.everlong.mentate.game.utils;

import android.content.Context;

import com.everlong.mentate.game.views.BaseGameView;
import com.everlong.mentate.game.views.PassageTestView;
import com.everlong.mentate.game.views.PictureMemoryTestView;

/**
 * Created by akshit on 3/25/17.
 */

public class GameViewFactory {

    private static final int PICTURE_MEMORY_TEST = 1;
    private static final int PASSAGE_TEST = 2;

    public static BaseGameView getGameView(Context context, int categoryID) {
        switch (categoryID) {
            case PICTURE_MEMORY_TEST:
                return new PictureMemoryTestView(context);
            case PASSAGE_TEST:
                return new PassageTestView(context);
        }
        return null;
    }
}
