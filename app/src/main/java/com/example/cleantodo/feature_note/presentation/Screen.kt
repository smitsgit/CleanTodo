package com.example.cleantodo.feature_note.presentation

sealed class Screen(val route: String) {
    object NotesSreen: Screen("notes_screen")
    object AddEditNoteSreen: Screen("add_edit_note_screen")
}