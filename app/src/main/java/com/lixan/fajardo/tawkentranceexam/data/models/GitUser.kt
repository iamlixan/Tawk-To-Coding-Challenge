package com.lixan.fajardo.tawkentranceexam.data.models

import android.os.Parcelable
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
    var notes: String = ""
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
                ""
            )
        }
    }
}