package com.example.limonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lemonade est aussi appéle Limonade dans le projet
        setContent {
            LemonadeTheme {
                // Background bleu , la couleur choisi pour personnalisé le projet
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var requiredSqueezes by remember { mutableStateOf(0) }

    // Sélection dynamique selon l'etape du choix
    val imageRes = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    // Sélection dynamique de d’instruction selon l’étape du choix
    val textRes = when (step) {
        1 -> R.string.lemon_tree_text
        2 -> R.string.lemon_squeeze_text
        3 -> R.string.lemon_drink_text
        else -> R.string.lemon_restart_text
    }

    // Texte affiché sur le bouton  selon l’étape du choix
    val buttonText = when (step) {
        1 -> "Pick Lemon"
        2 -> "Squeeze Lemon"
        3 -> "Drink Lemonade"
        else -> "Start Again"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = stringResource(textRes),
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White) // fond blanc autour de l’image
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(textRes),
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton personnalisé
        Button(
            onClick = {
                when (step) {
                    1 -> {
                        step = 2
                        requiredSqueezes = (2..4).random()
                        squeezeCount = 0
                    }
                    2 -> {
                        squeezeCount++
                        if (squeezeCount >= requiredSqueezes) step = 3
                    }
                    3 -> {
                        step = 4
                    }
                    4 -> {
                        step = 1
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0288D1),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .width(220.dp)
                .height(50.dp)
        ) {
            Text(text = buttonText, fontSize = 16.sp)
        }
    }
}
@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}