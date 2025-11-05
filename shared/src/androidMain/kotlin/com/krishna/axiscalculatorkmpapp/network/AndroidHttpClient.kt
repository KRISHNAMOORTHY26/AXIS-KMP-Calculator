package com.krishna.axiscalculatorkmpapp.network


import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual class HttpClientFactory {
    actual fun createClient(): HttpClient {
        return HttpClient(OkHttp) {
            val timeoutInMinutes = 5 // 5 minutes
            val timeoutInMillis = timeoutInMinutes * 60_000

            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }


            install(DefaultRequest) {
                headers.append("Content-Type", "application/json")
            }

            install(HttpTimeout){
                requestTimeoutMillis = timeoutInMillis.toLong()
                connectTimeoutMillis = timeoutInMillis.toLong()
                socketTimeoutMillis = timeoutInMillis.toLong()
            }

            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = io.ktor.client.plugins.logging.LogLevel.ALL
            }
        }
    }
}
