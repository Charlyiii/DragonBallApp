package com.ferreiro.dragonballapp.ui.screens.characters.list.menu

import android.graphics.Color
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import com.example.ui.R

class FilterOrderMenuProvider(
    private val onSortByAffiliation: () -> Unit,
    private val onSortByRace: () -> Unit,
    private val onSortByGender: () -> Unit,
    private val onFilterByAffiliation: () -> Unit,
    private val onFilterByGender: () -> Unit,
    private val onFilterByRace: () -> Unit
) : MenuProvider {

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_filter_order, menu)

        // Configurar la búsqueda
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        // Aplicar estilos
        val searchText =
            searchView.findViewById<AutoCompleteTextView>(androidx.appcompat.R.id.search_src_text)
        searchText.setTextColor(Color.WHITE)
        searchText.setHintTextColor(Color.WHITE)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.sort_by_affiliation -> {
                onSortByAffiliation()
                true
            }

            R.id.sort_by_race -> {
                onSortByRace()
                true
            }

            R.id.sort_by_gender -> {
                onSortByGender()
                true
            }

            R.id.filter_by_affiliation -> {
                onFilterByAffiliation()
                true
            }

            R.id.filter_by_gender -> {
                onFilterByGender()
                true
            }

            R.id.filter_by_race -> {
                onFilterByRace()
                true
            }

            else -> false
        }
    }
}