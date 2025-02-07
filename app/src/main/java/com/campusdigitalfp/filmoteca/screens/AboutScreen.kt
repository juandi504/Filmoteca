package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

private fun abrirPaginaWeb(url: String, context: Context) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        // Definición de la barra superior dentro del Scaffold
        topBar = {
            TopAppBar(
                // Definimos los colores personalizados para la TopAppBar
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer, // Color de fondo de la barra
                    titleContentColor = MaterialTheme.colorScheme.primary, // Color del título
                ),
                // Icono de "HOME" en la izquierda que nos lleva a FilmListScreen
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .clickable { navController.popBackStack("list", false) }
                            .padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Volver a pantalla de inicio"
                        )
                    }
                },
                // Definimos el título que aparecerá en la TopAppBar
                title = {
                    Text(stringResource(id = R.string.Acerca_de)) // Texto del título
                },

            )
        }) {paddingValues ->
        val context = LocalContext.current // Obtener el contexto local
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra verticalmente
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
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(id = R.string.volver))
            }

        }
    }
}