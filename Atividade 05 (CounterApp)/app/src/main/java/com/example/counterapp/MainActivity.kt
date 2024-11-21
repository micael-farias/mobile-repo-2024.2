package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapp.ui.theme.CounterAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterApp()
                }
            }
        }
    }
}

@Composable
fun CounterApp() {
    var result by remember { mutableStateOf(0.0) }
    var input by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Comece a calcular!") }
    var operationsCount by remember { mutableStateOf(0) }

    // Função para atualizar a mensagem com base no resultado
    fun updateMessage() {
        message = when {
            result > 100 -> "Uau! Você está rico!"
            result < -100 -> "Parece que está no vermelho!"
            result == 0.0 -> "De volta ao ponto de partida!"
            else -> "Resultado: $result"
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Exibe o resultado com uma animação
        AnimatedContent(
            targetState = result,
            transitionSpec = {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            }
        ) { targetResult ->
            Text(
                text = "Resultado: $targetResult",
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Mostra a mensagem dinâmica
        Text(
            text = message,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(4.dp)
        )

        // Campo de entrada para número
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Digite um número") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    result += input.toDoubleOrNull() ?: 0.0
                    input = ""
                    operationsCount++
                    updateMessage()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Incrementar")
            }

            Button(
                onClick = {
                    result -= input.toDoubleOrNull() ?: 0.0
                    input = ""
                    operationsCount++
                    updateMessage()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Decrementar")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Button(
                onClick = {
                    result *= input.toDoubleOrNull() ?: 1.0
                    input = ""
                    operationsCount++
                    updateMessage()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Multiplicar")
            }

            Button(
                onClick = {
                    val value = input.toDoubleOrNull() ?: 1.0
                    if (value != 0.0) {
                        result /= value
                    }
                    input = ""
                    operationsCount++
                    updateMessage()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Dividir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = 0.0
                input = ""
                message = "Recomece do zero!"
                operationsCount = 0
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Operação aleatória
                when (Random.nextInt(4)) {
                    0 -> result += Random.nextDouble(1.0, 10.0)
                    1 -> result -= Random.nextDouble(1.0, 10.0)
                    2 -> result *= Random.nextDouble(1.0, 2.0)
                    3 -> {
                        val randomValue = Random.nextDouble(1.0, 10.0)
                        if (randomValue != 0.0) result /= randomValue
                    }
                }
                operationsCount++
                updateMessage()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Surpresa!")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Exibe a contagem de operações
        Text(
            text = "Operações realizadas: $operationsCount",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
