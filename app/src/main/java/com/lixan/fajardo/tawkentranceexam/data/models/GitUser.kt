package com.lixan.fajardo.tawkentranceexam.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitUser(
    val loginName: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val gravatarId: String,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val organizationsUrl: String,
    val reposUrl: String,
    val eventsUrl: String,
    val type: String,
    val siteAdmin: Boolean,
    val name: String = "",
    val company: String = "",
    val blog: String = "",
    val location: String = "",
    val bio: String = "",
    val twitterUsername: String = "",
    val publicRepos: Int = 0,
    val publicGists: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val createdAt: String = "",
    val updatedAt: String = "",
    var hasNotes: Boolean = false
): Parcelable {
    companion object {
        fun empty(): GitUser {
            return GitUser (
                "",
                -1,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                false,
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                0,
                0,
                "",
                "",
                false
            )
        }
    }
}