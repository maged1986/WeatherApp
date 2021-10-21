package com.magednan.cobbletaskapplication.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magednan.cobbletaskapplication.model.Daily
import com.magednan.cobbletaskapplication.repository.MainRepository

class MainViewModel @ViewModelInject constructor(val repository: MainRepository)
    : ViewModel() {
//putting Data in LiveData
    val _listLiveData = MutableLiveData<List<Daily>>()
    val listLiveData: LiveData<List<Daily>>
        get() = _listLiveData
   // Getting Data from Repo
    suspend fun getData(latitude: Double, longitude: Double): MutableLiveData<List<Daily>> {
        repository.getData(latitude, longitude)
        _listLiveData.value = repository.getData(latitude, longitude)
        return _listLiveData
    }
}
