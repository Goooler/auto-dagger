
import gradle.kotlin.dsl.accessors._79379d928b4cca98f617cd011ea3d869.dokkaPlugin
import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.dokka.gradle.AbstractDokkaTask

plugins {
    id("org.jetbrains.dokka")
}

val libs = the<LibrariesForLibs>()

dependencies {
    dokkaPlugin(libs.dokka.versioningPlugin)
}

tasks.withType<AbstractDokkaTask>().configureEach {
    notCompatibleWithConfigurationCache("Dokka does not support config cache yet")
}