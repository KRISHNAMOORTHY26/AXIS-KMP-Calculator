package com.krishna.axiscalculatorkmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform