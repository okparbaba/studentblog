package com.example.secureblog.network

data class LoginResponse(
    val api_token: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val is_admin: Int,
    val name: String,
    val reset_key: Any?,
    val updated_at: String
)