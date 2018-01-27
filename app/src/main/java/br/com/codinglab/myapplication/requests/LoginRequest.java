package br.com.codinglab.myapplication.requests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;

import br.com.codinglab.myapplication.R;
import br.com.codinglab.myapplication.models.UserToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRequest implements Callback<UserToken> {
    private View view;
    private ProgressBar progressBar;
    private Context context;

    public LoginRequest(View view, Context context, ProgressBar progressBar) {
        this.view = view;
        this.progressBar = progressBar;
        this.context = context;
    }

    @Override
    public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {
        progressBar.setVisibility(View.GONE);
        int statusCode = response.code();

        if (statusCode == 201) {
            // TODO: Start new activity and store response data
        } else {
            Snackbar.make(view, context.getString(R.string.wrong_user_or_password), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(@NonNull Call<UserToken> call, @NonNull Throwable t) {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(view, context.getString(R.string.request_error), Snackbar.LENGTH_LONG).show();
    }
}
