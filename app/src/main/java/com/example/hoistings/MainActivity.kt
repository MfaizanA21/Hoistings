package com.example.hoistings

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoistings.ui.theme.HoistingsTheme
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoistingsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Layout()

                }
            }
        }
    }
}

@Composable
fun Layout() {
    var index: MutableState<Int> = rememberSaveable{
        mutableStateOf(0)
    }
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WordList(index.value) {index.value = 0}
        TwoButtons(index.value) { index.value++ }

    }
}

@Composable
fun WordList(index: Int,reset:() ->Unit) {
    val words = mutableListOf(
        "Man Bat",
        "Bulk",
        "Inferior Man",
        "Bhor",
        "WonderLess Women")
    if(index > words.size) {
        Text(text = "...", fontSize = 48.sp)
        reset()
    }
    if(index < words.size) {
        Text(
            fontFamily = FontFamily.Cursive,
            text = words[index],
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp
        )
    }

}

@Composable
fun TwoButtons(index: Int, increment: () -> Unit) {

    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center

    ) {
        Button(onClick = {
            Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show() })
        {
            Text(text = "Favourite")

        }
        Spacer(Modifier.padding(horizontal = 2.dp))

        Button(onClick = { increment() }) {
            Text(text = "Next")
        }

    }

}

