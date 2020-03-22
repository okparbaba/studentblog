package com.example.secureblog.network

import retrofit2.Call
import retrofit2.http.*

interface WebService {
    @FormUrlEncoded
    @POST("auth/token")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<LoginResponse>

    @GET("posts")
    fun getPosts(
        @Query("page") page:String,
        @Query("api_token") token:String
    ):Call<PostsResponse>

}