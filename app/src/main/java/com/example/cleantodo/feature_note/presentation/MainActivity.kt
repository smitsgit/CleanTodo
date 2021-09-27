package com.example.cleantodo.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.cleantodo.feature_note.presentation.add_edit.components.AddEditScreen
import com.example.cleantodo.feature_note.presentation.notes.NotesScreen
import com.example.cleantodo.ui.theme.CleanTodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanTodoTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesSreen.route
                    ) {
                        composable(route = Screen.NotesSreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteSreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",

                            arguments = listOf(
                                navArgument("noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument("noteColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val noteColor = it.arguments?.getInt("noteColor") ?: -1
                            AddEditScreen(
                                navController = navController,
                                noteColor = noteColor
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanTodoTheme {

    }
}