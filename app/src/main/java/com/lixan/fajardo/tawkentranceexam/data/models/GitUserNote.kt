package com.lixan.fajardo.tawkentranceexam.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitUserNote(
    val userId: Int,
    val note: String
): Parcelable {
    companion object {
        fun empty(): GitUserNote {
            return GitUserNote(
                -1,
                ""
            )
        }
    }
}