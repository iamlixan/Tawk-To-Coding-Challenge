package com.lixan.fajardo.tawkentranceexam.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    @ColumnInfo(name = "notes") val notes: String
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
                ""
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
                    notes = notes
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
                    notes = notes
                )
            }
        }
    }
}

