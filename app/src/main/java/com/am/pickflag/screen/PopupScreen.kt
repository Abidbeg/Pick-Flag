package com.am.pickflag.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun PopupScreen(modifier: Modifier = Modifier) {

    Popup(onDismissRequest = {}, alignment = Alignment.Center) {
        Box(contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Correct Answer Nice Work", fontWeight = FontWeight.Bold)
                HorizontalDivider(
                    color = Color.Black,
                    thickness = 2.dp,
                    modifier = modifier
                        .width(250.dp)
                        .padding(top = 16.dp, bottom = 16.dp)
                )
                Text(text = "Next", color = Color.Blue, fontWeight = FontWeight.Bold)
            }
        }

    }

}