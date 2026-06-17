import dev.songzh.function.trace.FunctionTracerGradleExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    kotlin("multiplatform") version "2.1.0"
    id("dev.songzh.function.trace") version "0.3.0"
}

group = "org.hw"
version = "1.0-SNAPSHOT"

// Make mavenLocal available in every project for the funtrace runtime library.
// Also add the funtrace dependency to commonMain of every KMP project from here,
// so individual subprojects don't need to declare it themselves.
allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.named("commonMain").configure {
                dependencies {
                    implementation("func.trace:funtrace:1.0.0")
                }
            }
        }
    }
}

// Automatically apply the plugin to every subproject added in the future
subprojects {
    apply(plugin = "dev.songzh.function.trace")
    extensions.configure<FunctionTracerGradleExtension> {
        traceAll = true
        packagePath = "func.trace"
    }
}

kotlin {
    jvmToolchain(17)

    jvm()

    macosArm64 {
        binaries {
            executable {
                entryPoint = "org.hw.main"
            }
        }
    }
    macosX64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":greet"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

// Configure the plugin for the root project
functionTracer {
    traceAll = true
    packagePath = "func.trace"
}

