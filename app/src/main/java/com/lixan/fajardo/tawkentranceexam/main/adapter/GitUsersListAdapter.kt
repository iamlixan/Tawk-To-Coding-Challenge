package com.lixan.fajardo.tawkentranceexam.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.databinding.ItemGitUserListBinding
import com.lixan.fajardo.tawkentranceexam.ext.loadInvertedUserAvatar
import com.lixan.fajardo.tawkentranceexam.ext.loadUserAvatar
import com.lixan.fajardo.tawkentranceexam.ext.ninjaTap
import com.lixan.fajardo.tawkentranceexam.ext.setVisible
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class GitUsersListAdapter constructor(
    private val listener: GitUsersListListener,
    private val disposables: CompositeDisposable
) : RecyclerView.Adapter<GitUsersListAdapter.ViewHolder>() {

    private val gitUsersList: MutableList<GitUser> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGitUserListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, listener, disposables)
    }

    override fun getItemCount(): Int {
        return this.gitUsersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(gitUsersList[position], position)
    }

    fun setData(gitUserList: List<GitUser>) {
        this.gitUsersList.clear()
        if (this.gitUsersList.isEmpty()) {
            this.gitUsersList.addAll(gitUserList)
            notifyDataSetChanged()
        }
    }

    fun addData(gitUserList: List<GitUser>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return gitUsersList[oldItemPosition] == gitUserList[newItemPosition]
            }

            override fun getOldListSize(): Int {
                return gitUsersList.size
            }

            override fun getNewListSize(): Int {
               return gitUserList.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val (oldID) = gitUsersList[oldItemPosition]
                val (newID) = gitUserList[newItemPosition]
                return oldID == newID
            }

        })
        gitUsersList.addAll(gitUserList)
        notifyDataSetChanged()
    }

    class ViewHolder(
       private val binding: ItemGitUserListBinding,
       private val listener: GitUsersListListener,
       private val disposables: CompositeDisposable
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(gitUser: GitUser, position: Int) {
            binding.gitUser = gitUser

            //position + 1 to get the exact item number from list.
            if ((position + 1) % 4 == 0 && position > 0) {
                binding.ivAvatar.loadInvertedUserAvatar(gitUser.avatarUrl)
            } else {
                binding.ivAvatar.loadUserAvatar(gitUser.avatarUrl)
            }

            binding.clParent.ninjaTap {
                listener.onItemClicked(position, gitUser)
            }.addTo(disposables)

            if(gitUser.notes.isNotBlank()) {
                binding.ivNoteIndicator.setVisible(true)
            }
            binding.executePendingBindings()
        }
    }
}