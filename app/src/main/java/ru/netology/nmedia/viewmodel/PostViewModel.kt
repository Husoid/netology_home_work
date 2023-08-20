package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun likedByMe(id: Long) = repository.likedByMe(id)

    fun shareCount(id: Long) = repository.shareCount(id)

    fun views(id: Long) = repository.views(id)
}