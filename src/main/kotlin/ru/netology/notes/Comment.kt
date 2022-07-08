package ru.netology.notes

data class Comment(
    val id: Int = 0,
    val noteId: Int,
    val fromId: Int = 0,
    val date: Long = 0,
    val text: String,
    val donut: Donut? = null,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val thread: Thread? = null
)

data class Donut(
    val isDon: Boolean,
    val placeholder: String
)

data class Thread(
    val count: Int,
    val canPost: Boolean,
    val showReplyButton: Boolean,
    val groupsCanPost: Boolean
){
    val items = mutableListOf<Comment>()

    internal fun addComment(comment: Comment): Comment {
        items += comment
        return items.last()
    }
}
