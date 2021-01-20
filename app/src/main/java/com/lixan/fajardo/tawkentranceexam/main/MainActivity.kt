package com.lixan.fajardo.tawkentranceexam.main

import android.os.Bundle
import android.widget.Toast
import com.lixan.fajardo.tawkentranceexam.R
import com.lixan.fajardo.tawkentranceexam.databinding.ActivityMainBinding
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModelActivity
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MainActivity : BaseViewModelActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupVMObserver()
    }

    private fun setupVMObserver() {
        viewModel.state
            .observeOn(schedulers.ui())
            .subscribeBy(
                onNext = {
                    handleStates(it)
                },
                onError = {
                    Timber.e(it)
                }
            )
            .addTo(disposables)

        viewModel.getGitUsers(0)
    }

    private fun handleStates(state: MainState) {
        when(state) {
            is MainState.Success -> {
                Timber.d("SUCCESS")
                binding.tv.text = "SUCCESS"
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
            }
            is MainState.Empty -> {
                Timber.d("EMPTY")
                binding.tv.text = "EMPTY"
                Toast.makeText(this, "EMPTY", Toast.LENGTH_LONG).show()
            }
            is MainState.Error -> {
                Timber.e("ERROR! : ${state.message}")
                Toast.makeText(this, "ERROR! : ${state.message}", Toast.LENGTH_LONG).show()
                binding.tv.text = "ERROR! : ${state.message}"
            }
        }
    }

}