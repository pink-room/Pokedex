plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "dev.pinkroom.pokedex.android"
    compileSdk = ConfigData.compileSdk
    defaultConfig {
        applicationId = "dev.pinkroom.pokedex.android"
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.code
        versionName = ConfigData.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    with(Deps.Compose) {
        implementation(platform(composeBom))
        implementation(ui)
        implementation(tooling)
        implementation(foundation)
        implementation(material)
        implementation(activity)
    }
}