package com.lixan.fajardo.tawkentranceexam.local.database

import android.content.Context
import androidx.room.*
import com.lixan.fajardo.tawkentranceexam.local.GitUserDBTypeConverter
import com.lixan.fajardo.tawkentranceexam.local.dao.GitUserDao
import com.lixan.fajardo.tawkentranceexam.local.dao.GitUserNoteDao
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUserNote


@Database(
    entities = [
    DBGitUser::class,
    DBGitUserNote::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(GitUserDBTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gitUserDao(): GitUserDao

    abstract fun gitUserNotesDao(): GitUserNoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            val dbName = "tawktocodingchallenge.db"

            val builder = Room.databaseBuilder(context, AppDatabase::class.java, dbName)
                .fallbackToDestructiveMigration()

            return builder.build()
        }
    }
}