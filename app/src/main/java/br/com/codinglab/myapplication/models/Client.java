package br.com.codinglab.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class Client {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("phone")
    public String phone;
}
