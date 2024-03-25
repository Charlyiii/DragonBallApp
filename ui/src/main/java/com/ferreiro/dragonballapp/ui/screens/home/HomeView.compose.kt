package com.ferreiro.dragonballapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeView() {
    Column {
        Text(text = "Hello, World!")
        Text(text = "Hello, World!")
        Text(text = "Hello, World!")
        Text(text = "Hello, World!")
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeViewPreview() {
    HomeView()
}