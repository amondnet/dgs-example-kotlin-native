/*
 * Copyright 2021 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.netflix.dgs.codegen")
    kotlin("plugin.spring")
    id("org.springframework.experimental.aot") version "0.12.1"
}


dependencies {
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.4.3"))

    api("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")

    implementation("com.graphql-java:graphql-java-extended-scalars")
    implementation("com.github.javafaker:javafaker:1.+")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.apache.tomcat.embed", module = "tomcat-embed-core")
        exclude(group = "org.apache.tomcat.embed", module = "tomcat-embed-websocket")
    }
    implementation("org.apache.tomcat.experimental:tomcat-embed-programmatic:${dependencyManagement.importedProperties["tomcat.version"]}")

    implementation("org.springframework.experimental:spring-native:0.12.1")
    implementation("org.springframework.experimental:spring-aot:0.12.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")


    implementation(kotlin("stdlib-jdk8"))


}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    generateClient = true
    packageName = "com.example.demo.generated"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    generateClient = true
    packageName = "com.example.demo.generated"
}


tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf(
        "BP_NATIVE_IMAGE" to "true",
        "BP_NATIVE_IMAGE_BUILD_ARGUMENTS" to "-H:+AllowIncompleteClasspath --initialize-at-build-time=org.springframework.util.unit.DataSize,org.springframework.core.annotation.AnnotationFilter,org.springframework.core.annotation.PackagesAnnotationFilter,org.springframework.util.StringUtils,org.springframework.util.Assert"
    )
}
springAot {
    mode.set(org.springframework.aot.gradle.dsl.AotMode.NATIVE)
    debugVerify.set(false)
    removeXmlSupport.set(true)
    removeSpelSupport.set(true)
    removeYamlSupport.set(false)
    removeJmxSupport.set(true)
    verify.set(true)
    //removeUnusedConfig.set(true)
    //failOnMissingSelectorHint.set(true)
    //buildTimePropertiesMatchIfMissing.set(true)
    //buildTimePropertiesChecks.set(arrayOf("default-include-all","!spring.dont.include.these.","!or.these"))}
}