package com.lixan.fajardo.tawkentranceexam.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.lixan.fajardo.tawkentranceexam.R
import com.lixan.fajardo.tawkentranceexam.databinding.ActivityMainBinding
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModelActivity

class MainActivity : BaseViewModelActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

}