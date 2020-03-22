package com.example.secureblog.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object{
        inline fun <reified T>getRetrofit(c:Class<T>):T{
            val httpClient = OkHttpClient.Builder()
            val intercepter = HttpLoggingInterceptor()
            intercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.interceptors().add(intercepter)
            val gson = GsonBuilder().setLenient().create()
            val retro = Retrofit.Builder()
                .baseUrl("http://3.12.197.147/forstudent2/public/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
            return retro.create(c)
        }
    }
}