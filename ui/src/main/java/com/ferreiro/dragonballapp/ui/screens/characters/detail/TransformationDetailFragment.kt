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
import com.ferreiro.dragonballapp.ui.screens.characters.detail.view.CharacterDetailView
import com.ferreiro.dragonballapp.ui.screens.characters.detail.view.TransformationDetailView
import com.ferreiro.dragonballapp.ui.utils.hideBottomAppBar
import com.ferreiro.dragonballapp.ui.utils.setupTopAppBar
import com.ferreiro.dragonballapp.ui.utils.showTopAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//TODO se podría usar la misma pantalla para mostrar el detalle de un personaje
@AndroidEntryPoint
class TransformationDetailFragment : Fragment() {
    private val args: TransformationDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    TransformationDetailView(transformation = args.transformation)
                }
                setupTopAppBar(args.transformation.name, true, activity = activity as MainActivity)
                hideBottomAppBar(activity as MainActivity)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTopAppBar(activity as MainActivity)
        hideBottomAppBar(activity as MainActivity)
    }
}
