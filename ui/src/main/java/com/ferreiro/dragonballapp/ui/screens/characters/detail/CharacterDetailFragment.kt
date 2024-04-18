package com.ferreiro.dragonballapp.ui.screens.characters.detail

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
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.screens.characters.detail.view.CharacterDetailView
import com.ferreiro.dragonballapp.ui.screens.characters.list.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: CharacterDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            viewModel.getCharacterById(args.characterId)
            lifecycleScope.launch {
                viewModel.characterStateFlow.collectLatest { characterState ->
                    setContent {
                        MaterialTheme {
                            when (characterState) {
                                is CharacterState.Success -> CharacterDetailView(
                                    character = characterState.character
                                )
                                is CharacterState.Loading -> LoadingItem()
                                //TODO: Implementar manejo de errores
                                is CharacterState.Error -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}
