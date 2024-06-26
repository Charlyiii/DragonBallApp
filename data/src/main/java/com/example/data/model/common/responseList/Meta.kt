package com.example.data.model.common.responseList

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val currentPage: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalItems: Int,
    val totalPages: Int
)