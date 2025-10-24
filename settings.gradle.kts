// FILE PERBAIKAN UNTUK MENGHILANGKAN ERROR
// Hapus isi file settings.gradle.kts Anda dan ganti dengan ini.

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Responsi 1 Mobile H1D023018"
include(":app")
