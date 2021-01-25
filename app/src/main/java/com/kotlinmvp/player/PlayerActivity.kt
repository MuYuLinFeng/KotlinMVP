package com.kotlinmvp.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinmvp.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(), IPlayerCallBack {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerPresenter.registerCallback(this)
        initListener()
    }

    private fun initListener() {
        playOrPause.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
        nextMusic.setOnClickListener {
            playerPresenter.playNext()
        }
        lastMusic.setOnClickListener {
            playerPresenter.playLast()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }

    override fun onTitleChange(title: String) {
        musicTitle.text = title
    }

    override fun onProgressChange(current: Int) {
    }

    override fun onPlaying() {
        playOrPause.text = "暂停"
    }

    override fun onPlayerPause() {
        playOrPause.text = "播放"
    }

    override fun onCoverChange(cover: String) {
        musicCover.text = cover
    }
}