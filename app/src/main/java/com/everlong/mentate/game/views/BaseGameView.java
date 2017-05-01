package com.everlong.mentate.game.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.everlong.mentate.R;
import com.everlong.mentate.game.models.Question;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by akshit on 3/25/17.
 */
public abstract class BaseGameView extends LinearLayout {

    @BindView(R.id.question_header)
    TextView questionHeaderText;
    @BindView(R.id.question_text)
    TextView questionText;
    @BindView(R.id.question_rg)
    RadioGroup questionRG;

    public GameViewListener listener;

    public BaseGameView(Context context) {
        super(context);
        init();
    }

    public BaseGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setGameViewListener(GameViewListener listener) {
        this.listener = listener;
    }

    public abstract void init();

    public void setView(Question question, int questionIndex) {
        questionHeaderText.setText(getContext().getString(R.string.question_number, questionIndex + 1));
        questionText.setText(question.getQuestion());
        questionRG.removeAllViews();
        questionRG.clearCheck();
        List<String> options = question.getOptions();

        for (int i = 0; i < options.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(i);
            radioButton.setText(options.get(i));
            radioButton.setPadding(10, 0, 0, 10);
            questionRG.addView(radioButton);
        }
    }

    @OnClick(R.id.next_button)
    public void onNextClicked() {
        int checkedID = questionRG.getCheckedRadioButtonId();
        if (checkedID != -1) {
            RadioButton radioButton = (RadioButton) findViewById(checkedID);
            listener.onNextClicked(radioButton.getText().toString());
        } else {
            listener.onNextClicked(null);
        }
    }

    public interface GameViewListener {
        void onNextClicked(String selectedOption);
    }
}
