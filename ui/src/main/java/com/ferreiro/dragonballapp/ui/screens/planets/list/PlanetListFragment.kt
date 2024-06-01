package com.ferreiro.dragonballapp.ui.screens.planets.list

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
import androidx.navigation.fragment.findNavController
import com.example.ui.R
import com.ferreiro.dragonballapp.ui.common.MainActivity
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.common.extensions.showToast
import com.ferreiro.dragonballapp.ui.common.extensions.toErrorMessage
import com.ferreiro.dragonballapp.ui.screens.characters.list.CharacterListFragmentDirections
import com.ferreiro.dragonballapp.ui.screens.characters.list.CharacterListState
import com.ferreiro.dragonballapp.ui.screens.characters.list.view.CharacterListView
import com.ferreiro.dragonballapp.ui.screens.planets.list.view.PlanetListView
import com.ferreiro.dragonballapp.ui.utils.hideBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.hideTopAppBar
import com.ferreiro.dragonballapp.ui.utils.setupTopAppBar
import com.ferreiro.dragonballapp.ui.utils.showBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.showTopAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanetListFragment : Fragment() {
    private val viewModel: PlanetListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            lifecycleScope.launch {
                viewModel.stateFlow.collectLatest { planetListState ->
                    setContent {
                        MaterialTheme {
                            when (planetListState) {
                                is PlanetListState.Success -> PlanetListView(
                                    planetList = planetListState.planetList,
                                    hideTopAppBar = { hideTopAppBar(activity as MainActivity) },
                                    showTopAppBar = { showTopAppBar(activity as MainActivity) }
                                ){
                                    val planetID = it.id
                                    findNavController().navigate(
                                        PlanetListFragmentDirections.actionPlanetListFragmentToPlanetDetailFragment(
                                            planetId = planetID
                                        )
                                    )
                                }

                                is PlanetListState.Error -> {
                                    showToast(toErrorMessage(planetListState.error))
                                    findNavController().popBackStack()
                                }

                                is PlanetListState.Loading -> LoadingItem()
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTopAppBar(getString(R.string.planets_text), withBackButton = true, activity = activity as MainActivity)
        hideBottomAppBar(activity as MainActivity)
    }

    override fun onDestroyView() {
        setupTopAppBar (getString(R.string.app_name), withBackButton = true, activity = activity as MainActivity)
        showBottomAppBar(activity as MainActivity)
        super.onDestroyView()
    }
    //TODO probar a hacer que las barras solo se oculten al hacer scroll
}




