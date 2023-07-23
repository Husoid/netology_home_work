package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )
        with(binding) {
            ivAvatar.setImageResource(R.drawable.ic_netology_48dp)
            tvAuthor.text = post.author
            tvPublished.text = post.published
            tvContent.text = post.content
            if (post.likedByMe) {
                ivLike?.setImageResource(R.drawable.ic_liked)
            }
            tvLike?.text = displayNumbers(post.likes)
            tvShare?.text = displayNumbers(post.shareCount)

            ivLike?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                ivLike.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
                )
                if (post.likedByMe) post.likes++ else post.likes--
                tvLike?.text = displayNumbers(post.likes)
            }
            ivShare?.setOnClickListener {
                tvShare?.text = displayNumbers(post.shareCount++)
            }
        }
    }

    fun displayNumbers (number: Int): String {
         var result = ""
        if (number < 1000) {result = number.toString()}
        if ((number >= 1000) && (number < 10000)) {
            result = (number / 100 / 10.0).toString() + "K"
        }
        if ((number >= 10000) && (number <= 1000000)) {
            result = (number / 1000).toString() + "K"
        }
        if (number >= 1000000) {
            result = (number / 100000 / 10.0).toString() + "M"
        }
        return  result
    }
}