plugins {
    id(ConfigData.application)
    kotlin(ConfigData.android)
}

android {
    namespace = ConfigData.androidAppId
    compileSdk = ConfigData.compileSdk
    defaultConfig {
        applicationId = ConfigData.androidAppId
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
        implementation(lifecycle)
        implementation(paging)
    }
    // Shared ViewModels
    implementation(Deps.KMMViewModels.core)
    // Koin
    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }
    // Image loading
    with(Deps.Landscapist) {
        implementation(coil)
        implementation(placeholder)
    }
}