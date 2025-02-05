package com.example.simpleoldapp

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val api: ApiInterface) {

    suspend fun registerUser(registrationData: RegisterCred): Response<ResponseReg> {

        return api.registerUser(registrationData)
    }


}