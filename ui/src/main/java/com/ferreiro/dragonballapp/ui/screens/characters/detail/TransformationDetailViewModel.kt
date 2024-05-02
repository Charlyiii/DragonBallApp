package com.ferreiro.dragonballapp.ui.screens.characters.detail

import androidx.lifecycle.ViewModel
import com.ferreiro.dragonballapp.domain.model.TransformationModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class TransformationDetailViewModel @Inject constructor() : ViewModel() {
    private val _transformationStateFlow =
        MutableStateFlow<TransformationState>(TransformationState.Loading)
    val transformationStateFlow: StateFlow<TransformationState> = _transformationStateFlow

    fun setTransformation(transformation: TransformationModel?) {
        transformation?.let {
            _transformationStateFlow.value = TransformationState.Success(transformation)
        } ?: run {
            _transformationStateFlow.value = TransformationState.Error(error = ErrorModel.CommonError("Transformation not found"))
        }
    }
}


sealed interface TransformationState {
    data class Success(val transformation: TransformationModel) : TransformationState
    data class Error(val error: ErrorModel) : TransformationState
    data object Loading : TransformationState
}
