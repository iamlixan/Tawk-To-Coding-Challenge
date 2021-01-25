package com.lixan.fajardo.tawkentranceexam.main.adapter

import android.view.View
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser

interface GitUsersListListener {

    fun onItemClicked(position: Int, gitUser: GitUser, avatarView: View, usernameView: View)
}