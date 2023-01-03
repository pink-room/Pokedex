package dev.pinkroom.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform