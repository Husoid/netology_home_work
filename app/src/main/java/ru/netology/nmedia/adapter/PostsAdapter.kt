package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnClickListener = (post: Post) -> Unit

class PostsAdapter(
    private val onLikeListener: OnClickListener,
    private val onShareListener: OnClickListener,
    private val onViewsListener: OnClickListener,
) :  ListAdapter<Post, PostViewHolder> ( PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener, onViewsListener)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnClickListener,
    private val onShareListener: OnClickListener,
    private val onViewsListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            ivAvatar.setImageResource(R.drawable.ic_netology_48dp)
            tvAuthor.text = post.author
            tvPublished.text = post.published
            tvContent.text = post.content
            ivLike.setImageResource(if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like)
            tvLike.text = displayNumbers(post.likes)
            tvShare.text = displayNumbers(post.shareCount)
            tvViews.text = displayNumbers(post.views)

            ivLike.setOnClickListener {
                onLikeListener(post)
            }
            ivShare.setOnClickListener {
                onShareListener(post)
            }
            ivViews.setOnClickListener {
                onViewsListener(post)
            }
        }
    }

    fun displayNumbers(number: Int): String {
        var result = ""
        if (number < 1000) {
            result = number.toString()
        }
        if ((number >= 1000) && (number < 10000)) {
            result = (number / 100 / 10.0).toString() + "K"
        }
        if ((number >= 10000) && (number <= 1000000)) {
            result = (number / 1000).toString() + "K"
        }
        if (number >= 1000000) {
            result = (number / 100000 / 10.0).toString() + "M"
        }
        return result
    }
}