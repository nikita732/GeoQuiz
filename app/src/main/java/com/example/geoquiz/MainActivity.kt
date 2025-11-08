package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GeoQuizApp()
                }
            }
        }
    }
}

@Composable
fun GeoQuizApp() {
    val questions = listOf(
        "Canberra is the capital of Australia.",
        "The Pacific Ocean is larger than the Atlantic Ocean.",
        "The Suez Canal connects the Red Sea and the Indian Ocean.",
        "The source of the Nile River is in Egypt.",
        "The Amazon River is the longest river in the Americas.",
        "Lake Baikal is the world's oldest and deepest freshwater lake."
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var areAnswerButtonsVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = "GeoQuiz",
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(0xFF7041D2))
                .padding(start = 24.dp, top = 22.dp, bottom = 16.dp),
            color = Color.White,
            fontSize = 22.sp,
            textAlign = TextAlign.Start
        )

        // Вопрос
        Text(
            text = questions[currentQuestionIndex],
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )

        // Ряд с True / False / Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Кнопка True
            if (areAnswerButtonsVisible) {
                Button(
                    onClick = { areAnswerButtonsVisible = false },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7041D2)
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("TRUE", color = Color.White, fontSize = 16.sp)
                }
            } else {
                Spacer(modifier = Modifier.weight(1f).padding(end = 50.dp))
            }

            // Колонка False + Next
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (areAnswerButtonsVisible) {
                    Button(
                        onClick = { areAnswerButtonsVisible = false },
                        modifier = Modifier
                            .padding(start = 50.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7041D2)
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("FALSE", color = Color.White, fontSize = 16.sp)
                    }
                }

                // Кнопка Next — показывается до последнего вопроса
                // или на последнем вопросе до ответа
                val isLastQuestion = currentQuestionIndex == questions.lastIndex
                val shouldShowNext =
                    (currentQuestionIndex < questions.lastIndex) ||
                            (isLastQuestion && areAnswerButtonsVisible)

                if (shouldShowNext) {
                    Button(
                        onClick = {
                            if (!isLastQuestion) {
                                currentQuestionIndex++
                                areAnswerButtonsVisible = true
                            }
                        },
                        enabled = !isLastQuestion || areAnswerButtonsVisible, // блокируем при ответе на последний
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7041D2),
                            disabledContainerColor = Color(0xFFBCA8E5)
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("NEXT  〉", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GeoQuizPreview() {
    GeoQuizTheme {
        GeoQuizApp()
    }
}
