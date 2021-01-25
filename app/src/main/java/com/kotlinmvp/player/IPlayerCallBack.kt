package com.kotlinmvp.player

interface IPlayerCallBack {
    fun onTitleChange(title: String)
    fun onProgressChange(current: Int)
    fun onPlaying()
    fun onPlayerPause()
    fun onCoverChange(cover: String)
}