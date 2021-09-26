package com.example.cleantodo.feature_note.domain.usecases

import com.example.cleantodo.feature_note.domain.model.Note
import com.example.cleantodo.feature_note.domain.repository.NoteRepo

class DeleteNoteUseCase(
    private val noteRepo: NoteRepo
) {
    suspend operator fun invoke(note: Note) {
        return noteRepo.deleteNote(note)
    }
}