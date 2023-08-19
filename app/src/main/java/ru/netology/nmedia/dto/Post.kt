package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int,
    var likedByMe: Boolean,
    var shareCount: Int,
    var views: Int
)

