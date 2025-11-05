package com.krishna.axiscalculatorkmpapp.viewModel

import com.krishna.axiscalculatorkmpapp.calculator.Calculator
import com.krishna.axiscalculatorkmpapp.network.ApiService
import com.krishna.axiscalculatorkmpapp.network.HttpClientFactory
import io.ktor.util.logging.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SharedViewModel {

    private val client = HttpClientFactory().createClient()
    private val api = ApiService(client)
    private val calculator = Calculator()

    private val _calcResult = MutableStateFlow<Double?>(null)
    val calcResult: StateFlow<Double?> = _calcResult

    private val _users = MutableStateFlow<String?>(null)
    val users: StateFlow<String?> = _users

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun calculate(operation: String, aStr: String, bStr: String) {
        val a = aStr.toDoubleOrNull()
        val b = bStr.toDoubleOrNull()

        if (a == null || b == null) {
            _calcResult.value = null
            return
        }

        _calcResult.value = when (operation) {
            "add" -> calculator.add(a, b)
            "subtract" -> calculator.subtract(a, b)
            "multiply" -> calculator.multiply(a, b)
            "divide" -> calculator.divide(a, b)
            else -> null
        }
    }

    fun loadUsers() {
        scope.launch {
            val data = api.getUsers()
            _users.value = data.title
        }
    }

    fun clear() {
        scope.cancel()
    }
}
