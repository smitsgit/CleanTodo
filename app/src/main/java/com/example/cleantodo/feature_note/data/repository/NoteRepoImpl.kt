package com.example.cleantodo.feature_note.data.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.cleantodo.feature_note.data.data_source.NoteDao
import com.example.cleantodo.feature_note.data.data_source.NoteDatabase
import com.example.cleantodo.feature_note.domain.model.Note
import com.example.cleantodo.feature_note.domain.repository.NoteRepo
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl(
    val noteDao: NoteDao
): NoteRepo {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return noteDao.addNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note)
    }
}