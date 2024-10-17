package com.example.simpleoldapp

import com.google.gson.annotations.SerializedName

class Data {



}

data class RegisterCred (
    val email: String,
    val password: String
)

data class ResponseReg (
    val id: Int,
    val token: String
)

data class Users {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("avatar")
    var avatar: String? = null
}