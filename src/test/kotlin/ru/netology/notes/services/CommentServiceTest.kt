package ru.netology.notes.services

import org.junit.Test

import org.junit.Assert.*
import ru.netology.notes.Comment
import ru.netology.notes.Note
import ru.netology.notes.exceptions.CommentNotFoundException
import ru.netology.notes.services.CommentService.clearComments

class CommentServiceTest {

    @Test
    fun add_comment() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        assertNotEquals(0, CommentService.add(firstComment))
    }

    @Test
    fun edit_comment_successful() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        val secondComment = Comment(noteId = 1, text = "Second comment")
        val thirdComment = Comment(noteId = 2, text = "Third comment")
        val fourthComment = Comment(id = 2, noteId = 1, text = "Fourth comment")
        CommentService.add(firstComment)
        CommentService.add(secondComment)
        CommentService.add(thirdComment)
        assertTrue(CommentService.edit(fourthComment))
    }

    @Test(expected = CommentNotFoundException::class)
    fun edit_comment_unsuccessful() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        val secondComment = Comment(noteId = 1, text = "Second comment")
        val thirdComment = Comment(noteId = 2, text = "Third comment")
        val fourthComment = Comment(id = 4, noteId = 1, text = "Fourth comment")
        CommentService.add(firstComment)
        CommentService.add(secondComment)
        CommentService.add(thirdComment)
        CommentService.edit(fourthComment)
    }

    @Test
    fun delete_comment_successful() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        CommentService.add(firstComment)
        assertTrue(CommentService.delete(1))
    }

    @Test(expected = CommentNotFoundException::class)
    fun delete_comment_unsuccessful() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        CommentService.add(firstComment)
        CommentService.delete(2)
    }

    @Test(expected = CommentNotFoundException::class)
    fun getById_unsuccessful() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        CommentService.add(firstComment)
        CommentService.getById(2)
    }

    @Test
    fun restore_successful() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        CommentService.add(firstComment)
        CommentService.delete(1)
        assertTrue(CommentService.restore(1))
    }

    @Test(expected = CommentNotFoundException::class)
    fun restore_unsuccessful() {
        clearComments()
        val firstComment = Comment(noteId = 1, text = "First comment")
        CommentService.add(firstComment)
        CommentService.delete(1)
        CommentService.restore(2)
    }

    @Test
    fun deleteCommentsAfterDeletingNote_successful() {
        clearComments()
        val firstNote = Note(title = "Test Note", text = "This is the first note")
        NoteService.add(firstNote)
        val firstComment = Comment(noteId = 1, text = "First comment")
        CommentService.add(firstComment)
        assertTrue(CommentService.deleteCommentsAfterDeletingNote(1))
    }
}
