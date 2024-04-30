package com.ferreiro.dragonballapp.ui.screens.characters.list

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
import com.ferreiro.dragonballapp.ui.screens.characters.list.view.CharacterListView
import com.ferreiro.dragonballapp.ui.utils.hideBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.hideTopAppBar
import com.ferreiro.dragonballapp.ui.utils.setupTopAppBar
import com.ferreiro.dragonballapp.ui.utils.showBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.showTopAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
//TODO Crear un BaseListFragment para reutilizar el código de la configuración del appbar
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
                                is CharacterListState.Success -> CharacterListView(
                                    characterList = characterListState.characterList,
                                    hideTopAppBar = { hideTopAppBar(activity as MainActivity) },
                                    showTopAppBar = { showTopAppBar(activity as MainActivity) }
                                ){
                                    val characterID = it.id
                                    findNavController().navigate(
                                        CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                                            characterId = characterID
                                        )
                                    )
                                }

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTopAppBar(getString(R.string.characters_text), true, activity = activity as MainActivity)
        hideBottomAppBar(activity as MainActivity)
    }

    override fun onDestroyView() {
        setupTopAppBar (getString(R.string.app_name), false, activity = activity as MainActivity)
        showBottomAppBar(activity as MainActivity)
        super.onDestroyView()
    }
    //TODO probar a hacer que las barras solo se oculten al hacer scroll
}
