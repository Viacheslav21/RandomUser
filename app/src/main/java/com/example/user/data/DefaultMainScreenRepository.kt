package com.example.user.data

import com.example.user.data.remote.MainScreenApi
import com.example.user.objects.UserObject


interface MainScreenRepository {
    suspend fun getUsers(page: Int): UserObject
}

class DefaultMainScreenRepository(
    private val remoteAuthDataSource: MainScreenApi
) : MainScreenRepository {
    override suspend fun getUsers(page: Int): UserObject =
        remoteAuthDataSource.getUsers(page = page)
}