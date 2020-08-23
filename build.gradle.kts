// https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html

fun yass2(module: String) = "ch.softappeal.yass2:yass2-$module:6.0.0"

fun ktor(module: String) = "io.ktor:ktor-$module:1.4.0"

plugins {
    kotlin("multiplatform") version "1.4.0"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    js {
        nodejs()
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                allWarningsAsErrors = true
                freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(yass2("coroutines"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(yass2("ktor"))
                implementation(yass2("reflect"))
                implementation(ktor("client-cio"))
                implementation(ktor("server-cio"))
                implementation(ktor("websockets"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(yass2("generate"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
