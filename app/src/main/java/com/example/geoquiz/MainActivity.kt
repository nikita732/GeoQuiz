package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    var currentQuestionIndex = 0

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
    }
}

@Preview(showBackground = true)
@Composable
fun GeoQuizPreview() {
    GeoQuizTheme {
        GeoQuizApp()
    }
}