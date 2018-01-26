package br.com.codinglab.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class UserToken {
    @SerializedName("user")
    public User user;

    @SerializedName("token")
    public String token;
}
