package com.ferreiro.dragonballapp.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            lifecycleScope.launch {
                viewModel.homeItemStateFlow.collectLatest { homeItemState ->
                    setContent {
                        MaterialTheme {
                            HomeView(homeItemState){ itemTitle ->
                                Toast.makeText(context, "Se ha hecho click en: ${context.getString(itemTitle)}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }
    }
}


