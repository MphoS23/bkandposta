package com.madassignment.pama.Dashboard

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.madassignment.pama.R

class PlayMusicActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_music)

        videoView = findViewById(R.id.videoView)
        val playVideoButton: Button = findViewById(R.id.playVideoButton)
        val playAudioButton: Button = findViewById(R.id.playAudioButton)

        playVideoButton.setOnClickListener {
            playVideo()
        }

        playAudioButton.setOnClickListener {
            playAudio()
        }
    }

    private fun playVideo() {
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.sample_video))
        videoView.requestFocus()
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            videoView.start()
        }
    }

    private fun playAudio() {
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        mediaPlayer.setOnErrorListener { mp, what, extra ->
            Toast.makeText(this@PlayMusicActivity, "Error playing audio", Toast.LENGTH_SHORT).show()
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}
