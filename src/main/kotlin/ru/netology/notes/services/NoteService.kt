package ru.netology.notes.services

import ru.netology.notes.Note
import ru.netology.notes.services.CommentService.deleteCommentsAfterDeletingNote
import ru.netology.notes.exceptions.NoteNotFoundException

object NoteService : CRUD<Note> {
    private val notes = mutableListOf<Note>()
    private var noteIdCount = 1
    private var urlIdCount = 1

    override fun add(entity: Note): Int {
        notes.add(
            entity.copy(
                id = noteIdCount,
                date = System.currentTimeMillis() / 1000,
                viewUrl = "https://smth.ru/note/$urlIdCount"
            )
        )
        noteIdCount++
        urlIdCount++
        return notes.last().id
    }

    override fun edit(entity: Note): Boolean {
        for ((index, value) in notes.withIndex()) {
            if (value.id == entity.id) {
                notes[index] = entity.copy(id = value.id, date = value.date, viewUrl = value.viewUrl)
                return true
            }
        }
        throw NoteNotFoundException(entity.id)
    }

    override fun delete(id: Int): Boolean {
        for ((index, value) in notes.withIndex()) {
            if (value.id == id){
                deleteCommentsAfterDeletingNote(value.id)
                notes.removeAt(index)
                return true
            }
        }
        throw NoteNotFoundException(id)
    }

    override fun get(): List<Note> {
        return notes
    }

    override fun getById(id: Int): Note {
        for (note in notes) {
            if (note.id == id) return note
        }
        throw NoteNotFoundException(id)
    }

    fun showNotes() {
        for (note in notes) {
            println(note)
        }
    }

    fun clearNoteService() {
        notes.clear()
        noteIdCount = 1
        urlIdCount = 1
    }
}
