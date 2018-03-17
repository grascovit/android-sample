package br.com.codinglab.myapplication.services;

import java.util.List;

import br.com.codinglab.myapplication.models.Client;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClientService {
    @GET("users/{user_id}/clients")
    Call<List<Client>> fetchClients(@Header("Authorization") String token,
                                    @Path("user_id") int userId,
                                    @Query("page") int page);
}
