package br.com.codinglab.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import br.com.codinglab.myapplication.R;
import br.com.codinglab.myapplication.services.LoginService;
import br.com.codinglab.myapplication.models.Auth;
import br.com.codinglab.myapplication.models.LoginBody;
import br.com.codinglab.myapplication.requests.ApiClient;
import br.com.codinglab.myapplication.requests.LoginRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.editTextEmail)
    EditText email;

    @BindView(R.id.editTextPassword)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void loginUser() {
        ProgressBar progressBar = findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.VISIBLE);

        Auth authBody = new Auth(email.getText().toString(), password.getText().toString());
        LoginBody loginBody = new LoginBody(authBody);
        LoginService loginService = ApiClient.getLoginService();
        LoginRequest loginRequest = new LoginRequest(findViewById(R.id.mainActivity), getApplicationContext(), progressBar);
        loginService.loginUser(loginBody).enqueue(loginRequest);
    }

    @OnClick(R.id.floatingActionButtonNewUser)
    public void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
