package com.lixan.fajardo.tawkentranceexam.network.response.dto

import com.google.gson.annotations.SerializedName
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.network.response.GitUsersResponseData
import com.lixan.fajardo.tawkentranceexam.utils.KEY_GIT_USER_DATA

data class GitUserDTO (
    @SerializedName("login") val loginName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarId: String,
    @SerializedName("gravatar_id") val gravatarId: String,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("followers_url") val followersUrl: String,
    @SerializedName("following_url") val followingUrl: String,
    @SerializedName("gists_url") val gistsUrl: String,
    @SerializedName("starred_url") val starredUrl: String,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String,
    @SerializedName("organizations_url") val organizationsUrl: String,
    @SerializedName("repos_url") val reposUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("type") val type: String,
    @SerializedName("site_admin") val siteAdmin: Boolean
) {
    companion object {
        fun mapGitUserResponse(from: GitUsersResponseData): Map<String, Any> {
            val map = mutableMapOf<String, Any>()

            map[KEY_GIT_USER_DATA] = from.gitUsersList.map {
                mapGitUser(it)
            }

            return map
        }

        private fun mapGitUser(from: GitUserDTO): GitUser {
            return GitUser(
                loginName = from.loginName,
                id = from.id,
                nodeId = from.nodeId,
                avatarId = from.avatarId,
                gravatarId = from.gravatarId,
                url = from.url,
                htmlUrl = from.htmlUrl,
                followersUrl = from.followersUrl,
                followingUrl = from.followingUrl,
                gistsUrl = from.gistsUrl,
                starredUrl = from.starredUrl,
                subscriptionsUrl = from.subscriptionsUrl,
                organizationsUrl = from.organizationsUrl,
                reposUrl = from.reposUrl,
                eventsUrl = from.eventsUrl,
                type = from.type,
                siteAdmin = from.siteAdmin
            )
        }
    }
}