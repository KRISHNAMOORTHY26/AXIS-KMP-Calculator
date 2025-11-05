package com.krishna.axiscalculatorkmpapp.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class ApiService(private val client: HttpClient) {

    suspend fun getUsers(): User {
        return client.get("https://jsonplaceholder.typicode.com/posts/1").body()
    }
}

@Serializable
data class User(
    val userId: Int,
    val id: Int,
    val title: String,
    val body : String
)
