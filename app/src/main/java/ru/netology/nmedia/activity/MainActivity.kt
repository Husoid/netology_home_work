package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                ivAvatar.setImageResource(R.drawable.ic_netology_48dp)
                tvAuthor.text = post.author
                tvPublished.text = post.published
                tvContent.text = post.content
                ivLike.setImageResource(if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like)
                tvLike.text = displayNumbers(post.likes)
                tvShare.text = displayNumbers(post.shareCount)
                tvViews.text = displayNumbers(post.views)
            }
        }
        binding.ivLike.setOnClickListener {
            viewModel.likedByMe()
        }
        binding.ivShare.setOnClickListener {
            viewModel.shareCount()
        }

        binding.ivViews.setOnClickListener {
            viewModel.views()
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
