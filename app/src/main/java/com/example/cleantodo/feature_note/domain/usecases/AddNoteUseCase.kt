package com.example.cleantodo.feature_note.domain.usecases

import com.example.cleantodo.feature_note.domain.model.InvalidNoteException
import com.example.cleantodo.feature_note.domain.model.Note
import com.example.cleantodo.feature_note.domain.repository.NoteRepo

class AddNoteUseCase(
    private val noteRepo: NoteRepo
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
           throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty")
        }
        return noteRepo.insertNote(note)
    }
}