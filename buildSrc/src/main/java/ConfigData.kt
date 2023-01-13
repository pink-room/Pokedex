object ConfigData {
    const val minSdk = 24
    const val targetSdk = 33
    const val compileSdk = 33
    private const val versionMajor = 0
    private const val versionMinor = 0
    private const val versionPatch = 1
    const val versionName = "$versionMajor.$versionMinor.$versionPatch"
    const val code =
        versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionPatch
    const val namespace = "dev.pinkroom.pokedex"
    const val androidAppId = "$namespace.android"
    const val application = "com.android.application"
    const val library = "com.android.library"
    const val android = "android"
    const val multiplatform = "multiplatform"
    const val ksp = "com.google.devtools.ksp"
    const val nativeCoroutines = "com.rickclephas.kmp.nativecoroutines"
    const val serialization = "kotlinx-serialization"
    const val serializationPlugin = "org.jetbrains.kotlin.plugin.serialization"
    const val sqlDelightPlugin = "com.squareup.sqldelight"
}