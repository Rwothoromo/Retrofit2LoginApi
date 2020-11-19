package org.hamwe.retrofit2loginapi

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceInterface {
//    @GET("login/{username}/{password}")
//    fun login(@Path("username") username: String, @Path("password") password: String): Call<JsonObject>

    //    @POST("api/v1/users/login/")
//    @POST("api/v1/token/")
    @POST("api/v2/auth/login")
    fun login(@Body logInRequest: LogInRequest): Call<JsonObject>
}
