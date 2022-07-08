package ru.netology.notes.exceptions

class CommentNotFoundException(commentId: Int): RuntimeException("Comment with id $commentId not found")
