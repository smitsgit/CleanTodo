package com.example.cleantodo.feature_note.presentation.add_edit

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
