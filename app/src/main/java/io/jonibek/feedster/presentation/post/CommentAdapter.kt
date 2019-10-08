package io.jonibek.feedster.presentation.post

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.jonibek.feedster.R
import io.jonibek.feedster.data.entities.Comment


class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val postList: MutableList<Comment> = mutableListOf()

    fun renderData(postList: List<Comment>) {
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.apply {
            val comment = postList[position]
            tvAuthorName.text = comment.name
            tvAuthorEmail.text = comment.email
            tvBody.text = comment.body
        }
    }

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAuthorName: TextView = view.findViewById(R.id.comment_author_name)
        val tvAuthorEmail: TextView = view.findViewById(R.id.comment_author_email)
        val tvBody: TextView = view.findViewById(R.id.comment_body_text)
    }
}