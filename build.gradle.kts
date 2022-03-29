import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version "2.6.4" apply false
    id("com.netflix.dgs.codegen") version "5.1.16" apply false
    kotlin("plugin.spring") version "1.5.10" apply false

}
repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/release") }
}
group = "com.example"
version = "0.0.1-SNAPSHOT"

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }
    //apply(plugin ="")
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/release") }
    }
    group = "com.example"
    version = "0.0.1-SNAPSHOT"
    //java.sourceCompatibility = JavaVersion.VERSION_11
    //java.sourceCompatibility = JavaVersion.VERSION_11

    val implementation by configurations


}
