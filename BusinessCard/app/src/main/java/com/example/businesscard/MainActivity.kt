package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color(0xFF084765)
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    NameCard()
    ContactCard()
}

@Composable
private fun NameCard() {
    val image = painterResource(id = R.drawable.android_logo)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 200.dp)
    ) {
        Image(painter = image, contentDescription = null, modifier = Modifier.height(100.dp))
        Text(
            text = "Ling Jiang",
            fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colors.onPrimary
        )
        Text(
            text = "Android Developer Extraordinaire",
            color = Color(0xFF3ddc84)
        )
    }
}

@Composable
private fun ContactCard() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        ContactRow(icon = Icons.Rounded.Phone, text = "156-5293-0212")
        ContactRow(icon = Icons.Rounded.Share, text = "@kyooryoo")
        ContactRow(icon = Icons.Rounded.Email, text = "kyooryoo@hotmail.com")
    }
}

@Composable
fun ContactRow(icon: ImageVector, text: String) {
    Divider(color = Color.LightGray, thickness = 1.dp)
    Row(
        modifier = Modifier.padding(start = 64.dp, top = 8.dp, bottom = 8.dp)
    ) {

        Icon(icon, contentDescription = null, tint = Color(0xFF3ddc84))
        Text(
            text = text,
            modifier = Modifier
                .padding(end = 64.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Right,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}