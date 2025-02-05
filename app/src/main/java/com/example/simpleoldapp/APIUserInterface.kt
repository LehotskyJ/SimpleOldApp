package com.example.simpleoldapp


import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.Response


interface APIUserInterface {
    @GET("users")
    suspend fun getUsers(): Result<List<Users>?>
}

interface ApiInterface {
/*    @Headers("Content-Type:application/json")
    @POST("auth_tokens")
    fun signin(@Body info: SignInBody): retrofit2.Call<ResponseBody>*/

    @Headers("Content-Type:application/json")
    @POST("api/register")
    suspend fun registerUser(
        @Body request: RegisterCred
    ): Response<ResponseReg>
}
