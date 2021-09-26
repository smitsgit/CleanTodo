package com.example.cleantodo.di

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.room.Room
import com.example.cleantodo.feature_note.data.data_source.NoteDatabase
import com.example.cleantodo.feature_note.data.repository.NoteRepoImpl
import com.example.cleantodo.feature_note.domain.repository.NoteRepo
import com.example.cleantodo.feature_note.domain.usecases.AddNoteUseCase
import com.example.cleantodo.feature_note.domain.usecases.DeleteNoteUseCase
import com.example.cleantodo.feature_note.domain.usecases.GetNotesUseCase
import com.example.cleantodo.feature_note.domain.usecases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
      return Room.databaseBuilder(
          app,
          NoteDatabase::class.java,
          NoteDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepo(noteDatabase: NoteDatabase): NoteRepo {
        return NoteRepoImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(noteRpo: NoteRepo): NoteUseCases {
        return NoteUseCases(
            GetNotesUseCase(noteRpo),
            DeleteNoteUseCase(noteRpo),
            AddNoteUseCase(noteRpo)
        )
    }
}