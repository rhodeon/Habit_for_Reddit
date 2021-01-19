package com.rhodeon.habitforreddit.views.comment

import android.content.Context
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.models.comment.Comment
import com.rhodeon.habitforreddit.models.comment.CommentData
import com.rhodeon.habitforreddit.utils.commentListingFromJson
import com.rhodeon.habitforreddit.utils.formatDate
import com.rhodeon.habitforreddit.utils.mapToJson

/**
 * Created by Ruona Onobrakpeya on 1/9/21.
 */


/**
 * Adapts a comment response to a comment view
 */
class CommentAdapter(val comment: Comment, private val commentLayout: CommentLayout, private val context: Context) {
    init {
        bind(comment, commentLayout, context)
    }

    private fun bind(comment: Comment, commentLayout: CommentLayout, context: Context) {
        val commentData = comment.data
        commentLayout.depth = commentData.depth

        setIndentColour(commentData, commentLayout)

        // TODO: Use enums for kinds
        if (comment.kind == "t1") { // Reply is a comment, not "more".
            setData(commentData, commentLayout)

            // Handle replies response
            val repliesResponse = comment.data.replies
            if (repliesResponse == "") return   // No replies

            // A JSON middleman is needed to convert the repliesResponse to a CommentListing
            val repliesJson = mapToJson(repliesResponse as Map<*, *>)
            val repliesListing = commentListingFromJson(repliesJson)
            val replies: List<Comment> =
                repliesListing.data.children   // Final list of replies as Comments

            // Bind replies to nested comment layouts
            for ((rank, reply) in replies.withIndex()) {
                commentLayout.replies += 1

                val replyLayout = CommentLayout(context, commentLayout.attributeSet)
                replyLayout.id = rank //rank
                commentLayout.binding.innerLayout.addView(replyLayout)

                bind(reply, replyLayout, context)
            }
        }
    }

    private fun setIndentColour(commentData: CommentData, commentLayout: CommentLayout) {
        if (commentData.depth == 0) {   // Remove indent line for top level comments
            commentLayout.binding.indentLine.collapse()
        }
        else {
            val indentColour = CommentIndentColour.values()[(commentData.depth - 1) % 6].color
            commentLayout.binding.indentLine.setBackgroundColor(indentColour)
        }
    }

    private fun setData(commentData: CommentData, commentLayout: CommentLayout) {
        commentLayout.setComment(commentData.rawBody)

        commentLayout.binding.apply {
            commentScore.text = commentData.score.toString()
            author.text = commentData.author
            timestamp.text = formatDate(commentData.creationTimeUtc)
        }
    }
}
