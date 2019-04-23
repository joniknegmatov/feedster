package io.jonibek.feedster.ui.feed

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase


class FeedsAdapter(private val onItemClickListener: OnItemClickListener,private val feedItemUseCase: FeedItemUseCase) : RecyclerView.Adapter<FeedItemViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()

    fun renderData(postList: List<Post>) {
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        return FeedItemViewHolder.newInstance(parent,feedItemUseCase)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        holder.bind(postList[position], onItemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(post: Post)
    }

}
