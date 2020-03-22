package com.example.secureblog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.secureblog.LoginActivity.Companion.API_TOKEN_KEY
import com.example.secureblog.network.PostsResponse
import com.example.secureblog.network.RetrofitBuilder
import com.example.secureblog.network.WebService
import com.example.secureblog.utils.PrefHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        PrefHelper.getSharePref(this)
        getPosts()
    }
    private fun getPosts(){
        val token = PrefHelper.getString(API_TOKEN_KEY)
        RetrofitBuilder.getRetrofit(WebService::class.java)
            .getPosts("1",token.toString())
            .enqueue(object :Callback<PostsResponse>{
                override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                    Log.e("data",t.message)
                }

                override fun onResponse(
                    call: Call<PostsResponse>,
                    response: Response<PostsResponse>
                ) {
                    if (response.isSuccessful){
                        val res = response.body()!!
                        for (i in res.data) Log.e("data",i.title)
                    }else{
                        Log.e("data",response.body().toString())
                    }
                }

            })
    }
}
