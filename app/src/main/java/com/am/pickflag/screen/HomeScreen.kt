package com.am.pickflag.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.am.pickflag.R
import com.am.pickflag.viewmodel.CountryFlagViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var showPopup by rememberSaveable {
        mutableStateOf(false)
    }

    val countryViewModel: CountryFlagViewModel = hiltViewModel()
    val countryList = countryViewModel.flagList.collectAsState()


    var randomArray = arrayOf(
        (0..countryList.value.data.size).random(),
        (0..countryList.value.data.size).random(),
        (0..countryList.value.data.size).random()
    )
    var pickTheName = (0..2).random()

    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.teal_700))
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

        Box(
            modifier
                .align(Alignment.Center)
                .padding(top = 100.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.white).copy(alpha = 0.5f))
        ) {
            if (countryList.value.data.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
                    Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.padding(start = 48.dp, end = 48.dp)
                ) {
                    Text(text = "Tap the flag of", color = colorResource(id = R.color.white))
                    Text(
                        text = countryList.value.data[randomArray[pickTheName]].name,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                    )
                    Text(text = "Round 2/3", color = colorResource(id = R.color.white))
                    AsyncImage(
                        modifier = modifier
                            .width(200.dp)
                            .height(150.dp),
                        contentScale = ContentScale.Fit,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(countryList.value.data[randomArray[0]].flag)
                            .decoderFactory(SvgDecoder.Factory()).build(),
                        contentDescription = countryList.value.data[randomArray[0]].name
                    )
                    Spacer(modifier.height(5.dp))
                    AsyncImage(
                        modifier = modifier
                            .width(200.dp)
                            .height(150.dp),
                        contentScale = ContentScale.Fit,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(countryList.value.data[randomArray[1]].flag)
                            .decoderFactory(SvgDecoder.Factory()).build(),
                        contentDescription = countryList.value.data[randomArray[1]].name
                    )
                    Spacer(modifier.height(5.dp))
                    AsyncImage(
                        modifier = modifier
                            .width(200.dp)
                            .height(150.dp)
                            .clickable { showPopup = true },
                        contentScale = ContentScale.Fit,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(countryList.value.data[randomArray[2]].flag)
                            .decoderFactory(SvgDecoder.Factory()).build(),
                        contentDescription = countryList.value.data[randomArray[2]].name
                    )
                    Spacer(modifier.height(5.dp))

                }
            }
        }
    }


    if (showPopup) {
        Box(
            modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Transparent)
        ) {
            Box(
                modifier
                    .align(Alignment.Center)
                    .height(100.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray), contentAlignment = Alignment.Center
            )
            {
                PopupScreen()
            }
        }
    }
}


