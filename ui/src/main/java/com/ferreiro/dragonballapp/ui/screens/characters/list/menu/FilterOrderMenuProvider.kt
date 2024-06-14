package com.ferreiro.dragonballapp.ui.screens.characters.list.menu

import android.content.Context
import android.graphics.Color
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import com.example.ui.R

class FilterOrderMenuProvider(
    private val onSortAZ: () -> Unit,
    private val onSortZA: () -> Unit,
    private val onSortByAffiliation: () -> Unit,
    private val onSortByRace: () -> Unit,
    private val onSortByGender: () -> Unit,
    private val onFilterByAffiliation: (String) -> Unit,
    private val onFilterByGender: (String) -> Unit,
    private val onFilterByRace: (String) -> Unit,
    private val availableAffiliations: List<String> = emptyList(),
    private val availableRaces: List<String> = emptyList(),
    private val availableGenders: List<String> = emptyList(),
    private val onSearchTextChange: (String) -> Unit,
) : MenuProvider, SearchView.OnQueryTextListener {

    private lateinit var searchView: SearchView

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_filter_order, menu)

        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)


        // Cambia el color del texto del SearchView
        val searchEditText: EditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(Color.WHITE)
        searchEditText.setHintTextColor(Color.WHITE)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.sort_AZ -> {
                onSortAZ()
                true
            }
            R.id.sort_ZA -> {
                onSortZA()
                true
            }
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
                createSubMenu(menuItem, availableAffiliations) { affiliation ->
                    onFilterByAffiliation(affiliation)
                }
                true
            }
            R.id.filter_by_race -> {
                createSubMenu(menuItem, availableRaces) { race ->
                    onFilterByRace(race)
                }
                true
            }
            R.id.filter_by_gender -> {
                createSubMenu(menuItem, availableGenders) { gender ->
                    onFilterByGender(gender)
                }
                true
            }
            else -> false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { onSearchTextChange(it) }

        // Ocultar el teclado
        val inputMethodManager = searchView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { onSearchTextChange(it) }
        return true
    }

    private fun createSubMenu(menuItem: MenuItem, items: List<String>, onClickAction: (String) -> Unit) {
        val subMenu = menuItem.subMenu // Obtener el submenu asociado al menuItem

        // Limpiar el submenu antes de agregar nuevos elementos
        subMenu?.clear()

        // Agregar elementos al submenu
        for ((index, item) in items.withIndex()) {
            subMenu?.add(
                Menu.NONE, // ID único para cada elemento
                index, // Posición del elemento
                Menu.NONE, // Orden
                item // Texto del elemento
            )?.apply {
                setOnMenuItemClickListener {
                    onClickAction(item)
                    true
                }
            }
        }
    }
}
