package ru.netology.notes.services

import ru.netology.notes.Comment
import ru.netology.notes.exceptions.CommentNotFoundException

object CommentService : CRUD<Comment> {
    private val comments = mutableListOf<Comment>()
    private val deletedComments = mutableListOf<Comment>()
    private var commentIdCount = 1

    override fun add(entity: Comment): Int {
        comments.add(
            entity.copy(
                id = commentIdCount,
                date = System.currentTimeMillis() / 1000
            )
        )
        commentIdCount++
        return comments.last().id
    }

    override fun edit(entity: Comment): Boolean {
        for ((index, value) in comments.withIndex()) {
            if (value.id == entity.id) {
                comments[index] = entity.copy(
                    id = value.id,
                    noteId = entity.noteId,
                    fromId = entity.fromId,
                    date = value.date
                )
                return true
            }
        }
        throw CommentNotFoundException(entity.id)
    }

    override fun delete(id: Int): Boolean {
        for ((index, value) in comments.withIndex()) {
            if (value.id == id) {
                deletedComments.add(value)
                comments.removeAt(index)
                return true
            }
        }
        throw CommentNotFoundException(id)
    }

    override fun get(): List<Comment> {
        return comments
    }

    override fun getById(id: Int): Comment {
        for (comment in comments) {
            if (comment.id == id) return comment
        }
        throw CommentNotFoundException(id)
    }

    fun restore(id: Int): Boolean {
        for ((index, value) in deletedComments.withIndex()) {
            if (value.id == id) {
                comments.add(value)
                deletedComments.removeAt(index)
                return true
            }
        }
        throw CommentNotFoundException(id)
    }

    fun deleteCommentsAfterDeletingNote(noteId: Int): Boolean {
        var result = false
        val iterator = comments.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.noteId == noteId){
                deletedComments.add(item)
                iterator.remove()
                result = true
            }
        }
        return result
    }

    fun showComments() {
        for (comment in comments) {
            println(comment)
        }
    }

    fun clearComments() {
        comments.clear()
        deletedComments.clear()
        commentIdCount = 1
    }
}
