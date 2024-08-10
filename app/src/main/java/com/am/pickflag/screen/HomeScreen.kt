package com.am.pickflag.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.pickflag.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.teal_700))
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(bottomStart = 140.dp, bottomEnd = 140.dp))
                .background(color = colorResource(R.color.teal_200))
        ) {
            Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "PICK THE FLAG",
                    modifier.padding(top = 120.dp, bottom = 50.dp),
                    fontSize = (18.sp),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white)
                )
            }

        }

        Box(
            modifier

                .align(Alignment.Center)
                .padding(top = 100.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.white).copy(alpha = 0.5f))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(start = 48.dp, end = 48.dp)
            ) {
                Text(text = "Tap the flag of", color = colorResource(id = R.color.white))
                Text(
                    text = "India",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                )
                Text(text = "Round 2/3", color = colorResource(id = R.color.white))
                Image(
                    modifier = modifier
                        .width(200.dp)
                        .height(150.dp),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.`in`),
                    contentDescription = "flag1"
                )
                Spacer(modifier.height(5.dp))
                Image(
                    modifier = modifier
                        .width(200.dp)
                        .height(150.dp),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.pk),
                    contentDescription = "flag2"
                )
                Spacer(modifier.height(5.dp))
                Image(
                    modifier = modifier
                        .width(200.dp)
                        .height(150.dp),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.au),
                    contentDescription = "flag3"
                )
                Spacer(modifier.height(5.dp))

            }
        }
    }

}