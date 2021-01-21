package com.lixan.fajardo.tawkentranceexam.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser

class GitUserDBTypeConverter {

    @TypeConverter
    fun saveFromList(gitUserList: List<GitUser>): String {
        return Gson().toJson(gitUserList)
    }


}