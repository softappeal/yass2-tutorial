// https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html

fun coroutines(module: String) = "org.jetbrains.kotlinx:kotlinx-coroutines-$module:1.3.5"

fun ktor(module: String) = "io.ktor:ktor-$module:1.3.2"

val yass2 = "ch.softappeal.yass2:yass2:4.0.0"

plugins {
    kotlin("multiplatform") version "1.3.71"
}

repositories {
    mavenCentral()
    jcenter()
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
                implementation(coroutines("core-common"))
                implementation(yass2)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(ktor("client-cio"))
                implementation(ktor("server-cio"))
                implementation(ktor("websockets"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlin("stdlib-js"))
                implementation(coroutines("core-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
