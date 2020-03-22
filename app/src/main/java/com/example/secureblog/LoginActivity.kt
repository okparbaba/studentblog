package com.example.secureblog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.secureblog.network.LoginResponse
import com.example.secureblog.network.RetrofitBuilder
import com.example.secureblog.network.WebService
import com.example.secureblog.utils.PrefHelper
import com.example.secureblog.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        PrefHelper.getSharePref(this)
        val token = PrefHelper.getString(API_TOKEN_KEY)
        token?.let {
            if (it.length>10){
                startActivity(Intent(this@LoginActivity,BlogActivity::class.java))
            }
        }

        btLogin?.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()

            if (TextUtils.isEmpty(email)){
                etEmail.error = "Email Required"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(pass)){
                etPassword.error = "Password Required"
                etPassword.requestFocus()
                return@setOnClickListener
            }
            login(email,pass)
        }
    }

    private fun login(email:String,pass:String){
        progressBar.visibility = View.VISIBLE
        RetrofitBuilder.getRetrofit(WebService::class.java)
            .login(email,pass)
            .enqueue(object :Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    toast("${t.message.toString()}")
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful){
                        val loginData = response.body()
                        if (loginData!!.api_token!=null){
                            PrefHelper.saveString(API_TOKEN_KEY,loginData.api_token)
                            PrefHelper.saveString(NAME_KEY,loginData.name)
                            PrefHelper.saveString(EMAIL_KEY,loginData.email)
                            startActivity(Intent(this@LoginActivity,BlogActivity::class.java))
                        }


                    }else toast("${response.body()}")
                }

            })
    }
    companion object{
        const val API_TOKEN_KEY = "api_token"
        const val NAME_KEY = "api_token"
        const val EMAIL_KEY = "api_token"
    }
}
