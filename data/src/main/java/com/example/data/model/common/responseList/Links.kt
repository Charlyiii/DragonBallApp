package com.example.data.model.common.responseList

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val first: String,
    val last: String,
    val next: String,
    val previous: String
)