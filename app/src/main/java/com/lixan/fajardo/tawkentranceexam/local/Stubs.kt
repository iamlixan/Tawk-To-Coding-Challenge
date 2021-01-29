package com.lixan.fajardo.tawkentranceexam.local

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUserNote

object Stubs {
    val DB_GIT_USER = DBGitUser(
        loginName = "iamlixan",
        id = 47642184,
        nodeId = "MDQ6VXNlcjE=",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        gravatarId = "",
        url = "https://api.github.com/users/iamlixan",
        htmlUrl = "https://github.com/iamlixan",
        followersUrl = "https://api.github.com/users/iamlixan/followers",
        followingUrl = "https://api.github.com/users/iamlixan/following{/other_user}",
        gistsUrl = "https://api.github.com/users/iamlixan/gists{/gist_id}",
        starredUrl = "https://api.github.com/users/iamlixan/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/iamlixan/subscriptions",
        organizationsUrl = "https://api.github.com/users/iamlixan/orgs",
        reposUrl = "https://api.github.com/users/iamlixan/repos",
        eventsUrl = "https://api.github.com/users/iamlixan/events{/privacy}",
        type = "User",
        siteAdmin = false,
        name = "Lixan Fajardo",
        company = "",
        blog = "wwww.lixanfajardo.com",
        location = "Philippines",
        bio = "This is a bio, is it not?",
        twitterUsername = "",
        publicRepos = 3,
        publicGists = 3435,
        followers = 3435,
        following = 3435,
        createdAt = "2019-02-14T18:35:38Z",
        updatedAt = "2021-01-20T14:30:21Z",
        hasNotes = false
    )

    val GIT_USER = GitUser(
        loginName = "iamlixan",
        id = 47642184,
        nodeId = "MDQ6VXNlcjE=",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        gravatarId = "",
        url = "https://api.github.com/users/iamlixan",
        htmlUrl = "https://github.com/iamlixan",
        followersUrl = "https://api.github.com/users/iamlixan/followers",
        followingUrl = "https://api.github.com/users/iamlixan/following{/other_user}",
        gistsUrl = "https://api.github.com/users/iamlixan/gists{/gist_id}",
        starredUrl = "https://api.github.com/users/iamlixan/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/iamlixan/subscriptions",
        organizationsUrl = "https://api.github.com/users/iamlixan/orgs",
        reposUrl = "https://api.github.com/users/iamlixan/repos",
        eventsUrl = "https://api.github.com/users/iamlixan/events{/privacy}",
        type = "User",
        siteAdmin = false,
        name = "Lixan Fajardo",
        company = "",
        blog = "wwww.lixanfajardo.com",
        location = "Philippines",
        bio = "This is a bio, is it not?",
        twitterUsername = "",
        publicRepos = 3,
        publicGists = 3435,
        followers = 3435,
        following = 3435,
        createdAt = "2019-02-14T18:35:38Z",
        updatedAt = "2021-01-20T14:30:21Z",
        hasNotes = false
    )

    val DB_GIT_USER_LIST = listOf(
        DB_GIT_USER,
        DB_GIT_USER,
        DB_GIT_USER,
        DB_GIT_USER,
        DB_GIT_USER
    )

    val GIT_USER_LIST = listOf(
        GIT_USER,
        GIT_USER,
        GIT_USER,
        GIT_USER,
        GIT_USER
    )

    val DB_GIT_USER_NOTE = DBGitUserNote (
        userId = 47642184,
        note = "This is a note, is it not?"
    )

    val GIT_USER_NOTE = GitUserNote(
        userId = 47642184,
        note = "This is a note, is it not?"
    )

    val LOGIN_NAME = "iamlixan"
}