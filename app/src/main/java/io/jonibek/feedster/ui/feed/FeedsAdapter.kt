package io.jonibek.feedster.ui.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.jonibek.feedster.R
import io.jonibek.feedster.data.entities.Post


class FeedsAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<FeedsAdapter.PostViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()

    fun renderData(postList: List<Post>) {
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_feed, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            val post = postList[position]
            tvPostTitle.text = post.title
            tvPostBody.text = post.body
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(post)
            }
        }
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPostTitle: TextView = view.findViewById(R.id.post_title_text)
        val tvPostBody: TextView = view.findViewById(R.id.post_body_text)
    }

    interface OnItemClickListener {
        fun onItemClick(post: Post)
    }
}