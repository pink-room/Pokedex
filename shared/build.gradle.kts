plugins {
    kotlin(ConfigData.multiplatform)
    id(ConfigData.library)
    id(ConfigData.serialization)
    id(ConfigData.ksp) version Versions.ksp
    id(ConfigData.nativeCoroutines) version Versions.nativeCoroutines
    id(ConfigData.sqlDelightPlugin) version Versions.sqlDelight
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.Ktorfit) {
                    implementation(ktorfit)
                    implementation(serialization)
                    implementation(negotiation)
                    implementation(json)
                    implementation(commonLogging)
                }
                implementation(Deps.Coroutines.core)
                implementation(Deps.Koin.core)
                implementation(Deps.KMMViewModels.core)
                api(Deps.Pagination.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.Ktorfit.clientAndroid)
                implementation(Deps.KMMViewModels.lifecycle)
            }
        }
        val androidTest by creating
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = ConfigData.namespace
    compileSdk = ConfigData.compileSdk
    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
    }
}
dependencies {
    add("kspCommonMainMetadata", Deps.Ktorfit.ksp)
    add("kspAndroid", Deps.Ktorfit.ksp)
    add("kspIosX64", Deps.Ktorfit.ksp)
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

sqldelight {
    database("PokemonDatabase") {
        packageName = "dev.pinkroom.pokedex"
    }
}