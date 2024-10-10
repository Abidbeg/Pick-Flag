package com.am.pickflag.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.am.pickflag.R
import com.am.pickflag.data.MAX_NO_OF_ROUND
import com.am.pickflag.viewmodel.CountryFlagViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val countryViewModel: CountryFlagViewModel = hiltViewModel()
    val flagUiState by countryViewModel.uiState.collectAsState()


    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.purple_200))
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
            if (flagUiState.currentViewFlags.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
                    Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.padding(start = 48.dp, end = 48.dp)
                ) {
                    Text(text = "Tap the flag of", color = colorResource(id = R.color.black))
                    Spacer(modifier.height(8.dp))
                    Text(
                        text = countryViewModel.currentFlag,
                        fontWeight = FontWeight.ExtraBold,
                        style = typography.titleLarge,
                    )
                    Spacer(modifier.height(8.dp))
                    Row {
                        Text(
                            text = "Round ${flagUiState.currentRound}/10",
                            color = colorResource(id = R.color.black)
                        )
                        Spacer(modifier.width(50.dp))
                        Text(
                            text = "Score: ${flagUiState.score}",
                            color = colorResource(id = R.color.black)
                        )
                    }
                    AsyncImage(
                        modifier = modifier
                            .width(200.dp)
                            .height(150.dp)
                            .clickable { countryViewModel.checkUserGuess(flagUiState.currentViewFlags[0].name) },
                        contentScale = ContentScale.Fit,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(flagUiState.currentViewFlags[0].flag)
                            .decoderFactory(SvgDecoder.Factory()).build(),
                        contentDescription = flagUiState.currentViewFlags[0].name
                    )
                    Spacer(modifier.height(5.dp))
                    AsyncImage(
                        modifier = modifier
                            .width(200.dp)
                            .height(150.dp)
                            .clickable { countryViewModel.checkUserGuess(flagUiState.currentViewFlags[1].name) },
                        contentScale = ContentScale.Fit,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(flagUiState.currentViewFlags[1].flag)
                            .decoderFactory(SvgDecoder.Factory()).build(),
                        contentDescription = flagUiState.currentViewFlags[1].name
                    )
                    Spacer(modifier.height(5.dp))
                    AsyncImage(
                        modifier = modifier
                            .width(200.dp)
                            .height(150.dp)
                            .clickable { countryViewModel.checkUserGuess(flagUiState.currentViewFlags[2].name) },
                        contentScale = ContentScale.Fit,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(flagUiState.currentViewFlags[2].flag)
                            .decoderFactory(SvgDecoder.Factory()).build(),
                        contentDescription = flagUiState.currentViewFlags[2].name
                    )
                    Spacer(modifier.height(5.dp))

                }
            }
        }
    }

    if (flagUiState.showPopup) {
        if (flagUiState.currentRound == MAX_NO_OF_ROUND) {
            FinalScoreDialog(
                score = flagUiState.score,
                onPlayAgain = { countryViewModel.resetGame() },
            )
        } else {
            ScoreDialog(
                onPlayAgain = { countryViewModel.nextRound() },
                userGuess = flagUiState.selectFlag
            )
        }
    }
}

@Composable
private fun ScoreDialog(
    onPlayAgain: () -> Unit,
    userGuess: Boolean,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = {
            if (userGuess) {
                Text(text = stringResource(R.string.congratulations))
            } else {
                Text(text = stringResource(R.string.wrong_guess))
            }
        },
        text = {
            if (userGuess) {
                Text(text = stringResource(R.string.you_right))
            } else {
                Text(text = stringResource(R.string.you_wrong))
            }
        },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.next_round))
            }
        }
    )
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = {
            Text(text = stringResource(R.string.game_over))
        },
        text = {
            Text(text = stringResource(R.string.you_scored, score))
        },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}


