package br.com.codinglab.myapplication;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.codinglab.myapplication.interfaces.LoginInterface;
import br.com.codinglab.myapplication.models.Auth;
import br.com.codinglab.myapplication.models.LoginBody;
import br.com.codinglab.myapplication.models.UserToken;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String API_URL = "https://clim-api-staging.herokuapp.com/api/v1/";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final Gson GSON = new GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .create();
    private static final Retrofit API_CLIENT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build();

    @BindView(R.id.mainActivity)
    ConstraintLayout mainLayout;

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
    void loginUser() {
        Auth authBody = new Auth(email.getText().toString(), password.getText().toString());
        LoginBody loginBody = new LoginBody(authBody);
        LoginInterface loginService = API_CLIENT.create(LoginInterface.class);
        Call<UserToken> call = loginService.loginUser(loginBody);
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {
                int statusCode = response.code();

                if (statusCode == 201) {
                    // TODO: Start new activity and store response data
                } else {
                    Snackbar.make(findViewById(R.id.mainActivity), wrongUserOrPassword, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserToken> call, @NonNull Throwable t) {
                Snackbar.make(findViewById(R.id.mainActivity), requestError, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
