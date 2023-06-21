package studio.zebro.clipr

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform