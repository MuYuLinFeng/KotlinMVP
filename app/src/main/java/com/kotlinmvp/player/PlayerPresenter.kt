package com.kotlinmvp.player

/**
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 * =================
 * 播放的状态
 * - 通知UI改变成播放状态
 * - 通知UI进度的变化
 * 上一首，下一首
 * - 通知UI歌曲标题变化
 * - 通知UI歌曲封面变化
 * 暂停音乐
 * - 更新UI状态为暂停
 */
class PlayerPresenter private constructor(){
    companion object{
        val instance by lazy {
            PlayerPresenter()
        }
    }
    private val callbackList = arrayListOf<IPlayerCallBack>()

    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING
    }

    private var currentPlayState = PlayState.NONE

    fun registerCallback(callBack: IPlayerCallBack) {
        if (!callbackList.contains(callBack)) {
            callbackList.add(callBack)
        }
    }

    fun unRegisterCallback(callBack: IPlayerCallBack) {
        if (!callbackList.contains(callBack)) {
            callbackList.add(callBack)
        }
    }

    fun doPlayOrPause() {
        dispatchTitleChange("当前")
        dispatchCoverChange("当前封面")
        if (currentPlayState == PlayState.PLAYING) {
            currentPlayState = PlayState.PAUSE
            dispatchPauseState()
        } else {
            currentPlayState = PlayState.PLAYING
            dispatchPlayingState()
        }
    }

    private fun dispatchPauseState() {
        callbackList.forEach {
            it.onPlayerPause()
        }
    }

    private fun dispatchPlayingState() {
        callbackList.forEach {
            it.onPlaying()
        }
    }

    fun playLast() {
        dispatchTitleChange("上一首")
        dispatchCoverChange("上一首封面")
        currentPlayState = PlayState.PLAYING
    }

    fun playNext() {
        dispatchTitleChange("下一首")
        dispatchCoverChange("下一首封面")
        currentPlayState = PlayState.PLAYING

    }

    private fun dispatchCoverChange(cover: String) {
        callbackList.forEach {
            it.onCoverChange(cover)
        }
    }

    private fun dispatchTitleChange(title: String) {
        callbackList.forEach {
            it.onTitleChange(title)
        }
    }
}