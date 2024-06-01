package com.ferreiro.dragonballapp.ui.screens.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ui.R
import com.ferreiro.dragonballapp.ui.common.MainActivity
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.common.extensions.showToast
import com.ferreiro.dragonballapp.ui.common.extensions.toErrorMessage
import com.ferreiro.dragonballapp.ui.screens.characters.list.menu.FilterOrderMenuProvider
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
                                ) {
                                    val characterID = it.id
                                    findNavController().navigate(
                                        CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                                            characterId = characterID
                                        )
                                    )
                                }

                                is CharacterListState.Error -> {
                                    showToast(toErrorMessage(characterListState.error))
                                    findNavController().popBackStack()
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
        setupTopAppBar(
            getString(R.string.characters_text),
            withBackButton = true,
            withFilter = true,
            activity = activity as MainActivity
        )
        hideBottomAppBar(activity as MainActivity)

        // Filter Menu
        val menuHost: MenuHost = requireActivity()
        val filterOrderMenuProvider = FilterOrderMenuProvider(
            onSortByAffiliation = {
                Toast.makeText(
                    requireContext(),
                    "Ordenar por afiliación",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onSortByRace = {
                Toast.makeText(
                    requireContext(),
                    "Ordenar por raza",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onSortByGender = {
                Toast.makeText(
                    requireContext(),
                    "Ordenar por género",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onFilterByAffiliation = {
                Toast.makeText(
                    requireContext(),
                    "Filtrar por afiliación",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onFilterByGender = {
                Toast.makeText(
                    requireContext(),
                    "Filtrar por género",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onFilterByRace = {
                Toast.makeText(
                    requireContext(),
                    "Filtrar por raza",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        menuHost.addMenuProvider(
            filterOrderMenuProvider,
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
    }

    override fun onDestroyView() {
        setupTopAppBar(
            getString(R.string.app_name),
            withBackButton = true,
            activity = activity as MainActivity
        )
        showBottomAppBar(activity as MainActivity)
        super.onDestroyView()
    }
}
