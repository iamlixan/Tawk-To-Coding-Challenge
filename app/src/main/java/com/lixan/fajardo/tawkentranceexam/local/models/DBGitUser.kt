package com.lixan.fajardo.tawkentranceexam.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser

@Entity(tableName = DBGitUser.DB_GIT_USER_TABLE_NAME)
data class DBGitUser(
    @ColumnInfo(name = "login")val loginName: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "node_id") val nodeId: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    @ColumnInfo(name = "gravatar_id") val gravatarId: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "html_url") val htmlUrl: String,
    @ColumnInfo(name = "followers_url") val followersUrl: String,
    @ColumnInfo(name = "following_url") val followingUrl: String,
    @ColumnInfo(name = "gists_url") val gistsUrl: String,
    @ColumnInfo(name = "starred_url") val starredUrl: String,
    @ColumnInfo(name = "subscriptions_url") val subscriptionsUrl: String,
    @ColumnInfo(name = "organizations_url") val organizationsUrl: String,
    @ColumnInfo(name = "repos_url") val reposUrl: String,
    @ColumnInfo(name = "events_url") val eventsUrl: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "site_admin") val siteAdmin: Boolean,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "blog") val blog: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "bio") val bio: String,
    @ColumnInfo(name = "twitter_username") val twitterUsername: String,
    @ColumnInfo(name = "public_repos") val publicRepos: Int,
    @ColumnInfo(name = "public_gists") val publicGists: Int,
    @ColumnInfo(name = "followers") val followers: Int,
    @ColumnInfo(name = "following") val following: Int,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "has_notes") val hasNotes: Boolean
) {
    companion object {
        const val DB_GIT_USER_TABLE_NAME = "gitusers"

        fun empty(): DBGitUser {
            return DBGitUser(
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

        fun fromDomain(gitUser: GitUser): DBGitUser {
            return with(gitUser) {
                DBGitUser(
                    loginName = loginName,
                    id = id,
                    nodeId = nodeId,
                    avatarUrl = avatarUrl,
                    gravatarId = gravatarId,
                    url = url,
                    htmlUrl = htmlUrl,
                    followersUrl = followersUrl,
                    followingUrl = followingUrl,
                    gistsUrl = gistsUrl,
                    starredUrl = starredUrl,
                    subscriptionsUrl = subscriptionsUrl,
                    organizationsUrl = organizationsUrl,
                    reposUrl = reposUrl,
                    eventsUrl = eventsUrl,
                    type = type,
                    siteAdmin = siteAdmin,
                    name = name,
                    company = company,
                    blog = blog,
                    location = location,
                    bio = bio,
                    twitterUsername = twitterUsername,
                    publicRepos = publicRepos,
                    publicGists = publicGists,
                    followers = followers,
                    following = following,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    hasNotes = hasNotes
                )
            }
        }

        fun mapListFromDomain(gitUserList: List<GitUser>): List<DBGitUser> {
            return with(gitUserList) {
                map {
                    fromDomain(it)
                }
            }
        }

        fun mapListToDomain(gitUserList: List<DBGitUser>): List<GitUser> {
            return with(gitUserList) {
                map {
                    toDomain(it)
                }
            }
        }

        fun toDomain(gitUserDB: DBGitUser): GitUser {
            return with(gitUserDB) {
                GitUser(
                    loginName = loginName,
                    id = id,
                    nodeId = nodeId,
                    avatarUrl = avatarUrl,
                    gravatarId = gravatarId,
                    url = url,
                    htmlUrl = htmlUrl,
                    followersUrl = followersUrl,
                    followingUrl = followingUrl,
                    gistsUrl = gistsUrl,
                    starredUrl = starredUrl,
                    subscriptionsUrl = subscriptionsUrl,
                    organizationsUrl = organizationsUrl,
                    reposUrl = reposUrl,
                    eventsUrl = eventsUrl,
                    type = type,
                    siteAdmin = siteAdmin,
                    name = name,
                    company = company,
                    blog = blog,
                    location = location,
                    bio = bio,
                    twitterUsername = twitterUsername,
                    publicRepos = publicRepos,
                    publicGists = publicGists,
                    followers = followers,
                    following = following,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    hasNotes = hasNotes
                )
            }
        }
    }
}

