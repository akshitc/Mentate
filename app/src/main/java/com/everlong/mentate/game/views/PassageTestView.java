package com.everlong.mentate.game.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.everlong.mentate.R;
import com.everlong.mentate.game.models.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akshit on 4/11/17.
 */

public class PassageTestView extends BaseGameView {

    @BindView(R.id.passage_text)
    TextView passageText;

    public PassageTestView(Context context) {
        super(context);
    }

    public PassageTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PassageTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        inflate(getContext(), R.layout.passage_test_layout, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setView(Question question, int questionIndex) {
        super.setView(question, questionIndex);
        passageText.setText(question.getPassageText());
    }
}
