package br.com.codinglab.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("auth")
    public Auth auth;

    public LoginBody(Auth auth) {
        this.auth = auth;
    }
}
