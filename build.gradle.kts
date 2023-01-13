plugins {
    id(ConfigData.application).version(Versions.gradle).apply(false)
    id(ConfigData.library).version(Versions.gradle).apply(false)
    kotlin(ConfigData.android).version(Versions.kotlin).apply(false)
    kotlin(ConfigData.multiplatform).version(Versions.kotlin).apply(false)
    id(ConfigData.serializationPlugin).version(Versions.kotlin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
