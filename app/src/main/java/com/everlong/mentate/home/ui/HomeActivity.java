package com.everlong.mentate.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.everlong.mentate.MainApplication;
import com.everlong.mentate.R;
import com.everlong.mentate.game.ui.GameActivity;
import com.everlong.mentate.home.contract.HomeContract;
import com.everlong.mentate.home.contract.HomePresenter;
import com.everlong.mentate.home.di.DaggerHomeComponent;
import com.everlong.mentate.home.di.HomeModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    @BindView(R.id.best_score)
    TextView bestScoreText;

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        injectComponents();
        presenter.setViews();
    }

    private void injectComponents() {
        DaggerHomeComponent.builder().applicationComponent(((MainApplication) getApplication())
                .getApplicationComponent()).homeModule(new HomeModule(this)).build().inject(this);
    }

    @OnClick(R.id.start_button)
    public void onStartButtonClicked() {
        presenter.onStartClicked();
    }

    @Override
    public void setBestScore(int bestScore) {
        bestScoreText.setVisibility(View.VISIBLE);
        bestScoreText.setText(getString(R.string.best_score, bestScore));
    }

    @Override
    public void hideBestScore() {
        bestScoreText.setVisibility(View.GONE);
    }

    @Override
    public void launchGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
