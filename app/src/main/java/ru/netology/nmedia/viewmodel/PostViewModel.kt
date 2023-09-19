package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryFileImpl
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    likes = 0,
    shareCount = 0,
    views = 0,
    video = null
)
class PostViewModel (application: Application) : AndroidViewModel(application)  {

    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val data = repository.get()
    val edited = MutableLiveData(empty)
    fun likedByMe(id: Long) = repository.likedByMe(id)

    fun shareCount(id: Long) = repository.shareCount(id)

    fun views(id: Long) = repository.views(id)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun dontSave() {
        edited.value = empty
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun removeById(id: Long) = repository.removeById(id)
}