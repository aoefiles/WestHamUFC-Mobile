package com.responsi.h1d023018.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.responsi.h1d023018.data.model.Coach
import com.responsi.h1d023018.data.model.Player
import com.responsi.h1d023018.data.model.TeamResponse
import com.responsi.h1d023018.data.network.RetrofitInstance
import kotlinx.coroutines.launch


class TeamViewModel : ViewModel() {

    private val _teamData = MutableLiveData<TeamResponse>()
    val teamData: LiveData<TeamResponse> = _teamData

    private val _coach = MutableLiveData<Coach>()
    val coach: LiveData<Coach> = _coach


    private val _squad = MutableLiveData<List<Player>>()
    val squad: LiveData<List<Player>> = _squad


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchTeamData()
    }

    private fun fetchTeamData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTeamDetails()
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _teamData.value = responseBody
                    responseBody.coach?.let { coachData ->
                        _coach.value = coachData
                    }
                    _squad.value = responseBody.squad ?: emptyList()
                    Log.d("TeamViewModel", "Data fetch successful")
                } else {
                    Log.e("TeamViewModel", "Response error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("TeamViewModel", "Exception: ${e.message}")
            }
            _isLoading.value = false
        }
    }
}

