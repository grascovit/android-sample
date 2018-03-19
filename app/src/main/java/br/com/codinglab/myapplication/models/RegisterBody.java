package br.com.codinglab.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("user")
    public User user;

    public RegisterBody(User user) {
        this.user = user;
    }
}
