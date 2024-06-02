package com.ferreiro.dragonballapp.ui.screens.characters.list.menu

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import com.example.ui.R

class FilterOrderMenuProvider(
    private val context: Context,
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
    private val availableGenders: List<String> = emptyList()
) : MenuProvider {

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_filter_order, menu)
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
                    // Manejar la acción al hacer clic en un elemento del submenu
                    onClickAction(item)
                    true
                }
            }
        }
    }
}
