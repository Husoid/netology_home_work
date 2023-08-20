package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Освоение новой профессии — это не только открывающиеся возможности и перспективы, но и настоящий вызов самому себе. Приходится выходить из зоны комфорта и перестраивать привычный образ жизни: менять распорядок дня, искать время для занятий, быть готовым к возможным неудачам в начале пути. В блоге рассказали, как избежать стресса на курсах профпереподготовки → http://netolo.gy/fPD",
            published = "23 сентября в 10:12",
            likes = 5,
            likedByMe = false,
            shareCount = 56,
            views = 6
        ),
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
            published = "22 сентября в 10:14",
            likes = 5,
            likedByMe = false,
            shareCount = 56,
            views = 6
        ),
        Post(
            id = 3,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
            published = "22 сентября в 10:12",
            likes = 5,
            likedByMe = false,
            shareCount = 56,
            views = 6
        ),
        Post(
            id = 4,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
            published = "20 сентября в 10:14",
            likes = 5,
            likedByMe = false,
            shareCount = 56,
            views = 6
        )
    )
    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun likedByMe(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe, likes = if (it.likedByMe) {
                    it.likes - 1
                } else {
                    it.likes + 1
                }
            )
        }
        data.value = posts
    }

    override fun shareCount(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                shareCount = it.shareCount + 1
            )
        }
        data.value = posts
    }

    override fun views(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                views = it.views + 1
            )
        }
        data.value = posts
    }
}