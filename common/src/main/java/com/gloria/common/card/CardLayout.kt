package com.gloria.common.card

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.gloria.common.R
import com.gloria.common.network.VideoBean

class CardLayout: LinearLayout {

    private var videoBean: VideoBean? = null
    private var ivThumbnails: ImageView? = null
    private var ivAvatar: ImageView? = null
    private var tvNickname: TextView? = null
    private var tvLikecount: TextView? = null
    private var tvDescription: TextView? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(context, R.layout.card_layout, this)

        ivThumbnails = findViewById(R.id.iv_thumbnails)
        ivAvatar = findViewById(R.id.iv_avatar)
        tvNickname = findViewById(R.id.tv_nickname)
        tvLikecount = findViewById(R.id.tv_likecount)
        tvDescription = findViewById(R.id.tv_description)
    }

    fun setVideoBean(videoBean: VideoBean) {
        this.videoBean = videoBean
        refresh()
    }

    private fun refresh() {
        videoBean?.let {
            tvNickname?.text = it.nickname
            tvLikecount?.text = it.likecount
            tvDescription?.text = it.description

            Utils.loadImageFromUrl(context, it.avatar, ivAvatar!!)
            Utils.loadImageFromUrl(context, it.thumbnails, ivThumbnails!!)
        }
    }
}