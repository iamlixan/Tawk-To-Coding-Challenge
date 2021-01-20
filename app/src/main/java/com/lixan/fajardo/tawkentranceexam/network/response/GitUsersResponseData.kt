package com.lixan.fajardo.tawkentranceexam.network.response

import com.lixan.fajardo.tawkentranceexam.network.response.base.BaseResponse
import com.lixan.fajardo.tawkentranceexam.network.response.dto.GitUserDTO

data class GitUsersResponseData(val gitUsersList: List<GitUserDTO>): BaseResponse()