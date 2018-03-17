package br.com.codinglab.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    public int id;

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("email")
    public String email;

    @SerializedName("password_digest")
    public String passwordDigest;
}
