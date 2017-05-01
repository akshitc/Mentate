package com.everlong.mentate.game.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.everlong.mentate.MainApplication;
import com.everlong.mentate.R;
import com.everlong.mentate.game.contract.GameContract;
import com.everlong.mentate.game.contract.GamePresenter;
import com.everlong.mentate.game.di.DaggerGameComponent;
import com.everlong.mentate.game.di.GameModule;
import com.everlong.mentate.game.models.Question;
import com.everlong.mentate.game.utils.GameViewFactory;
import com.everlong.mentate.game.views.BaseGameView;
import com.everlong.mentate.home.ui.HomeActivity;
import com.everlong.mentate.score.ui.ScoreActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.everlong.mentate.score.ui.ScoreActivity.SCORE_KEY;

public class GameActivity extends AppCompatActivity implements GameContract.View, BaseGameView.GameViewListener {

    @BindView(R.id.game_layout)
    RelativeLayout gameLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.category_data)
    LinearLayout categoryDataLayout;
    @BindView(R.id.category_name)
    TextView categoryName;
    @BindView(R.id.category_description)
    TextView categoryDescription;
    private BaseGameView baseGameView;

    @Inject
    GamePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        injectComponents();
        fetchData();
    }

    private void injectComponents() {
        DaggerGameComponent.builder().applicationComponent(((MainApplication) getApplication())
                .getApplicationComponent()).gameModule(new GameModule(this)).build().inject(this);
    }

    private void fetchData() {
        presenter.onCreate();
        presenter.fetchData();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCategoryDataVisibility(boolean isVisible) {
        if (isVisible) {
            categoryDataLayout.setVisibility(View.VISIBLE);
        } else {
            categoryDataLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCategoryName(String name) {
        categoryName.setText(name);
    }

    @Override
    public void setCategoryDescription(String description) {
        categoryDescription.setText(description);
    }

    @OnClick(R.id.proceed_button)
    public void onProceedClick() {
        presenter.onProceedClicked();
    }

    @Override
    public void initQuestionView(int categoryID) {
        baseGameView = GameViewFactory.getGameView(this, categoryID);
        gameLayout.addView(baseGameView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        presenter.setCurrentQuestion();
    }

    @Override
    public void setQuestionView(Question question, int questionIndex) {
        baseGameView.setView(question, questionIndex);
        baseGameView.setGameViewListener(this);
    }

    @Override
    public void onNextClicked(String selectedOption) {
        presenter.onNextClicked(selectedOption);
    }

    @Override
    public void showToast() {
        Toast.makeText(this, R.string.select_an_option, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeGameView() {
        if (baseGameView != null) {
            gameLayout.removeView(baseGameView);
        }
    }

    @Override
    public void launchScoreActivity(int score) {
        finish();
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(SCORE_KEY, score);
        startActivity(intent);
    }
}
