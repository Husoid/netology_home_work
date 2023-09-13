package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun likedByMe(id: Long)
    fun shareCount(id: Long)
    fun views(id: Long)
    fun save(post: Post)
    fun removeById(id: Long)
}
