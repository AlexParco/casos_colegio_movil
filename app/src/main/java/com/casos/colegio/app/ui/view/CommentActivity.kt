package com.casos.colegio.app.ui.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.casos.colegio.app.data.models.Comment
import com.casos.colegio.app.data.models.CommentDTO
import com.casos.colegio.app.data.models.Mensaje
import com.casos.colegio.app.databinding.ActivityCommentBinding
import com.casos.colegio.app.ui.adapter.CommentAdapter
import com.casos.colegio.app.ui.viewmodel.CommentViewModel

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate) {

    private var commnetList : List<Comment> = emptyList()
    private val adapter by lazy { CommentAdapter(commnetList) }
    private val commentViewModel by lazy { CommentViewModel(mensaje.id.toString(), this) }

    private val mensaje by lazy {
        intent.getSerializableExtra("MENSAJE") as Mensaje
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.commnetList.layoutManager = LinearLayoutManager(this)
        binding.commnetList.setHasFixedSize(true)
        binding.commnetList.adapter = adapter

        binding.btnSend.setOnClickListener {
            val comment = CommentDTO(
                binding.mensajeInput.text.toString(),
                commentViewModel.userLogin?.id.toString(),
                mensaje.id
            )
            commentViewModel.saveComment(comment)
            binding.mensajeInput.text.clear()
        }

        commentViewModel.commentList.observe(this) {
            adapter.comments = it
            adapter.notifyDataSetChanged()
        }
    }

}