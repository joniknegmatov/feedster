package io.jonibek.feedster.ui.feed.feeditem

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.jonibek.feedster.R
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.databinding.ListItemFeedBinding
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase

class FeedItemViewHolder(private val binding: ListItemFeedBinding, private val feedItemUseCase: FeedItemUseCase) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, clickListener: FeedsAdapter.OnItemClickListener) {
        binding.vm = FeedItemViewModel(post, feedItemUseCase)
        binding.root.setOnClickListener {
            clickListener.onItemClick(post)
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup, feedItemUseCase: FeedItemUseCase): FeedItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ListItemFeedBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.list_item_feed, parent, false)
            return FeedItemViewHolder(binding, feedItemUseCase)
        }
    }

}