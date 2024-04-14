package com.ferreiro.dragonballapp.ui.screens.characters.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ui.R
import com.ferreiro.dragonballapp.ui.common.MainActivity
import com.ferreiro.dragonballapp.ui.common.components.LoadingItem
import com.ferreiro.dragonballapp.ui.screens.characters.list.view.CharacterListView
import com.google.android.material.appbar.MaterialToolbar
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTopAppBar(getString(R.string.characters_text), true)
        hideBottomAppBar()
    }

    override fun onDestroyView() {
        setupTopAppBar(getString(R.string.app_name), false)
        showBottomAppBar()
        super.onDestroyView()
    }

    //TODO probar a hacer que las barras solo se oculten al hacer scroll
    //TODO search y filtros
    private fun setupTopAppBar(titleOfScreen: String, withBackButton: Boolean) {
        (activity as MainActivity).findViewById<MaterialToolbar>(R.id.toolbar).apply {
            title = titleOfScreen

            if (withBackButton) {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    activity?.onBackPressed()
                }
            } else {
                navigationIcon = null
                menu.clear()
            }
        }
    }

    private fun hideBottomAppBar() {
        (requireActivity() as? MainActivity)?.setupBottomAppBar(isVisible = false)
    }

    private fun showBottomAppBar() {
        (requireActivity() as? MainActivity)?.setupBottomAppBar(isVisible = true)

    }
}
