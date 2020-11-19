package org.hamwe.retrofit2loginapi

import android.content.Context
import org.hamwe.retrofit2loginapi.RetrofitClient.getClient

object ApiUtils {
    //    private const val baseUrl = "http://<your-ip>:<port>/"
    private const val baseUrl = "https://weconnect-api-v2-rwothoromo.herokuapp.com/"

    fun getApiService(context: Context): ApiServiceInterface {
        return getClient(baseUrl, context)!!.create(ApiServiceInterface::class.java)
    }
}
