object Deps {

    object Compose {
        const val composeBom = "androidx.compose:compose-bom:2022.12.00"
        const val ui = "androidx.compose.ui:ui"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val foundation = "androidx.compose.foundation:foundation"
        const val material = "androidx.compose.material:material"
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
        const val paging = "androidx.paging:paging-compose:${Versions.pagingCompose}"
    }

    object Ktorfit {
        const val ksp = "de.jensklingenberg.ktorfit:ktorfit-ksp:${Versions.ktorfit}"
        const val ktorfit = "de.jensklingenberg.ktorfit:ktorfit-lib:${Versions.ktorfit}"
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}"
    }

    object KMMViewModels {
        const val core = "com.rickclephas.kmm:kmm-viewmodel-core:${Versions.kmmViewModels}"
        const val lifecycle =
            "androidx.lifecycle:lifecycle-viewmodel:${Versions.viewModelLifecycle}"
    }

    object Pagination {
        const val core = "io.github.kuuuurt:multiplatform-paging:${Versions.paging}"
    }

    object Landscapist {
        const val coil = "com.github.skydoves:landscapist-coil:${Versions.landscapist}"
        const val placeholder =
            "com.github.skydoves:landscapist-placeholder:${Versions.landscapist}"
    }

    object SqlDelight {
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    }
}