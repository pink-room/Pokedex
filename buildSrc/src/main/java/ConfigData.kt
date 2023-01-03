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
}