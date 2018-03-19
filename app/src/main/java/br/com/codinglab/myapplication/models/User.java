package br.com.codinglab.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    public Integer id;

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("cpf")
    public String cpf;

    @SerializedName("cnpj")
    public String cnpj;

    public User(String firstName, String lastName, String email, String password, String financialDocument, String financialDocumentType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        if (financialDocumentType.equals("cpf")) {
            this.cpf = financialDocument;
        } else if (financialDocumentType.equals("cnpj")) {
            this.cnpj = financialDocument;
        }
    }
}
