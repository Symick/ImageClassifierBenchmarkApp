package com.example.aibatteryusagebenchmark.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aibatteryusagebenchmark.R
import com.example.aibatteryusagebenchmark.domain.FetchClassification
import com.example.aibatteryusagebenchmark.presentation.image.BitMapMapper
import com.example.aibatteryusagebenchmark.presentation.image.GetImage
import com.example.aibatteryusagebenchmark.ui.dashboard.model.DashboardUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DashboardViewModel(
    val fetchClassification: FetchClassification,
    val getImage: GetImage,
    val bitMapMapper: BitMapMapper
) : ViewModel() {

    private val _state = MutableStateFlow(
        DashboardUIModel(
            selectedImage = R.drawable.placeholder,
            imageSelected = false,
        )
    )
    val state = _state.asStateFlow()


    fun onSelectImageClicked() {
        _state.value = _state.value.copy(selectedImage = getImage(), imageSelected = true, classification = null)
    }

    fun onUploadImageClicked(@DrawableRes image: Int) {
        val bitmap = bitMapMapper(image) ?: return

        viewModelScope.launch(Dispatchers.IO) {
            val classification = fetchClassification(bitmap)
            _state.value = _state.value.copy(classification = classification, imageSelected = false)
        }
    }
}