package com.gloria.gloriavideo

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.VideoView

class DetailActivity : AppCompatActivity() {

    companion object{
        val TAG = "DetailActivity"
    }

    private var videoUrl = ""
    private var videoView: VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        videoUrl = intent.getStringExtra("url").toString()
        initView()
    }

    private fun initView() {
        videoView = findViewById(R.id.video_view)

        playVideo()
    }

    private fun playVideo() {
        val uri = Uri.parse(videoUrl)
        videoView?.setVideoURI(uri)
        videoView?.start()
        videoView?.setOnPreparedListener {
            Log.d(TAG, "video prepared")
        }
        videoView?.setOnErrorListener(object : MediaPlayer.OnErrorListener{
            override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
                Log.d(TAG, "video error")
                return false
            }

        })
    }
}