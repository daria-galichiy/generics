package ru.netology.notes.services

interface CRUD<T> {
    fun add(entity: T): Int
    fun edit(entity: T): Boolean
    fun delete(id: Int): Boolean
    fun get(): List<T>
    fun getById(id: Int): T
}
