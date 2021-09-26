package com.example.cleantodo.feature_note.presentation.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleantodo.feature_note.domain.model.Note
import com.example.cleantodo.feature_note.domain.usecases.NoteUseCases
import com.example.cleantodo.feature_note.domain.util.NoteOrder
import com.example.cleantodo.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state = _state
    private var lastDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(actionEvent: NotesActionEvent) {
        when (actionEvent) {
            is NotesActionEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase(actionEvent.note)
                    lastDeletedNote = actionEvent.note
                }
            }
            is NotesActionEvent.Order -> {
                if (
                    state.value.notesOrder::class == actionEvent.noteOrder::class &&
                    state.value.notesOrder.type == actionEvent.noteOrder.type
                ) {
                    return
                }
                getNotes(actionEvent.noteOrder)
            }
            is NotesActionEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null

                }
            }
            NotesActionEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
          getNotesJob?.cancel()
          getNotesJob = noteUseCases.getNotesUseCase(noteOrder).onEach {
              _state.value = _state.value.copy(it, noteOrder)
          }.launchIn(viewModelScope)
    }

}