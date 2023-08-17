plugins {
    java
    kotlin("jvm")
    id("library")
}

dependencies {
    implementation(libs.bundles.jvmTesting)
    api(libs.dagger.hilt.core)
    api(libs.kotlinx.metadata)
    api(libs.bundles.compileTesting)
    api(libs.okio)
}