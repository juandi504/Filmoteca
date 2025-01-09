package com.campusdigitalfp.filmoteca


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.campusdigitalfp.filmoteca.navigation.Navigation
import com.campusdigitalfp.filmoteca.ui.theme.FilmotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FilmotecaTheme {
                Navigation()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FilmotecaTheme {
        Navigation()
    }
}