package com.ferreiro.dragonballapp.ui.screens.planets.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.ui.R
import com.ferreiro.dragonballapp.ui.common.MainActivity
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.screens.characters.detail.CharacterDetailFragmentArgs
import com.ferreiro.dragonballapp.ui.screens.characters.detail.CharacterDetailViewModel
import com.ferreiro.dragonballapp.ui.screens.characters.detail.CharacterState
import com.ferreiro.dragonballapp.ui.screens.characters.detail.view.CharacterDetailView
import com.ferreiro.dragonballapp.ui.screens.planets.detail.view.PlanetDetailView.PlanetDetailView
import com.ferreiro.dragonballapp.ui.utils.hideBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.setupTopAppBar
import com.ferreiro.dragonballapp.ui.utils.showTopAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanetDetailFragment : Fragment() {

    private val args: PlanetDetailFragmentArgs by navArgs()
    private val viewModel: PlanetDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            viewModel.getPlanetById(args.planetId)
            lifecycleScope.launch {
                viewModel.planetStateFlow.collectLatest { planetState ->
                    setContent {
                        MaterialTheme {
                            when (planetState) {
                                is PlanetState.Success -> {
                                    PlanetDetailView(
                                        planet = planetState.planet,
                                    )
                                    setupTopAppBar(planetState.planet.name, true, activity = activity as MainActivity)
                                    hideBottomAppBar(activity as MainActivity)
                                }
                                is PlanetState.Loading -> LoadingItem()
                                //TODO: Implementar manejo de errores
                                is PlanetState.Error -> {}
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTopAppBar(activity as MainActivity)
        hideBottomAppBar(activity as MainActivity)
    }
}
