package com.example.secureblog.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class PrefHelper private constructor(context: Context){
    companion object{
        var sharedPreferences:PrefHelper? = null
        var sh:SharedPreferences? = null
        fun getSharePref(context:Context):SharedPreferences?{

            if (sharedPreferences==null){
                sharedPreferences = PrefHelper(context)
                sh = context.getSharedPreferences("blog",MODE_PRIVATE)
            }
            return sh
        }
        fun saveString(key:String,value:String){
            sh?.edit()?.putString(key,value)?.apply()
        }
        fun getString(key:String):String?{
            return sh?.getString(key,"")
        }
    }
}