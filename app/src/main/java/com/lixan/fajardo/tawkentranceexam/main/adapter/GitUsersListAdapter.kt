package com.lixan.fajardo.tawkentranceexam.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.databinding.ItemGitUserListBinding
import com.lixan.fajardo.tawkentranceexam.ext.loadUserAvatar
import com.lixan.fajardo.tawkentranceexam.ext.ninjaTap
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class GitUsersListAdapter constructor(
    private val listener: GitUsersListListener,
    private val disposables: CompositeDisposable
) : RecyclerView.Adapter<GitUsersListAdapter.ViewHolder>() {

    private val gitUserList: MutableList<GitUser> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGitUserListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, listener, disposables)
    }

    override fun getItemCount(): Int {
        return this.gitUserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(gitUserList[position], position)
    }

    fun setData(gitUserList: List<GitUser>) {
        this.gitUserList.clear()
        if (this.gitUserList.isEmpty()) {
            this.gitUserList.addAll(gitUserList)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
       private val binding: ItemGitUserListBinding,
       private val listener: GitUsersListListener,
       private val disposables: CompositeDisposable
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(gitUser: GitUser, position: Int) {
            binding.gitUser = gitUser
            binding.ivAvatar.loadUserAvatar(gitUser.avatarUrl)

            binding.clParent.ninjaTap {
                listener.onItemClicked(position, gitUser)
            }.addTo(disposables)
            binding.executePendingBindings()
        }
    }
}