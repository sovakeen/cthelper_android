package com.example.cthelper.feature.student.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cthelper.network.api.StatisticsApiService
import com.example.cthelper.network.api.StatisticsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface StatisticsUiState {
    data object Loading : StatisticsUiState
    data class Success(val data: StatisticsResponse) : StatisticsUiState
    data class Error(val message: String) : StatisticsUiState
}

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val statsRepositoryImpl: StatsRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow<StatisticsUiState>(StatisticsUiState.Loading)
    val uiState: StateFlow<StatisticsUiState> = _uiState.asStateFlow()

    init {
        loadStatistics()
    }

    fun loadStatistics() {
        viewModelScope.launch {
            _uiState.value = StatisticsUiState.Loading
            try {
                val response = statsRepositoryImpl.getStats()
                _uiState.value = StatisticsUiState.Success(response)
            } catch (e: IOException) {
                _uiState.value = StatisticsUiState.Error("Network error: ${e.localizedMessage}")
            } catch (e: HttpException) {
                _uiState.value = StatisticsUiState.Error("Server error: ${e.localizedMessage}")
            } catch (e: Exception) {
                _uiState.value = StatisticsUiState.Error("Unexpected error: ${e.localizedMessage}")
            }
        }
    }

    fun refresh() = loadStatistics()
}
