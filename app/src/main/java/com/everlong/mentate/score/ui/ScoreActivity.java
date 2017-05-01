package com.everlong.mentate.score.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.everlong.mentate.MainApplication;
import com.everlong.mentate.R;
import com.everlong.mentate.score.contract.ScoreContract;
import com.everlong.mentate.score.contract.ScorePresenter;
import com.everlong.mentate.score.di.DaggerScoreComponent;
import com.everlong.mentate.score.di.ScoreModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ScoreActivity extends AppCompatActivity implements ScoreContract.View {

    public static final String SCORE_KEY = "score_key";

    @BindView(R.id.score_text)
    TextView scoreText;

    @Inject
    ScorePresenter presenter;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);
        injectComponents();
        getScoreFromIntent();
        presenter.saveBestScore(score);
        setViews();
    }

    private void getScoreFromIntent() {
        score = getIntent().getExtras().getInt(SCORE_KEY);
    }

    private void injectComponents() {
        DaggerScoreComponent.builder().applicationComponent(((MainApplication) getApplication())
                .getApplicationComponent()).scoreModule(new ScoreModule(this)).build().inject(this);
    }

    private void setViews() {
        scoreText.setText(getString(R.string.your_score, score));
    }

    @OnClick(R.id.back_button)
    public void onBackClicked() {
        presenter.onBackToHomeClicked();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
