package com.example.cleantodo.feature_note.domain.util

sealed class NoteOrder(val type: OrderType) {
    class Title(type: OrderType): NoteOrder(type)
    class Color(type: OrderType): NoteOrder(type)
    class Date(type: OrderType): NoteOrder(type)

    fun copy(type: OrderType): NoteOrder {
        return when(this) {
            is Color -> Color(type)
            is Date -> Date(type)
            is Title -> Title(type)
        }
    }
}