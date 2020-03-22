package com.example.secureblog.utils

import android.app.Activity
import android.widget.Toast

fun Activity.toast(msg:String){
    Toast.makeText(applicationContext,msg,Toast.LENGTH_LONG).show()
}