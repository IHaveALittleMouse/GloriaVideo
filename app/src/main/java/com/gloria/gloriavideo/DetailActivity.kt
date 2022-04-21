package com.gloria.gloriavideo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout

class DetailActivity : AppCompatActivity() {

    companion object{
        val TAG = "DetailActivity"
    }

    private var videoUrl = ""
    private var videoView: VideoView? = null
    private var playPauseLayout: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        videoUrl = intent.getStringExtra("url").toString()
        initView()
    }

    private fun initView() {
        videoView = findViewById(R.id.video_view)
        playPauseLayout = findViewById(R.id.view_play_pause)
        playPauseLayout?.visibility = View.INVISIBLE
        playVideo()
    }

    private fun playVideo() {
        val uri = Uri.parse(videoUrl)
        videoView?.setVideoURI(uri)
        videoView?.start()
        videoView?.setOnPreparedListener {
            Log.d(TAG, "video prepared")
        }
        videoView?.setOnErrorListener { _, _, _ ->
            Log.d(TAG, "video error")
            false
        }
        videoView?.setOnCompletionListener {
            if (!videoView?.isPlaying!!){
                videoView?.start()
            }
        }
        videoView?.setOnClickListener {
            if (videoView?.isPlaying!!) {
                videoView?.pause()
                playPauseLayout?.visibility = View.VISIBLE
                playPauseLayout?.bringToFront()
            } else if (!videoView?.isPlaying!!){
                videoView?.start()
                playPauseLayout?.visibility = View.INVISIBLE
            }
        }
    }
}