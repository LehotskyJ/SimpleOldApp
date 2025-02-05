package com.example.simpleoldapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import javax.inject.Singleton
import kotlin.random.Random
import kotlinx.coroutines.launch

data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0,
)

@HiltViewModel
class DiceRollViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()

    // Handle business logic
    fun rollDice() {
        _uiState.update { currentState ->
            currentState.copy(
                firstDieValue = Random.nextInt(from = 1, until = 70),
                secondDieValue = Random.nextInt(from = 1, until = 7),
                numberOfRolls = currentState.numberOfRolls + 1,
            )
        }
    }

    // Register user
    fun registerUser(registrationData: RegisterCred, onResult: (Int, String, ResponseReg?) -> Unit) {

        viewModelScope.launch {
            try {
                val response = userRepository.registerUser(registrationData)
                if (response.isSuccessful) {
                    onResult(response.code(), response.message(), response.body())
                } else {
                    onResult(response.code(), response.message(), response.body())
                }

            } catch (e: Exception) {
                onResult(0, e.message.toString(), null)
            }
        }

    }
}