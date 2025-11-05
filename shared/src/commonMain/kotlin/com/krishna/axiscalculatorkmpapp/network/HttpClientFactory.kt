package com.krishna.axiscalculatorkmpapp.network

import io.ktor.client.HttpClient

expect class HttpClientFactory() {
    fun createClient(): HttpClient
}