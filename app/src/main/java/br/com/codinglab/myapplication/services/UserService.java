package br.com.codinglab.myapplication.services;

import br.com.codinglab.myapplication.models.RegisterBody;
import br.com.codinglab.myapplication.models.UserToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("users")
    Call<UserToken> registerUser(@Body RegisterBody registerBody);
}
