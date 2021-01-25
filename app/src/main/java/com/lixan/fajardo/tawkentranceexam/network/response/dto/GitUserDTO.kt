package com.lixan.fajardo.tawkentranceexam.network.response.dto

import com.google.gson.annotations.SerializedName
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.network.response.GitUsersResponseData
import com.lixan.fajardo.tawkentranceexam.utils.KEY_GIT_USER_DATA

data class GitUserDTO (
    @SerializedName("login") val loginName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
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
    @SerializedName("site_admin") val siteAdmin: Boolean,
    @SerializedName("name") val name: String? = "",
    @SerializedName("company") val company: String? = "",
    @SerializedName("blog") val blog: String? = "",
    @SerializedName("location") val location: String? = "",
    @SerializedName("bio") val bio: String? = "",
    @SerializedName("twitter_username") val twitterUsername: String? = "",
    @SerializedName("public_repos") val publicRepos: Int? = 0,
    @SerializedName("public_gists") val publicGists: Int? = 0,
    @SerializedName("followers") val followers: Int? = 0,
    @SerializedName("following") val following: Int? = 0,
    @SerializedName("created_at") val createdAt: String? = "",
    @SerializedName("updated_at") val updatedAt: String? = ""
) {
    companion object {
        fun mapGitUserResponse(from: GitUsersResponseData): Map<String, Any> {
            val map = mutableMapOf<String, Any>()

            map[KEY_GIT_USER_DATA] = from.gitUsersList.map {
                mapGitUser(it)
            }

            return map
        }

        fun mapGitUser(from: GitUserDTO): GitUser {
            return GitUser(
                loginName = from.loginName,
                id = from.id,
                nodeId = from.nodeId,
                avatarUrl = from.avatarUrl,
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
                siteAdmin = from.siteAdmin,
                name = from.name.orEmpty(),
                company = from.company.orEmpty(),
                blog = from.blog.orEmpty(),
                location = from.location.orEmpty(),
                bio = from.bio.orEmpty(),
                twitterUsername = from.twitterUsername.orEmpty(),
                publicRepos = from.publicRepos ?: 0,
                publicGists = from.publicGists ?: 0,
                followers = from.followers ?: 0,
                following = from.following ?: 0,
                createdAt = from.createdAt.orEmpty(),
                updatedAt = from.updatedAt.orEmpty()
            )
        }
    }
}