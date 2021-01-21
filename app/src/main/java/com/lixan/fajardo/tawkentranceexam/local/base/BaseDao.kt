package com.lixan.fajardo.tawkentranceexam.local.base

import androidx.room.*

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserList(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserList(vararg obj: T)

    @Insert
    fun saveUserList(obj: MutableList<T>): List<Long>

    @Update
    fun update(obj: T)

    @Transaction
    fun insertOrUpdate(obj: T) {
        val id = saveUserList(obj)
        if (id == -1L) update(obj)
    }

    @Delete
    fun delete(obj: T)
}