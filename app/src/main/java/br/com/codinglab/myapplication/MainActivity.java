package br.com.codinglab.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import br.com.codinglab.myapplication.interfaces.Login;
import br.com.codinglab.myapplication.models.Auth;
import br.com.codinglab.myapplication.models.LoginBody;
import br.com.codinglab.myapplication.models.UserToken;
import br.com.codinglab.myapplication.requests.ApiClient;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editTextEmail)
    EditText email;

    @BindView(R.id.editTextPassword)
    EditText password;

    @BindString(R.string.wrong_user_or_password)
    String wrongUserOrPassword;

    @BindString(R.string.request_error)
    String requestError;

    @BindString(R.string.login_dialog_title)
    String loginDialogTitle;

    @BindString(R.string.loading)
    String loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void loginUser() {
        final ProgressBar progressBar = findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.VISIBLE);

        Auth authBody = new Auth(email.getText().toString(), password.getText().toString());
        LoginBody loginBody = new LoginBody(authBody);
        Login loginService = ApiClient.getLoginRequest();
        loginService.loginUser(loginBody).enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {
                progressBar.setVisibility(View.GONE);
                int statusCode = response.code();

                if (statusCode == 201) {
                    // TODO: Start new activity and store response data
                } else {
                    Snackbar.make(findViewById(R.id.mainActivity), wrongUserOrPassword, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserToken> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Snackbar.make(findViewById(R.id.mainActivity), requestError, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
