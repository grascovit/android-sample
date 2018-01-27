package br.com.codinglab.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import br.com.codinglab.myapplication.R;
import br.com.codinglab.myapplication.services.Login;
import br.com.codinglab.myapplication.models.Auth;
import br.com.codinglab.myapplication.models.LoginBody;
import br.com.codinglab.myapplication.requests.ApiClient;
import br.com.codinglab.myapplication.requests.LoginRequest;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editTextEmail)
    EditText email;

    @BindView(R.id.editTextPassword)
    EditText password;

    @BindString(R.string.wrong_user_or_password)
    String wrongUserOrPassword;

    @BindString(R.string.request_error)
    String requestError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void loginUser() {
        ProgressBar progressBar = findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.VISIBLE);
        Auth authBody = new Auth(email.getText().toString(), password.getText().toString());
        LoginBody loginBody = new LoginBody(authBody);
        Login loginService = ApiClient.getLoginRequest();
        LoginRequest loginRequest = new LoginRequest(findViewById(R.id.mainActivity), getApplicationContext(), progressBar);
        loginService.loginUser(loginBody).enqueue(loginRequest);
    }
}
