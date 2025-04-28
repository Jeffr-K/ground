plugins {
    kotlin("jvm") version "1.9.25" apply false
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.4.4" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    kotlin("plugin.jpa") version "1.9.25" apply false
}

allprojects {
    group = "com.meshcraft"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    if (project.name != "ground-core") {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply(plugin = "org.jetbrains.kotlin.plugin.spring")
        apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")

        the<JavaPluginExtension>().apply {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
        }

        dependencies {
            "implementation"("org.springframework.boot:spring-boot-starter-data-jdbc")
            "implementation"("org.springframework.boot:spring-boot-starter-data-jpa")
            "implementation"("org.springframework.boot:spring-boot-starter-web")
            "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
            "implementation"("org.springframework.kafka:spring-kafka")
            "implementation"("org.jetbrains.kotlin:kotlin-reflect")
            "implementation"("org.springframework.boot:spring-boot-starter-validation")
            "developmentOnly"("org.springframework.boot:spring-boot-devtools")
            "runtimeOnly"("com.mysql:mysql-connector-j")
            "annotationProcessor"("org.springframework.boot:spring-boot-configuration-processor")

//            "implementation"("io.github.microutils:kotlin-logging-jvm:3.0.5")
//            "implementation"("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
//            "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
//            "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
//            "implementation"("org.springframework.boot:spring-boot-starter-actuator")
//            "implementation"("io.micrometer:micrometer-registry-prometheus")
//            "implementation"("org.springframework.boot:spring-boot-starter-cache")
//            "implementation"("com.github.ben-manes.caffeine:caffeine:3.1.8")

            "testImplementation"("io.kotest:kotest-runner-junit5:5.8.1")
            "testImplementation"("io.kotest:kotest-assertions-core:5.8.1")
            "testImplementation"("io.kotest:kotest-property:5.8.1")
            "testImplementation"("io.kotest:kotest-extensions-spring:4.4.3")
            "testImplementation"("io.mockk:mockk:1.13.10")
            "testImplementation"("com.ninja-squad:springmockk:4.0.2")
            "testImplementation"("org.testcontainers:testcontainers:1.19.6")
            "testImplementation"("org.testcontainers:junit-jupiter:1.19.6")
            "testImplementation"("org.testcontainers:mysql:1.19.6")
            "testImplementation"("org.testcontainers:kafka:1.19.6")
//            "testImplementation"("org.springframework.boot:spring-boot-starter-test")
            "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
                exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
            }
            "testImplementation"("org.springframework.kafka:spring-kafka-test")
            "testImplementation"("org.jetbrains.kotlin:kotlin-test-junit5")
            "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "21"
            }
        }

        plugins.withId("org.jetbrains.kotlin.plugin.jpa") {
            configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
                annotation("jakarta.persistence.Entity")
                annotation("jakarta.persistence.MappedSuperclass")
                annotation("jakarta.persistence.Embeddable")
            }
        }

        tasks.withType<Test> {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }
    }
}