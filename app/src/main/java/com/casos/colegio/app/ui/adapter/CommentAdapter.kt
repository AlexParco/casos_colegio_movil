package com.casos.colegio.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casos.colegio.app.data.models.Comment
import com.casos.colegio.app.databinding.ItemCommentBinding

class CommentAdapter(
    var comments: List<Comment>
):
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCommentBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(comment: Comment) {
            binding.cmtBody.text = comment.body
            binding.cmtUser.text = comment.user?.nombre
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment: Comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = comments.size
}