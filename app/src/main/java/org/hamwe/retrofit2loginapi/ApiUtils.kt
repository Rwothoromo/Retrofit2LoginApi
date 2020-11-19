package org.hamwe.retrofit2loginapi

import org.hamwe.retrofit2loginapi.RetrofitClient.getClient

object ApiUtils {
//    private const val baseUrl = "http://192.168.43.147/demo/"
    private const val baseUrl = "http://192.168.43.147:8000/"

    fun getApiService(): ApiService {
        return getClient(baseUrl)!!.create(ApiService::class.java)
    }
}
