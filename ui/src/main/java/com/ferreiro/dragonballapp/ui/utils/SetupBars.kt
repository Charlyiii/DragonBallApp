package com.ferreiro.dragonballapp.ui.utils

import androidx.fragment.app.FragmentActivity
import com.example.ui.R
import com.ferreiro.dragonballapp.ui.common.MainActivity
import com.google.android.material.appbar.MaterialToolbar

fun setupTopAppBar(titleOfScreen: String, withBackButton: Boolean, activity: FragmentActivity) {
    (activity as MainActivity).findViewById<MaterialToolbar>(R.id.toolbar).apply {
        title = titleOfScreen

        if (withBackButton) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                activity.onBackPressed()
            }
        } else {
            navigationIcon = null
            menu.clear()
        }
    }
}

fun hideBottomAppBar(activity: FragmentActivity) {
    (activity as? MainActivity)?.setupBottomAppBar(isVisible = false)
}

fun showBottomAppBar(activity: FragmentActivity) {
    (activity as? MainActivity)?.setupBottomAppBar(isVisible = true)

}

fun hideTopAppBar(activity: FragmentActivity) {
    (activity as? MainActivity)?.setupTopAppBar(isVisible = false)
}

fun showTopAppBar(activity: FragmentActivity) {
    (activity as? MainActivity)?.setupTopAppBar(isVisible = true)
}
