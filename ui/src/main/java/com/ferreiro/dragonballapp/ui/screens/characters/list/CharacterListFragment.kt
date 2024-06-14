package com.ferreiro.dragonballapp.ui.screens.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.ui.common.MainActivity
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.common.extensions.showToast
import com.ferreiro.dragonballapp.ui.common.extensions.toErrorMessage
import com.ferreiro.dragonballapp.ui.common.extensions.toReadableString
import com.ferreiro.dragonballapp.ui.screens.characters.list.menu.FilterOrderMenuProvider
import com.ferreiro.dragonballapp.ui.screens.characters.list.utils.GroupingType
import com.ferreiro.dragonballapp.ui.screens.characters.list.view.CharacterListView
import com.ferreiro.dragonballapp.ui.utils.hideBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.setupTopAppBar
import com.ferreiro.dragonballapp.ui.utils.showBottomAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private val viewModel: CharacterListViewModel by viewModels()
    private var groupingType by mutableStateOf(GroupingType.NONE)
    private var isFiltering by mutableStateOf(false)

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
                                    //TODO
                                    //hideTopAppBar = { hideTopAppBar(activity as MainActivity) },
                                    //showTopAppBar = { showTopAppBar(activity as MainActivity) },
                                    isFiltering = isFiltering,
                                    groupingType = groupingType // Pasar el tipo de agrupación al Composable
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

        // Observa las listas de opciones en el ViewModel
        lifecycleScope.launch {
            viewModel.availableAffiliations.collectLatest { affiliations ->
                viewModel.availableRaces.collectLatest { races ->
                    viewModel.availableGenders.collectLatest { genders ->
                        delay(2000)
                        setupMenu(affiliations, races, genders)
                    }
                }
            }
        }

        // Observa el tipo de agrupación en el ViewModel
        lifecycleScope.launch {
            viewModel.groupingType.collect { type ->
                groupingType = type // Actualizar el tipo de agrupación
            }
        }
    }

    private fun setupMenu(
        affiliations: List<Affiliation>,
        races: List<String>,
        genders: List<String>
    ) {
        if (view != null && viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            val menuHost: MenuHost = requireActivity()
            val filterOrderMenuProvider = FilterOrderMenuProvider(
                onSortAZ = {
                    viewModel.sortAZ()
                    isFiltering = true
                },
                onSortZA = {
                    viewModel.sortZA()
                    isFiltering = true
                },
                onSortByAffiliation = {
                    viewModel.sortByAffiliation()
                    isFiltering = true
                },
                onSortByRace = {
                    viewModel.sortByRace()
                    isFiltering = true
                },
                onSortByGender = {
                    viewModel.sortByGender()
                    isFiltering = true
                },
                onFilterByAffiliation = { affiliation ->
                    viewModel.filterByAffiliation(
                        affiliation,
                        requireContext()
                    )
                    isFiltering = true
                },
                onFilterByGender = { gender ->
                    viewModel.filterByGender(gender)
                    isFiltering = true
                },
                onFilterByRace = { race ->
                    viewModel.filterByRace(race)
                    isFiltering = true
                },

                availableAffiliations = affiliations.map { it.toReadableString(requireContext()) },
                availableRaces = races,
                availableGenders = genders,
                onSearchTextChange = { newText ->
                    viewModel.filterByQuery(newText)
                    isFiltering = true
                }
            )

            menuHost.addMenuProvider(
                filterOrderMenuProvider,
                viewLifecycleOwner,
                Lifecycle.State.RESUMED
            )
        }
    }
    override fun onDestroyView() {
        setupTopAppBar(
            getString(R.string.app_name),
            withBackButton = true,
            activity = activity as MainActivity
        )
        showBottomAppBar(activity as MainActivity)
        super.onDestroyView()
        isFiltering = false
    }
}
