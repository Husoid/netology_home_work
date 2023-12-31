package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

interface OnClickListener {
    fun onLikeListener(post: Post) {}
    fun onShareListener(post: Post) {}
    fun onViewsListener(post: Post) {}
    fun onVideoListener(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
}

class PostsAdapter(
    private val onInteractionListener: OnClickListener,
) :  ListAdapter<Post, PostViewHolder> ( PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
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
    private val onInteractionListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            ivAvatar.setImageResource(R.drawable.ic_netology_48dp)
            tvAuthor.text = post.author
            tvPublished.text = post.published
            tvContent.text = post.content
            btnLike.isChecked = post.likedByMe
            btnLike.text = displayNumbers(post.likes)
            btnShare.text = displayNumbers(post.shareCount)
            btnViews.text = displayNumbers(post.views)
            if (post.video == null) {ivVideo.visibility = View.GONE} else {ivVideo.visibility = View.VISIBLE}

            btnLike.setOnClickListener {
                onInteractionListener.onLikeListener(post)
            }
            btnShare.setOnClickListener {
                onInteractionListener.onShareListener(post)
            }
            btnViews.setOnClickListener {
                onInteractionListener.onViewsListener(post)
            }

            ivVideo.setOnClickListener {
                onInteractionListener.onVideoListener(post)
            }

            ibMenu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
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