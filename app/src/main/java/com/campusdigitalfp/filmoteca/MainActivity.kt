package com.campusdigitalfp.filmoteca

import android.content.Context
import android.content.Intent
import android.net.Uri
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

fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}

fun mandarEmail(context: Context, email: String, asunto: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
        putExtra(Intent.EXTRA_SUBJECT, asunto)
    }

    try {
        // Inicia la actividad de correo sin la verificación resolveActivity ya que si lo hago con el if
        // que aparece en el ejercicio no funciona, ya que siempre devuelve null el if y no ejecuta la
        // APP de correo del móvil.
        context.startActivity(intent)
    } catch (e: Exception) {
        // Maneja el caso cuando no haya aplicaciones para manejar el Intent
        Toast.makeText(context, "No se encontró ninguna aplicación de correo", Toast.LENGTH_SHORT)
            .show()
    }
}

@Composable
fun AboutScreen() {
    val context = LocalContext.current // Obtener el contexto local
    val toastMessage =
        stringResource(id = R.string.toast) // Defino esta variable ya que si no no me deja usar stringResource en onClick
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
                onClick = { abrirPaginaWeb("https://www.google.es", context) },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(id = R.string.website))
            }
            Button(
                onClick = {
                    mandarEmail(
                        context,
                        "jdfacims@fpvirtualaragon.es",
                        context.getString(R.string.incidencia_con_filmoteca)
                    )
                },
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