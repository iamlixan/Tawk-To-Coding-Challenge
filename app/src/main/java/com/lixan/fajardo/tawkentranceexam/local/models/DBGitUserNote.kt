package com.lixan.fajardo.tawkentranceexam.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote

@Entity(tableName = DBGitUserNote.DB_GIT_USER_NOTE_TABLE_NAME)
data class DBGitUserNote (
    @PrimaryKey
    @ColumnInfo(name = "id") val userId: Int,
    @ColumnInfo(name = "note") val note: String
) {
    companion object {
        const val DB_GIT_USER_NOTE_TABLE_NAME = "gitusernote"

        fun empty(): DBGitUserNote {
          return DBGitUserNote (
              -1,
              ""
          )
        }

        fun fromDomain(from: GitUserNote): DBGitUserNote {
            return with(from) {
                DBGitUserNote(userId, note)
            }
        }

        fun toDomain(from: DBGitUserNote): GitUserNote {
            return with(from) {
                GitUserNote(userId, note)
            }
        }

        fun mapListFromDomain(from: List<GitUserNote>): List<DBGitUserNote> {
            return with(from) {
                map {
                    fromDomain(it)
                }
            }
        }

        fun mapListToDomain(from: List<DBGitUserNote>): List<GitUserNote> {
            return with(from) {
                map {
                    toDomain(it)
                }
            }
        }
    }
}