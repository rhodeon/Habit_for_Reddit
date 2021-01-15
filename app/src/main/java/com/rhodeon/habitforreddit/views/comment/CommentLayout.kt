package com.rhodeon.habitforreddit.views.comment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.rhodeon.habitforreddit.databinding.ViewCommentBinding
import io.noties.markwon.Markwon

/**
 * Created by Ruona Onobrakpeya on 1/9/21.
 */

class CommentLayout(context: Context, val attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {
    var binding: ViewCommentBinding =
        ViewCommentBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    var depth: Int = 0  // Current comment depth
    var replies: Int = 0  // Number of inflated replies

    /**
     * Displays comment with markdown.
     */
    fun setComment(comment: String) {
        val markwon = Markwon.create(context)   // TODO: Use hilt for markwon
        markwon.setMarkdown(binding.commentBody, comment)
    }
}