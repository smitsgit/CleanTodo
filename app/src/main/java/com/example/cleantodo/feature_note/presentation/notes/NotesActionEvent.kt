package com.example.cleantodo.feature_note.presentation.notes

import com.example.cleantodo.feature_note.domain.model.Note
import com.example.cleantodo.feature_note.domain.util.NoteOrder

sealed class NotesActionEvent() {
    data class Order(val noteOrder: NoteOrder): NotesActionEvent()
    data class DeleteNote(val note: Note): NotesActionEvent()
    data class RestoreNote(val note: Note): NotesActionEvent()
    object ToggleOrderSection: NotesActionEvent()
}
