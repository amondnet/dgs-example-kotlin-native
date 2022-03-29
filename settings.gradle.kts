pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://repo.spring.io/release") }
    }
}
rootProject.name = "dgs-example-kotlin-native"
include("dgs-example-kotlin")
include("dgs-example-kotlin-native")
