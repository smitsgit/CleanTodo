package com.example.cleantodo.feature_note.domain.usecases

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)