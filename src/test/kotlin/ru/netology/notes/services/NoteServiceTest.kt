package ru.netology.notes.services

import org.junit.Assert.*
import org.junit.Test
import ru.netology.notes.Note
import ru.netology.notes.exceptions.NoteNotFoundException
import ru.netology.notes.services.NoteService.clearNoteService

class NoteServiceTest {

    @Test
    fun add_note_successful() {
        clearNoteService()
        val firstNote = Note(title = "Test Note", text = "This is the first note")
        val result = NoteService.add(firstNote)
        assertNotEquals(0, result)
    }

    @Test
    fun edit_note_successful() {
        clearNoteService()
        val firstNote = Note(title = "Test Note", text = "This is the first note")
        val secondNote = Note(title = "Second Test Note", text = "This is the second note")
        val thirdNote = Note(id = 2, title = "Third Test Note", text = "This is the third note")
        NoteService.add(firstNote)
        NoteService.add(secondNote)
        assertTrue(NoteService.edit(thirdNote))
    }

    @Test(expected = NoteNotFoundException::class)
    fun edit_note_unsuccessful() {
        clearNoteService()
        val firstNote = Note(title = "Test Note", text = "This is the first note")
        val secondNote = Note(title = "Second Test Note", text = "This is the second note")
        val thirdNote = Note(id = 3, title = "Third Test Note", text = "This is the third note")
        NoteService.add(firstNote)
        NoteService.add(secondNote)
        assertTrue(NoteService.edit(thirdNote))
    }

    @Test
    fun delete_note_successful() {
        clearNoteService()
        val firstNote = Note(title = "Test Note", text = "This is the first note")
        NoteService.add(firstNote)
        assertTrue(NoteService.delete(1))
    }

    @Test(expected = NoteNotFoundException::class)
    fun delete_note_unsuccessful() {
        clearNoteService()
        val firstNote = Note(title = "Test Note", text = "This is the first note")
        NoteService.add(firstNote)
        assertTrue(NoteService.delete(2))
    }


    @Test(expected = NoteNotFoundException::class)
    fun getById_unsuccessful() {
        clearNoteService()
        val firstNote = Note(title = "Test Note", text = "This is the first note")
        NoteService.add(firstNote)
        println(NoteService.getById(4))
    }
}
