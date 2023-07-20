package com.pisiitech.widgetskullanimi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pisiitech.widgetskullanimi.ui.theme.WidgetsKullanimiTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WidgetsKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SayfaButtonTextTextField()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WidgetsKullanimiTheme {
        SayfaButtonTextTextField()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SayfaButtonTextTextField() {
    val tf = remember { mutableStateOf("") }
    val tfOutlined = remember { mutableStateOf("") }
    val alinanVeri = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gelen Veri: ${alinanVeri.value}",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                background = Color.Blue
                )
            )
        TextField(
            value = tf.value,
            onValueChange = {tf.value = it },
            label = { Text(text = "Veri giriniz")}
        )
        Button(onClick = {
            alinanVeri.value = tf.value
        }) {
            Text(text = "Veriyi Al")
        }
        OutlinedTextField(
            value = tfOutlined.value,
            onValueChange = {tfOutlined.value = it },
            label = { Text(text = "Veri giriniz")}
        )
        OutlinedButton(onClick = {
            alinanVeri.value = tfOutlined.value
        }) {
            Text(text = "Veriyi Al Outlined")
        }
    }
}