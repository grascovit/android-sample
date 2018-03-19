package br.com.codinglab.myapplication.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.codinglab.myapplication.models.Client;
import br.com.codinglab.myapplication.services.ClientService;
import br.com.codinglab.myapplication.services.LoginService;
import br.com.codinglab.myapplication.services.UserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String API_URL = "https://clim-api-staging.herokuapp.com/api/v1/";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final Gson GSON = new GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .create();
    private static final Retrofit API_CLIENT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build();

    public static LoginService getLoginService() {
        return API_CLIENT.create(LoginService.class);
    }

    public static ClientService getClientService() {
        return API_CLIENT.create(ClientService.class);
    }

    public static UserService getUserService() {
        return API_CLIENT.create(UserService.class);
    }
}
