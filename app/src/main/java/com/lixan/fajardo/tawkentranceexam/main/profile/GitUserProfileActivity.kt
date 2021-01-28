package com.lixan.fajardo.tawkentranceexam.main.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.lixan.fajardo.tawkentranceexam.R
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote
import com.lixan.fajardo.tawkentranceexam.databinding.ActivityUserProfileBinding
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModelActivity
import com.lixan.fajardo.tawkentranceexam.ext.gone
import com.lixan.fajardo.tawkentranceexam.ext.loadUserAvatar
import com.lixan.fajardo.tawkentranceexam.ext.ninjaTap
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class GitUserProfileActivity: BaseViewModelActivity<ActivityUserProfileBinding, GitUserProfileViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_user_profile

    private var username: String = ""

    companion object {
        private const val KEY_USERNAME = "KEY_USERNAME"
        fun openActivity(context: Context, username: String, avatarView: View, usernameView: View) {
            val intent = Intent(context, GitUserProfileActivity::class.java)
            intent.putExtra(KEY_USERNAME, username)

            val p1 = Pair.create(avatarView, "avatarTransition")
            val p2 = Pair.create(usernameView, "usernameTransition")

            val activityCompatOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, p1, p2)
            context.startActivity(intent, activityCompatOptions.toBundle())
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

        binding.btnSave.ninjaTap {
            viewModel.saveGitUserNote(
                viewModel.gitUserProfile.id,
                binding.etNotes.text.toString()
            )
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
            is GitUserProfileState.SaveNoteSuccess -> {
                showSuccessSnackbar(
                    getString(R.string.message_save_note_success)
                )
            }
            is GitUserProfileState.ShowLoadingLayout -> {
                binding.loadingLayout.visibility = View.VISIBLE
                binding.nsvParent.visibility = View.GONE
            }
            is GitUserProfileState.HideLoadingLayout -> {
                binding.nsvParent.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
            }
            is GitUserProfileState.Error -> {
                showErrorSnackbar(state.errorMessage)
            }
        }
    }

    private fun applyDataToViews(gitUser: Pair<GitUser, GitUserNote>) {
        binding.gitUserProfile = gitUser.first
        binding.gitUserNote = gitUser.second
        gitUser.first?.let {
            binding.ivAvatar.loadUserAvatar(it.avatarUrl)
        }


        if (gitUser.first?.name.isNullOrBlank()) {
            binding.tvName.gone()
        }

        if (gitUser.first?.company.isNullOrBlank()) {
            binding.tvCompany.gone()
        }

        if (gitUser.first?.blog.isNullOrBlank()) {
            binding.tvBlog.gone()
        }

        if (gitUser.first?.location.isNullOrBlank()) {
            binding.tvLocation.gone()
        }

        if (gitUser.first?.bio.isNullOrBlank()) {
            binding.tvBio.gone()
        }

        if (gitUser.first?.twitterUsername.isNullOrBlank()) {
            binding.tvTwitterUsername.gone()
        }

    }

}