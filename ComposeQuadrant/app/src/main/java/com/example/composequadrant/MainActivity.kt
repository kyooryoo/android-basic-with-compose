package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    ComposeQuadrant()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrant() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        Row(Modifier.weight(weight = 1f)) {
            Quadrant(
                title = stringResource(R.string.title_1),
                paragraph = stringResource(R.string.paragraph_1),
                backgroundColor = Color.Green,
                modifier = Modifier.weight(weight = 1f)
            )
            Quadrant(
                title = stringResource(R.string.title_2),
                paragraph = stringResource(R.string.paragraph_2),
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(weight = 1f)
            )
        }
        Row(Modifier.weight(weight = 1f)) {
            Quadrant(
                title = stringResource(R.string.title_3),
                paragraph = stringResource(R.string.paragraph_3),
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(weight = 1f)
            )
            Quadrant(
                title = stringResource(R.string.title_4),
                paragraph = stringResource(R.string.paragraph_4),
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(weight = 1f)
            )
        }
    }
}

@Composable
private fun Quadrant(
    title: String, paragraph: String, backgroundColor: Color, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = paragraph,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantTheme {
        Quadrant(
            title = "Text composable",
            paragraph = "Displays text and follows Material Design guidelines.",
            backgroundColor = Color.Green
        )
    }
}