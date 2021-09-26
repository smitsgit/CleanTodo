package com.example.cleantodo.feature_note.domain.util

sealed class NoteOrder(val type: OrderType) {
    class Title(type: OrderType): NoteOrder(type)
    class Color(type: OrderType): NoteOrder(type)
    class Date(type: OrderType): NoteOrder(type)
}