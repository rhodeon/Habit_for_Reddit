package com.rhodeon.habitforreddit.views.comment

import android.annotation.SuppressLint
import android.content.Context
import com.rhodeon.habitforreddit.models.comment.Comment
import com.rhodeon.habitforreddit.utils.commentListingFromJson
import com.rhodeon.habitforreddit.utils.mapToJson

/**
 * Created by Ruona Onobrakpeya on 1/9/21.
 */


/**
 * Adapts a comment response to a comment view
 */
class CommentAdapter(val comment: Comment, val commentLayout: CommentLayout, val context: Context) {
    @SuppressLint("ResourceType")
    fun bind() {
        commentLayout.depth = comment.data.depth
        commentLayout.setComment(comment.data.rawBody)

        // Handle replies
        val repliesResponse = comment.data.replies
        if (repliesResponse == "") return   // No replies

        // A JSON middleman is needed to convert the repliesResponse to a CommentListing
        val repliesJson = mapToJson(repliesResponse as Map<*, *>)
        val repliesListing = commentListingFromJson(repliesJson)
        val replies: List<Comment> =
            repliesListing.data.children   // Final list of replies as Comments

        for ((rank, reply) in replies.withIndex()) {
            val replyLayout = CommentLayout(context, commentLayout.attributeSet)
            replyLayout.id = rank //rank
            commentLayout.binding.innerLayout.addView(replyLayout)
            commentLayout.replies += 1

            // TODO: Use enums for kinds
            if (reply.kind == "t1") {   // Reply is a comment, not "more".
                commentLayout.binding.innerLayout.findViewById<CommentLayout>(replyLayout.id)
                    .setComment(reply.data.rawBody)
            }
        }
    }
}
