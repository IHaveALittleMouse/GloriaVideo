package com.gloria.gloriavideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gloria.common.card.CardLayout
import com.gloria.common.network.VideoApi
import com.gloria.common.network.VideoBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var card0: CardLayout? = null
    private var scope: CoroutineScope? = null
    private var videoList: List<VideoBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initAction()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope?.cancel()
    }

    private fun initView() {
        card0 = findViewById(R.id.cl_test)
    }

    private fun initAction() {
        getVideoData()
    }

    private fun getVideoData() {
        scope = MainScope()
        scope?.launch {
            try {
                videoList = VideoApi.createVideoApi().requestData()
                Log.d(TAG, videoList.toString())
                setVideoData()
            }catch (e: Exception){
                e.printStackTrace()
                Log.e(TAG, e.toString())
            }
        }
    }

    private fun setVideoData() {
        videoList?.get(0)?.let { card0?.setVideoBean(it) }
    }
}