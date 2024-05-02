package com.ferreiro.dragonballapp.ui.screens.planets.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ferreiro.dragonballapp.ui.common.MainActivity
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.common.extensions.showToast
import com.ferreiro.dragonballapp.ui.common.extensions.toErrorMessage
import com.ferreiro.dragonballapp.ui.screens.planets.detail.view.PlanetDetailView
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
                                    ){
                                        val character = it.id
                                        findNavController().navigate(
                                            PlanetDetailFragmentDirections.actionPlanetDetailFragmentToCharacterDetailFragment(
                                                characterId = character
                                            )
                                        )
                                    }
                                    setupTopAppBar(planetState.planet.name, true, activity = activity as MainActivity)
                                    hideBottomAppBar(activity as MainActivity)
                                }
                                is PlanetState.Loading -> LoadingItem()

                                is PlanetState.Error -> {
                                    showToast(toErrorMessage(planetState.error))
                                }
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
