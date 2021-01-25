package com.lixan.fajardo.tawkentranceexam.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.lixan.fajardo.tawkentranceexam.R
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.databinding.ActivityUserProfileBinding
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModelActivity
import com.lixan.fajardo.tawkentranceexam.ext.gone
import com.lixan.fajardo.tawkentranceexam.ext.loadUserAvatar
import com.lixan.fajardo.tawkentranceexam.ext.ninjaTap
import com.lixan.fajardo.tawkentranceexam.utils.NINJA_TAP_THROTTLE_TIME
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import java.util.concurrent.TimeUnit

class GitUserProfileActivity: BaseViewModelActivity<ActivityUserProfileBinding, GitUserProfileViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_user_profile

    private var username: String = ""

    companion object {
        private const val KEY_USERNAME = "KEY_USERNAME"
        fun openActivity(context: Context, username: String) {
            val intent = Intent(context, GitUserProfileActivity::class.java)
            intent.putExtra(KEY_USERNAME, username)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            username = it.getStringExtra(KEY_USERNAME).orEmpty()
        }

        setupVMObserver()
        setupViews()
    }

    private fun setupViews() {
        binding.btnBack.ninjaTap {
            onBackPressed()
        }.addTo(disposables)
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

        viewModel.getUserProfile(username)
    }

    private fun handleStates(state: GitUserProfileState) {
        when(state) {
            is GitUserProfileState.Success -> {
                applyDataToViews(state.gitUserProfile)
            }
            is GitUserProfileState.Error -> {
                showErrorSnackbar(state.errorMessage)
            }
        }
    }

    private fun applyDataToViews(gitUser: GitUser) {
        binding.gitUserProfile = gitUser
        binding.ivAvatar.loadUserAvatar(gitUser.avatarUrl)

        if (gitUser.name.isBlank()) {
            binding.tvName.gone()
        }

        if (gitUser.company.isBlank()) {
            binding.tvCompany.gone()
        }

        if (gitUser.blog.isBlank()) {
            binding.tvBlog.gone()
        }

        if (gitUser.location.isBlank()) {
            binding.tvLocation.gone()
        }

        if (gitUser.bio.isBlank()) {
            binding.tvBio.gone()
        }

        if (gitUser.twitterUsername.isBlank()) {
            binding.tvTwitterUsername.gone()
        }

    }

}