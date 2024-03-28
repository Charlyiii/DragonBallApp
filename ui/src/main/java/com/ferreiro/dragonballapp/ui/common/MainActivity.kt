package com.ferreiro.dragonballapp.ui.common

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.ui.R
import com.example.ui.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        val musicControlButton = binding.musicControlButton

        setupAppBarsDesign(toolbar)

        // Observa los cambios en la lista de URLs de canciones
        viewModel.urlSongs.onEach { urls ->
            if (urls.isNotEmpty()) {
                viewModel.playMusic(urls)
            }
        }.launchIn(lifecycleScope)

        // Establece el listener de clics para el botón de control de música
        musicControlButton.setOnClickListener {
            if (viewModel.isPlaying) {
                Log.d("Prueba", "Pause song")
                viewModel.pauseSong()
            } else {
                Log.d("Prueba", "Play song")
                viewModel.playSong()
            }
            updateMusicControlButtonIcon(musicControlButton)
        }
    }


    private fun updateMusicControlButtonIcon(musicControlButton: ImageButton?) {
        val iconResource = if (viewModel.isPlaying) {
            R.drawable.baseline_music_note_20
        } else {
            R.drawable.baseline_music_off_20
        }
        musicControlButton?.setImageResource(iconResource)
    }

    private fun setupAppBarsDesign(toolbar: MaterialToolbar) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.orange_db)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.orange_db)
        setSupportActionBar(toolbar)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.mediaPlayer?.release()
    }
}
