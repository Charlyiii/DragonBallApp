package com.ferreiro.dragonballapp.ui.screens.planets.list

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.screens.characters.list.view.CharacterListView
import com.ferreiro.dragonballapp.ui.screens.planets.list.view.PlanetListView

class PlanetListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    PlanetListView()
                }
            }
        }
    }
}


