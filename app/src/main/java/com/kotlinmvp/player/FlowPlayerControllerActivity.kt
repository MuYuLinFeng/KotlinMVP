package com.kotlinmvp.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinmvp.R
import kotlinx.android.synthetic.main.activity_flow_player_controller.*

class FlowPlayerControllerActivity : AppCompatActivity(), IPlayerCallBack {
    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player_controller)
        playerPresenter.registerCallback(this)
        initListener()
    }

    private fun initListener() {
        flowPlayOrPause.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }

    override fun onTitleChange(title: String) {
    }

    override fun onProgressChange(current: Int) {
    }

    override fun onPlaying() {
        flowPlayOrPause.text = "暂停"
    }

    override fun onPlayerPause() {
        flowPlayOrPause.text = "播放"
    }

    override fun onCoverChange(cover: String) {
    }
}