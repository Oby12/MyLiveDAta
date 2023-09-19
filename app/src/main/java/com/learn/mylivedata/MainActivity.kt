package com.learn.mylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.learn.mylivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var liveDataTimerViewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        liveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?> { aLong ->
            //aLong akan selalu diperbarui secara realtime sesuai dengan perubahan yang ada di kelas ViewModel. Namun jika elapsedTimeObserver tidak dipanggil saat melakukan observe
            //getElapsedTime() maka nilai aLong juga tidak akan ada perubahan.
            val newText = this@MainActivity.resources.getString(R.string.seconds, aLong)
            activityMainBinding.timerTextview.text = newText
        }
        //Jadi cara mendapatkan value dari sebuah LiveData harus dilakukan dengan cara meng-observe LiveData tersebut. Dan proses ini dilakukan secara asynchronous.
        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}