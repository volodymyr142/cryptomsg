package ua.volodymyr142.cryptomessenger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
