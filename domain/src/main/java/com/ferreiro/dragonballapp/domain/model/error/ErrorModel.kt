package com.ferreiro.dragonballapp.domain.model.error

sealed interface ErrorModel {
    data class CommonError(val message: String) : ErrorModel
}