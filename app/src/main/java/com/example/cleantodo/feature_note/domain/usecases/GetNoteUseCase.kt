package com.example.cleantodo.feature_note.domain.usecases

import com.example.cleantodo.feature_note.domain.model.Note
import com.example.cleantodo.feature_note.domain.repository.NoteRepo

class GetNoteUseCase(
    private val noteRepo: NoteRepo
) {
    suspend operator fun invoke(id: Int): Note? {
        return noteRepo.getNoteById(id)
    }
}