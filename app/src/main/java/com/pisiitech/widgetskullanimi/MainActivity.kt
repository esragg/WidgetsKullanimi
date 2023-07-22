package com.pisiitech.widgetskullanimi

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
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
                    SayfaDinamikDropDownMenu()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WidgetsKullanimiTheme {
        SayfaDinamikDropDownMenu()
    }
}

@Composable
fun SayfaDinamikDropDownMenu() {
    val ulkeListe = listOf("Turkiye", "Italya", "Almanya", "Japonya", "Rusya", "Cin")
    val menuAcilisKontrol = remember { mutableStateOf(false) }
    val secilenIndeks = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .size(100.dp, 50.dp)
                    .clickable {
                        menuAcilisKontrol.value = true
                    }
                ) {
                Text(text = ulkeListe[secilenIndeks.value])
                Image(painter = painterResource(id = R.drawable.dropdownmenu_resim),
                    contentDescription = "")

            }
            DropdownMenu(
                expanded = menuAcilisKontrol.value,
                onDismissRequest = { menuAcilisKontrol.value = false}) {

                ulkeListe.forEachIndexed { indeks, ulke ->
                    DropdownMenuItem(
                        text = { Text(text = ulke) },
                        onClick = {
                            Log.e("Menu","Ulke secildi: $ulke")
                            menuAcilisKontrol.value = false
                            secilenIndeks.value = indeks
                        })
                }
            }
        }
        Button(onClick = {
            Log.e("Menu","En son secilen ulke: ${ulkeListe[secilenIndeks.value]}")
        }) {
            Text(text = "Goster")
        }
    }
}


@Composable
fun SayfaDropDownMenu() {
    val menuAcilisKontrol = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
           Button(onClick = {
                menuAcilisKontrol.value = true
           }) {
               Text(text = "Goster")
           }

            DropdownMenu(
                expanded = menuAcilisKontrol.value,
                onDismissRequest = {
                    menuAcilisKontrol.value = false}) {
                DropdownMenuItem(
                    text = { Text(text = "Sil") },
                    onClick = {
                        Log.e("Menu","Sil secildi")
                        menuAcilisKontrol.value = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Guncelle") },
                    onClick = {
                        Log.e("Menu","Guncelle secildi")
                        menuAcilisKontrol.value = false
                    })
            }
        }
    }
}
@Composable
fun SayfaImage() {
    Column {
        val activity = (LocalContext.current as Activity)
        Image(bitmap = ImageBitmap.imageResource(id =
            activity.resources.getIdentifier(
                "yemekresim",
                "drawable",
                activity.packageName)
        ),
        contentDescription = "")
        Image(painter = painterResource(id = R.drawable.resim), contentDescription = "" )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SayfaWebview() {
    val url = "https://gelecegiyazanlar.turkcell.com.tr/"
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}
@Composable
fun SayfaProgressVeSlider() {
    val progressDurum = remember { mutableStateOf(false) }
    val sliderDeger = remember { mutableStateOf(0f) } //slider bize ondalikli sekilde sonuc veriyor
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(progressDurum.value) {
            CircularProgressIndicator(color = Color.Red)
        }

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                progressDurum.value = true
            }) {
                Text(text = "Basla")
            }
            Button(onClick = {
                progressDurum.value = false
            }) {
                Text(text = "Dur")
            }
        }

        Text(text = "Sonuc : ${sliderDeger.value.toInt()}") //kusurati gormek istemedigimden

        Slider(
            value = sliderDeger.value,
            onValueChange = {sliderDeger.value = it },
            valueRange = 0f..100f,
            modifier = Modifier.padding(all = 20.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Blue,
                inactiveTrackColor = Color.Yellow
            )
        )

        Button(onClick = {
            Log.e("Slider", sliderDeger.value.toInt().toString())
        }) {
            Text(text = "Goster")
        }
    }
}
@Composable
fun SayfaRadioButton() {
    val secilenIndex = remember { mutableStateOf(0) }
    val takimListesi = listOf("Real Madrid", "Barcelona", "Manchester United","Bayern Munich","Chelsea")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            takimListesi.forEachIndexed{indeks,takim ->
                Row(modifier = Modifier.clickable {
                    secilenIndex.value = indeks
                    Log.e("RadioButton secildi", takim)
                }) {
                   RadioButton(selected = (takim == takimListesi[secilenIndex.value]),
                       onClick = {
                           secilenIndex.value = indeks
                           Log.e("RadioButton secildi", takim)
                       })
                    Text(text = takim, modifier = Modifier.padding(10.dp))
                }
            }
        }
        Button(onClick = {
            Log.e("RadioButton enson durum", takimListesi[secilenIndex.value])
        }) {
            Text(text = "Goster")
        }
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
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {//normal tiklanilma islemi
                        Log.e("Box", "Tiklandi")
                    },
                    onDoubleTap = {
                        Log.e("Box", "Cift Tiklandi")
                    },
                    onLongPress = {
                        Log.e("Box", "Uzerine Uzun Basildi")
                    }
                )
            }
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