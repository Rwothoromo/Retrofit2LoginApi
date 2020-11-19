package org.hamwe.retrofit2loginapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("login/{username}/{password}")
    fun login(@Path("username") username: String, @Path("password") password: String): Call<ResponseObject>
}
