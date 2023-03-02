package br.senai.sp.jandira.viacep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.viacep.models.Cep
import br.senai.sp.jandira.viacep.services.CepRetrofitService
import br.senai.sp.jandira.viacep.services.RetrofitFactory
import br.senai.sp.jandira.viacep.services.buscarCep
import br.senai.sp.jandira.viacep.ui.theme.ViaCepTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViaCepTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var cepState by remember {
        mutableStateOf("")
    }

    var resultState by remember {
        mutableStateOf("Resultado aqui!")
    }

    fun handleClickSearch() {
        resultState = buscarCep(cepState) {
            resultState = it
        }.toString()
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = cepState,
            onValueChange = { cepState = it.trim() },
            Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(
                    text = "Insira o seu CEP"
                )
            }
        )

        Button(
            onClick = { handleClickSearch() }
        ) {
            Text(text = "Buscar")
        }
        Text(text = resultState)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ViaCepTheme {
        Greeting("Android")
    }
}