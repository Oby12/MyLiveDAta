package com.learn.mylivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MainViewModel : ViewModel() {

    //menjalankn timer di konstrcutor
    companion object {
        private const val ONE_SECOND = 1000
    }

    private val mInitialTime = SystemClock.elapsedRealtime()
    //MutableLiveData bisa kita ubah value-nya
    private val mElapsedTime = MutableLiveData<Long?>()

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    //tambahkan obyek LiveData yang nantinya akan di-subscribe oleh MainActivity.

    fun getElapsedTime(): LiveData<Long?> {  //<-sedangkan LiveData bersifat Read-Only.
        return mElapsedTime
    }
}