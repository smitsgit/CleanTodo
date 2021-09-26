package com.example.cleantodo.feature_note.domain.usecases

import com.example.cleantodo.feature_note.domain.model.Note
import com.example.cleantodo.feature_note.domain.repository.NoteRepo
import com.example.cleantodo.feature_note.domain.util.NoteOrder
import com.example.cleantodo.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val noteRepo: NoteRepo
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return noteRepo.getNotes().map { notes ->
           when(noteOrder) {
               is NoteOrder.Color -> {
                   when(noteOrder.type) {
                       OrderType.Ascending -> { notes.sortedBy { it.color } }
                       OrderType.Descending -> { notes.sortedByDescending { it.color } }
                   }
               }
               is NoteOrder.Date -> {
                   when(noteOrder.type) {
                       OrderType.Ascending -> { notes.sortedBy { it.timeStamp } }
                       OrderType.Descending -> { notes.sortedByDescending { it.timeStamp } }
                   }
               }
               is NoteOrder.Title -> {
                   when(noteOrder.type) {
                       OrderType.Ascending -> { notes.sortedBy { it.title } }
                       OrderType.Descending -> { notes.sortedByDescending { it.title } }
                   }
               }
           }
       }
    }
}