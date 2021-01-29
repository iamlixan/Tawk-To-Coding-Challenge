package com.lixan.fajardo.tawkentranceexam

import androidx.room.EmptyResultSetException
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.local.Stubs
import com.lixan.fajardo.tawkentranceexam.local.dao.GitUserDao
import com.lixan.fajardo.tawkentranceexam.local.dao.GitUserNoteDao
import com.lixan.fajardo.tawkentranceexam.local.database.AppDatabase
import com.lixan.fajardo.tawkentranceexam.local.implementation.GitUserLocalRepositoryImpl
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUserNote
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.math.exp

class GitUserLocalSourceImplTest {

    private val appDataBase: AppDatabase = mock(AppDatabase::class.java)
    private val gitUserDao: GitUserDao = mock(GitUserDao::class.java)
    private val gitUserNoteDao: GitUserNoteDao = mock(GitUserNoteDao::class.java)

    private val stubGitUSer = Stubs.GIT_USER
    private val stubDBGitUser = Stubs.DB_GIT_USER

    private val stubDBGitUserNote = Stubs.DB_GIT_USER_NOTE
    private val stubGitUserNote = Stubs.GIT_USER_NOTE

    private val stubDBGitUserList = Stubs.DB_GIT_USER_LIST
    private val stubGitUserList = Stubs.GIT_USER_LIST

    private val stubLoginName = Stubs.LOGIN_NAME

    private lateinit var localRepository: GitUserLocalRepository

    @Before
    fun setup() {
        localRepository = GitUserLocalRepositoryImpl(appDataBase)
    }

    @Test
    fun saveGitUser_shouldInsertNewGitUserToDatabase() {
        `when`(appDataBase.gitUserDao()).thenReturn(gitUserDao)

        localRepository
            .saveGitUser(stubGitUSer)
            .test()
            .assertComplete()

        verify(gitUserDao, times(1))
            .insertOrUpdate(DBGitUser.fromDomain(stubGitUSer))
    }

    @Test
    fun saveGitUser_shouldEmitGitUserArgument(){
        val expected = stubGitUSer

        `when`(appDataBase.gitUserDao()).thenReturn(gitUserDao)

        localRepository
            .saveGitUser(stubGitUSer)
            .test()
            .assertComplete()
            .assertValue{
                it == expected
            }
            .assertComplete()
    }

    @Test
    fun saveGitUserNote_shouldInsertNewGitUserNoteToDatabase() {
        `when`(appDataBase.gitUserNotesDao()).thenReturn(gitUserNoteDao)

        localRepository
            .saveGitUserNote(stubGitUserNote)
            .test()
            .assertComplete()

        verify(gitUserNoteDao, times(1))
            .insertOrUpdate(DBGitUserNote.fromDomain(stubGitUserNote))
    }

    @Test
    fun saveGitUserNote_shouldEmitGitUserNoteArgument() {
        val expected = stubGitUserNote
        `when`(appDataBase.gitUserNotesDao()).thenReturn(gitUserNoteDao)

        localRepository
            .saveGitUserNote(stubGitUserNote)
            .test()
            .assertComplete()
            .assertValue {
                it == expected
            }
            .assertComplete()
    }

    @Test
    fun saveGitUserList_shouldInsertNewGitUserListToDatabase() {
        `when`(appDataBase.gitUserDao()).thenReturn(gitUserDao)

        localRepository
            .saveGitUserList(stubGitUserList)
            .test()
            .assertComplete()

        verify(gitUserDao, times(1))
            .insert(DBGitUser.mapListFromDomain(stubGitUserList).toMutableList())
    }

    @Test
    fun saveGitUserList_shouldEmitGitUserListArgument() {
        val expected = stubGitUserList
        `when`(appDataBase.gitUserDao()).thenReturn(gitUserDao)

        localRepository
            .saveGitUserList(stubGitUserList)
            .test()
            .assertComplete()
            .assertValue {
                it == expected
            }
            .assertComplete()
    }

    @Test
    fun getGitUserList_shouldReturnEmptyList() {
        val expected = emptyList<GitUser>()
        `when`(appDataBase.gitUserDao()).thenReturn(gitUserDao)

        `when`(gitUserDao.getGitUsers())
            .thenReturn(Single.error(EmptyResultSetException("Results are empty.")))

        localRepository
            .getGitUsers()
            .test()
            .assertComplete()
            .assertValue {
                it == expected
            }
    }

    @Test
    fun getGitUserList_shouldMapDBGitUserListToGitUserList() {
        val expectation = DBGitUser.mapListToDomain(stubDBGitUserList)

        `when`(appDataBase.gitUserDao()).thenReturn(gitUserDao)

        `when`(gitUserDao.getGitUsers()).thenReturn(Single.just(stubDBGitUserList))

        localRepository
            .getGitUsers()
            .test()
            .assertComplete()
            .assertValue {
                it == expectation
            }
    }

    @Test
    fun getGiUserProfile_shouldMapDBGitUserToGitUser() {
        val expectation = DBGitUser.toDomain(stubDBGitUser)

        `when`(appDataBase.gitUserDao()).thenReturn(gitUserDao)

        `when`(gitUserDao.getGitUserProfile(stubLoginName)).thenReturn(Single.just(stubDBGitUser))

        localRepository
            .getGitUserProfile(stubLoginName)
            .test()
            .assertComplete()
            .assertValue {
                it == expectation
            }
    }
}