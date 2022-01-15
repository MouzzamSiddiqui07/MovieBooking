package com.example.moviebooking.Network

import com.example.moviebooking.Utils.Credentials
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

     val requestInterceptor  = Interceptor {  chain ->

         val url : HttpUrl  =  chain.request()
             .url()
             .newBuilder()
             .addQueryParameter("api_key",com.example.moviebooking.Utils.Credentials.API_KEY)
             .build()

         val request : Request = chain.request()
             .newBuilder()
             .url(url)
             .build()


         return@Interceptor chain.proceed(request)

     }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(60,TimeUnit.SECONDS)
        .build()


    fun getInstance() : Retrofit
    {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




}
