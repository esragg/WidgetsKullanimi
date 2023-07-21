package com.pisiitech.widgetskullanimi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pisiitech.widgetskullanimi.ui.theme.WidgetsKullanimiTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.net.PasswordAuthentication

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
                    SayfaTiklanma()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WidgetsKullanimiTheme {
        SayfaTiklanma()
    }
}

@Composable
fun SayfaTiklanma() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit){ detectTapGestures(
                onTap = {//normal tiklanilma islemi
                    Log.e("Box","Tiklandi")
                },
                onDoubleTap = {
                    Log.e("Box","Cift Tiklandi")
                },
                onLongPress = {
                    Log.e("Box","Uzerine Uzun Basildi")
                }
            ) }
        )
    }
}

@Composable
fun SayfaCheckBox() {
    val checkboxDurum = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Checkbox(
                checked = checkboxDurum.value,
                onCheckedChange = {
                    checkboxDurum.value = it
                    Log.e("CheckBox secildi", it.toString())
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Text(text = "Jetpack Compose", modifier = Modifier.padding(10.dp))
        }
        Button(onClick = {
            Log.e("CheckBox en son durum", checkboxDurum.value.toString())
        }) {
            Text(text = "Goster")
        }
    }
}


@Composable
fun SayfaSwitch() {
    val switchDurum = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            checked = switchDurum.value,
            onCheckedChange = {
                switchDurum.value = it
                Log.e("Switch secildi", it.toString())
            },
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color.Red,//cubuk rengi
                checkedThumbColor = Color.Blue, //top rengi
                uncheckedTrackColor = Color.Green,
                uncheckedThumbColor = Color.Black
            )
        )
        Button(onClick = {
            Log.e("Switch en son durum", switchDurum.value.toString())
        }) {
            Text(text = "Goster")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SayfaFab() {
    Scaffold(
        content = {

        },
        floatingActionButton = {
            ExtendedFloatingActionButton( //Yazili Fab
                onClick = {
                    Log.e("Fab","Tiklandi")
                },
                text = { Text(text = "EKLE", color = Color.White)},
                containerColor = Color.Red,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription = "Fab_ekle_icon",
                        tint = Color.White)
                }
            )
        }
    )
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
            label = { Text(text = "Veri giriniz")},
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Gray,
                textColor = Color.Red,
                focusedIndicatorColor = Color.Green,
                focusedLabelColor = Color.Yellow
            ),
            visualTransformation = PasswordVisualTransformation(), //girdigimizi sifre olarak * li hale donusturuyor
            //klavyenin turunu degistirme
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)//sadece sayi girecegimi varsayarsak
        )
        Button(onClick = {
            alinanVeri.value = tf.value
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(50)
            ) {
            Text(text = "Veriyi Al")
        }
        OutlinedTextField(
            value = tfOutlined.value,
            onValueChange = {tfOutlined.value = it },
            label = { Text(text = "Veri giriniz")}
        )
        OutlinedButton(onClick = {
            alinanVeri.value = tfOutlined.value
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Veriyi Al Outlined")
        }
    }
}