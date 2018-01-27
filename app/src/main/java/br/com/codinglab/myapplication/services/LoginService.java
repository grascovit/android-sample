package br.com.codinglab.myapplication.services;

import br.com.codinglab.myapplication.models.LoginBody;
import br.com.codinglab.myapplication.models.UserToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("user_token")
    Call<UserToken> loginUser(@Body LoginBody loginBody);
}
