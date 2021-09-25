package com.example.cleantodo.feature_note.data.data_source

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleantodo.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
}