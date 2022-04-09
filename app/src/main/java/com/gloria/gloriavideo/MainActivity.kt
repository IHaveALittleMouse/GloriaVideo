package com.gloria.gloriavideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.gloria.common.network.VideoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var btnGetData: Button? = null
    private var scope: CoroutineScope? = null

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
        btnGetData = findViewById(R.id.btn_get_data)
    }

    private fun initAction() {
        btnGetData?.setOnClickListener { getVideoData() }
    }

    private fun getVideoData() {
        scope = MainScope()
        scope?.launch {
            try {
                val result = VideoApi.createVideoApi().requestData()
                Log.d(TAG, result.toString())
            }catch (e: Exception){
                e.printStackTrace()
                Log.e(TAG, e.toString())
            }
        }
    }
}