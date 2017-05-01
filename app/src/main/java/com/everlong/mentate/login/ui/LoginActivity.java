package com.everlong.mentate.login.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.everlong.mentate.MainApplication;
import com.everlong.mentate.R;
import com.everlong.mentate.home.ui.HomeActivity;
import com.everlong.mentate.login.contract.LoginContract;
import com.everlong.mentate.login.contract.LoginPresenter;
import com.everlong.mentate.login.di.DaggerLoginComponent;
import com.everlong.mentate.login.di.LoginModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View,
        RadioGroup.OnCheckedChangeListener, FirebaseAuth.AuthStateListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name_et)
    EditText nameEditText;
    @BindView(R.id.age_et)
    EditText ageEditText;
    @BindView(R.id.gender_rg)
    RadioGroup genderRG;

    @Inject
    LoginPresenter presenter;
    @Inject
    FirebaseAuth mAuth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies();
        presenter.checkForLogin(mAuth.getCurrentUser() != null);
    }

    private void injectDependencies() {
        DaggerLoginComponent.builder().applicationComponent(((MainApplication) getApplication())
                .getApplicationComponent()).loginModule(new LoginModule(this)).build().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);
    }

    @Override
    public void setLoginView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setToolBar();
        setListeners();
    }

    private void setToolBar() {
        toolbar.setTitle(R.string.basic_information);
    }

    private void setListeners() {
        genderRG.setOnCheckedChangeListener(this);
    }

    @Override
    public void launchHomeActivity() {
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.male:
                presenter.setGender(getString(R.string.male));
                break;
            case R.id.female:
                presenter.setGender(getString(R.string.female));
                break;
        }
    }

    @OnClick(R.id.save_button)
    public void onSaveClicked() {
        presenter.validateUserDetails();
    }

    @Override
    public String getName() {
        return nameEditText.getText().toString().trim();
    }

    @Override
    public String getAge() {
        return ageEditText.getText().toString().trim();
    }

    @Override
    public void showMissingInfoToast() {
        Toast.makeText(LoginActivity.this, "Please provide missing information.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        dialog = ProgressDialog.show(this, "", "Saving User Details...");
    }

    @Override
    public void signInUser() {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                hideProgressDialog();
                if (task.isSuccessful()) {
                    presenter.saveUserDetails();
                } else {
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void hideProgressDialog() {
        dialog.dismiss();
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Log.d("Akshit", "onAuthStateChanged:signed_in:" + user.getUid());
            launchHomeActivity();
        } else {
            // User is signed out
            Log.d("Akshit", "onAuthStateChanged:signed_out");
            setLoginView();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }
}
