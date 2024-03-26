package com.ferreiro.dragonballapp.ui.common

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiro.dragonballapp.domain.usecase.app.GetUrlSongsUseCase
import com.ferreiro.dragonballapp.domain.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUrlsongsUseCase: GetUrlSongsUseCase
) : ViewModel(){

    private val _urlSongs = MutableSharedFlow<List<String>>()
    val urlSongs: SharedFlow<List<String>> get() = _urlSongs

    var mediaPlayer: MediaPlayer? = null
    private var currentSongIndex = 0
    var isPlaying: Boolean = true

init {
    getSongs()
}

    private fun getSongs(){
        viewModelScope.launch {
            getUrlsongsUseCase().collect { result ->
                when (result) {
                    is Either.Success -> {
                        _urlSongs.emit(result.data)
                    }
                    is Either.Error -> {

                    }
                }
            }
        }
    }

    fun playMusic(songList: List<String>) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(songList[currentSongIndex])
            prepareAsync()
            setOnPreparedListener { it.start() }
            setOnCompletionListener {
                playNextSong(songList)
            }
        }
    }

    fun pauseSong() {
        mediaPlayer?.pause()
        isPlaying = false
    }

    fun playSong() {
        mediaPlayer?.start()
        isPlaying = true
    }

    private fun playNextSong(songList: List<String>) {
        currentSongIndex = (currentSongIndex + 1) % songList.size
        if (currentSongIndex < songList.size) {
            mediaPlayer?.reset()
            mediaPlayer?.setDataSource(songList[currentSongIndex])
            mediaPlayer?.prepareAsync()
        } else {
            currentSongIndex = 0
            mediaPlayer?.reset()
            mediaPlayer?.setDataSource(songList[currentSongIndex])
            mediaPlayer?.prepareAsync()
        }
    }
}
