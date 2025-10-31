package com.pampang.nav.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pampang.nav.models.ProfileMenuModel
import com.pampang.nav.models.profileMenus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _profileMenuItems = MutableLiveData<List<ProfileMenuModel>>()
    val profileMenuItems: LiveData<List<ProfileMenuModel>> get() = _profileMenuItems

    init {
        _profileMenuItems.value = profileMenus
    }
}