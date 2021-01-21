package com.lixan.fajardo.tawkentranceexam.local.base.dao

import androidx.room.Dao
import androidx.room.Query
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.local.base.BaseDao
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import io.reactivex.Single

@Dao
abstract class GitUserDao : BaseDao<DBGitUser> {

    @Query("SELECT * FROM ${DBGitUser.DB_GIT_USER_TABLE_NAME}")
    abstract fun getGitUsers(): Single<List<DBGitUser>>
}