package com.lixan.fajardo.tawkentranceexam.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import com.lixan.fajardo.tawkentranceexam.R
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.databinding.ActivityMainBinding
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModelActivity
import com.lixan.fajardo.tawkentranceexam.ext.setVisible
import com.lixan.fajardo.tawkentranceexam.main.adapter.GitUsersListAdapter
import com.lixan.fajardo.tawkentranceexam.main.adapter.GitUsersListListener
import com.lixan.fajardo.tawkentranceexam.utils.NINJA_TAP_THROTTLE_TIME
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : BaseViewModelActivity<ActivityMainBinding, MainViewModel>(), GitUsersListListener {

    override fun getLayoutId(): Int = R.layout.activity_main

    private lateinit var adapter: GitUsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
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

        viewModel.getGitUsers()
    }

    private fun setupView() {
        binding.tvSearch
            .textChanges()
            .skipInitialValue()
            .debounce(NINJA_TAP_THROTTLE_TIME, TimeUnit.MILLISECONDS)
            .observeOn(schedulers.ui())
            .subscribeBy(
                onNext = {
                    Toast.makeText(this, "ENTERED: $it", Toast.LENGTH_SHORT).show()
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)

        adapter = GitUsersListAdapter(this, disposables)
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        binding.rvUserList.adapter = adapter

        binding.rvUserList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(SCROLL_DIRECTION) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getGitUsers(false)
                }
            }
        })
    }

    private fun handleStates(state: MainState) {
        when(state) {
            is MainState.SetData -> {
                adapter.setData(state.gitUsers)
            }
            is MainState.AddData -> {
                adapter.addData(state.gitUsers)
            }
            is MainState.ShowProgressLoading -> {
                binding.progressLayout.setVisible(true)
            }
            is MainState.HideProgressLoading -> {
                binding.progressLayout.setVisible(false)
            }
            is MainState.Empty -> {
                Toast.makeText(this, "EMPTY", Toast.LENGTH_LONG).show()
            }
            is MainState.Error -> {
                Timber.e("ERROR! : ${state.message}")
                Toast.makeText(this, "ERROR! : ${state.message}", Toast.LENGTH_LONG).show()
//                binding.tv.text = "ERROR! : ${state.message}"
            }
        }
    }

    override fun onItemClicked(position: Int, gitUser: GitUser) {
        Toast.makeText(this, "CLICKED: $position", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val SCROLL_DIRECTION = 1
    }
}