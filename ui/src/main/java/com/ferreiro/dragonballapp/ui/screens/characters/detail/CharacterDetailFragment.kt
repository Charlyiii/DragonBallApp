package com.ferreiro.dragonballapp.ui.screens.characters.detail

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
import com.ferreiro.dragonballapp.ui.screens.characters.detail.view.CharacterDetailView
import com.ferreiro.dragonballapp.ui.utils.hideBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.setupTopAppBar
import com.ferreiro.dragonballapp.ui.utils.showTopAppBar
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
                                is CharacterState.Success -> {
                                    CharacterDetailView(
                                        character = characterState.character,
                                        onClickPlanet = {
                                            val planetID = it.id
                                            findNavController().navigate(
                                                CharacterDetailFragmentDirections.actionCharacterDetailFragmentToPlanetDetailFragment(
                                                    planetId = planetID
                                                )
                                            )
                                        },
                                        onClickTransformation = {
                                            findNavController().navigate(
                                                CharacterDetailFragmentDirections.actionCharacterDetailFragmentToTransformationDetailFragment(
                                                    transformation = it
                                                )
                                            )
                                        }
                                    )
                                    setupTopAppBar(characterState.character.characterName, true, activity = activity as MainActivity)
                                    hideBottomAppBar(activity as MainActivity)
                                }
                                is CharacterState.Loading -> LoadingItem()

                                is CharacterState.Error -> {
                                    showToast(toErrorMessage(characterState.error))
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
