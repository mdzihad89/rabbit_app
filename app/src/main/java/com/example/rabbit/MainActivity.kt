package com.example.rabbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.rabbit.ui.theme.RabbitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RabbitTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val viewModel:MainViewModel= hiltViewModel()
                    val rabbit=viewModel.state.value.rabbit
                    val isLoading=viewModel.state.value.isLoading

                    if (rabbit != null) {
                        Image(
                            painter = rememberImagePainter(
                                data = rabbit.imageUrl,
                                builder = {crossfade(true)}
                            ),
                            contentDescription = "Rabbit"


                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    if (rabbit != null) {
                        Text(text = rabbit.description)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = viewModel::getRandomRabbit,
                        modifier = Modifier.align(Alignment.End)

                    ) {
                        Text(text = "Next Rabbit")

                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    if (isLoading){
                        CircularProgressIndicator()
                    }





                }

            }
        }
    }
}

