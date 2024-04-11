package com.ferreiro.dragonballapp.ui.screens.characters.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.screens.characters.list.view.CharacterListView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private val viewModel: CharacterListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            lifecycleScope.launch {
                viewModel.stateFlow.collectLatest { characterListState ->
                    setContent {
                        MaterialTheme {
                            when (characterListState) {
                                is CharacterListState.Success -> CharacterListView(characterList = characterListState.characterList)

                                //TODO:  Implementar manejo de errores
                                is CharacterListState.Error -> {
                                    Toast.makeText(
                                        context,
                                        "Error: ${characterListState.error}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                is CharacterListState.Loading -> LoadingItem()
                            }
                        }
                    }
                }
            }
        }
    }
}







