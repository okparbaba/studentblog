package com.example.secureblog.network

data class PostsResponse(
    val current_page: Int,
    val `data`: List<Data>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: Any,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)

data class Data(
    val body: String,
    val category_id: Int,
    val created_at: String,
    val id: Int,
    val is_published: Int,
    val title: String,
    val updated_at: String,
    val user_id: Int
)