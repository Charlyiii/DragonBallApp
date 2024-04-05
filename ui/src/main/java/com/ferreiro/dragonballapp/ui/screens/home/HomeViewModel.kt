package com.ferreiro.dragonballapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.HomeOptionType
import com.ferreiro.dragonballapp.domain.repository.HomeOptionsRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import com.ferreiro.dragonballapp.ui.vo.HomeOptionVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeOptionsRepository: HomeOptionsRepository
) : ViewModel() {

    private val _homeItemStateFlow: MutableStateFlow<List<HomeOptionVO>> =
        MutableStateFlow(listOf())

    val homeItemStateFlow: StateFlow<List<HomeOptionVO>> = _homeItemStateFlow

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            homeOptionsRepository.getHomeOptions().collectLatest { result ->
                when (result) {
                    is Either.Success -> {
                        result.data.map { homeOption ->
                            when (homeOption) {
                                HomeOptionType.CHARACTERS -> {
                                    _homeItemStateFlow.value += HomeOptionVO(
                                        R.string.characters_text,
                                        R.drawable.characters_img,
                                        R.id.action_homeFragment_to_characterListFragment
                                    )
                                }

                                HomeOptionType.PLANETS -> {
                                    _homeItemStateFlow.value +=
                                        HomeOptionVO(
                                            R.string.planets_text,
                                            R.drawable.planets_img,
                                            R.id.action_homeFragment_to_planetListFragment
                                        )
                                }
                            }
                        }
                    }

                    is Either.Error -> {
                        //TODO: Handle error
                    }
                }
            }
        }
    }
}
