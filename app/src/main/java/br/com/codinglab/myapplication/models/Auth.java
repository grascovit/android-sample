package br.com.codinglab.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
