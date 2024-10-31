package com.example.aibatteryusagebenchmark.ui.dashboard.composables

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.aibatteryusagebenchmark.domain.model.Classification
import com.example.aibatteryusagebenchmark.presentation.DashboardViewModel
import com.example.aibatteryusagebenchmark.ui.dashboard.model.DashboardUIModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsState()

    ScreenContent(
        state = state,
        onSelectImageClicked = viewModel::onSelectImageClicked,
        onUploadImageClicked = viewModel::onUploadImageClicked,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun ScreenContent(
    state: DashboardUIModel,
    onSelectImageClicked: () -> Unit,
    onUploadImageClicked: (drawableRes: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Box(Modifier.fillMaxWidth()) {
            if (state.classification != null) {
                ClassificationLabel(
                    classification = state.classification,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .zIndex(10f)
                )
            }

            Image(
                painter = painterResource(state.selectedImage),
                contentDescription = "Picture of a european landmark",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Log.i("screen", "${state.imageSelected}")

        if (!state.imageSelected) {
            Button(onClick = onSelectImageClicked) {
                Text("Select image from gallery")
            }
        } else {
            Button(onClick = { onUploadImageClicked(state.selectedImage) }) {
                Text("Upload Image")
            }
        }
    }
}

@Composable
private fun ClassificationLabel(
    classification: Classification,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .background(
                MaterialTheme.colorScheme.background.copy(alpha = 0.5F)
            )
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(
            text = classification.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp
        )
    }
}

