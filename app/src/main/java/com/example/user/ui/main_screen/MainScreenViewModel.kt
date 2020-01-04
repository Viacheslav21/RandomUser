package com.example.user.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.user.data.MainScreenRepository
import com.example.user.main.BaseViewModel
import com.example.user.objects.UserInfo
import kotlinx.coroutines.launch

class MainScreenViewModel(private val mainScreenRepository: MainScreenRepository) : BaseViewModel() {


    private val _showMainScreenUsers = MediatorLiveData<Pair<List<UserInfo>, Boolean>>()
    val showMainScreenUsers: LiveData<Pair<List<UserInfo>, Boolean>> = _showMainScreenUsers

    private var page = 0
    fun getElements(newPage: Boolean = false) {
        viewModelScope.launch {
            showLoading(true)
            try {
                if (_showMainScreenUsers.value?.first.isNullOrEmpty() || newPage) {
                    val response = mainScreenRepository.getUsers(page)

                    val userList = response.results ?: emptyList()

                    page = response.info?.page ?: 0

                    _showMainScreenUsers.postValue(Pair(userList, !newPage))
                } else {
                    _showMainScreenUsers.postValue(_showMainScreenUsers.value)
                }

            } catch (e: Exception) {
                handleError(e)
            } finally {
                showLoading(false)
            }
        }
    }

}

