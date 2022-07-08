package ru.netology.notes.exceptions

class NoteNotFoundException(noteId: Int): RuntimeException("Note with id $noteId not found")
