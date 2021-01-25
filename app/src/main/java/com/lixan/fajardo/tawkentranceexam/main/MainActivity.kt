package com.lixan.fajardo.tawkentranceexam.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
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
import com.lixan.fajardo.tawkentranceexam.main.profile.GitUserProfileActivity
import com.lixan.fajardo.tawkentranceexam.utils.NINJA_TAP_THROTTLE_TIME
import com.novoda.merlin.Merlin
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : BaseViewModelActivity<ActivityMainBinding, MainViewModel>(), GitUsersListListener {

    override fun getLayoutId(): Int = R.layout.activity_main

    private lateinit var adapter: GitUsersListAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private var rvState: Parcelable? = null

    private var gitUserList = arrayListOf<GitUser>()

    private lateinit var merlin: Merlin

    private var showInternetConnectedStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupVMObserver()
        setupMerlin()
    }

    override fun onPause() {
        merlin.unbind()
        super.onPause()
    }

    override fun onResume() {
        merlin.bind()
        super.onResume()
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
    }

    private fun setupMerlin(){
        merlin = Merlin.Builder()
            .withConnectableCallbacks()
            .withDisconnectableCallbacks()
            .build(this)

        merlin.registerConnectable {
            if (showInternetConnectedStatus) {
                viewModel.getGitUsers(true)
                showSuccessSnackbar(getString(R.string.message_internet_connected))
            }

            showInternetConnectedStatus = true
        }

        merlin.registerDisconnectable {
            showErrorSnackbar(getString(R.string.error_internet_disconnected))
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        rvState = binding.rvUserList.layoutManager?.onSaveInstanceState()
        outState.putParcelable(KEY_RECYCLERVIEW_STATE, rvState)
        outState.putParcelableArrayList(KEY_RECYCLERVIEW_ITEMS, gitUserList)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        rvState = savedInstanceState.getParcelable(KEY_RECYCLERVIEW_STATE)
        if (gitUserList.isNotEmpty()) {
            gitUserList.clear()
            gitUserList.addAll(
                savedInstanceState.getParcelableArrayList(KEY_RECYCLERVIEW_ITEMS) ?: emptyList()
            )
        }

        adapter.setData(gitUserList)
        binding.rvUserList.layoutManager?.onRestoreInstanceState(rvState)
    }

    private fun setupView() {
        binding.etSearch
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

        if (!this::adapter.isInitialized){
            adapter = GitUsersListAdapter(this, disposables)

        }
        if (!this::linearLayoutManager.isInitialized) {
            linearLayoutManager = LinearLayoutManager(this)
        }

        binding.rvUserList.layoutManager = linearLayoutManager
        binding.rvUserList.adapter = adapter
        binding.rvUserList.addItemDecoration(DividerItemDecoration(this, linearLayoutManager.orientation))

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
                gitUserList.clear()
                gitUserList.addAll(state.gitUsers)
                adapter.setData(state.gitUsers)
            }
            is MainState.AddData -> {
                gitUserList.addAll(state.gitUsers)
                adapter.addData(state.gitUsers)
            }
            is MainState.ShowProgressLoading -> {
                binding.progressLayout.setVisible(true)
            }
            is MainState.HideProgressLoading -> {
                binding.progressLayout.setVisible(false)
            }
            is MainState.RemoteEmpty -> {
                Toast.makeText(this, "REMOTE EMPTY", Toast.LENGTH_SHORT).show()
            }
            is MainState.LocalEmpty -> {
                Toast.makeText(this, "LOCAL EMPTY", Toast.LENGTH_SHORT).show()
            }
            is MainState.NoInternetError -> {
                showErrorSnackbar(state.message)
            }
            is MainState.Error -> {
                Timber.e("ERROR! : ${state.message}")
            }
        }
    }

    override fun onItemClicked(
        position: Int,
        gitUser: GitUser,
        avatarView: View,
        usernameView: View
    ) {
        GitUserProfileActivity.openActivity(this, gitUser.loginName, avatarView, usernameView)
    }

    companion object {
        const val SCROLL_DIRECTION = 1
        const val KEY_RECYCLERVIEW_STATE = "KEY_RECYCLERVIEW_STATE"
        const val KEY_RECYCLERVIEW_ITEMS = "KEY_RECYCLERVIEW_ITEMS"
    }
}