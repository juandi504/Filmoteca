package com.campusdigitalfp.filmoteca

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.ui.theme.FilmotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Llamar a loadData pasando el contexto de la actividad
        loadData(context = this)
        setContent {
            FilmotecaTheme {
                AboutScreen()
            }
        }
    }
}

// Función que recibe el contexto y carga los datos necesarios
fun loadData(context: Context) {
    // Cargar datos aquí, por ejemplo cargar preferencias o recursos
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun AboutScreen() {
    val context = LocalContext.current // Obtener el contexto local
    val toastMessage = stringResource(id = R.string.toast) // Defino esta variable ya que si no no me deja usar stringResource en onClick
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center // Centra verticalmente
    ) {
        Text(
            text = stringResource(id = R.string.autor),
            modifier = Modifier.padding(16.dp),

            )
        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = stringResource(id = R.string.imagen_perfil),
            modifier = Modifier.size(100.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { showToast(context, toastMessage) },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(id = R.string.website))
            }
            Button(
                onClick = { showToast(context, toastMessage) },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(id = R.string.soporte))
            }
        }
        Button(
            onClick = { showToast(context, toastMessage) },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(stringResource(id = R.string.volver))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FilmotecaTheme {
        AboutScreen()
    }
}