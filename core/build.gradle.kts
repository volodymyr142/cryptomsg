import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    iosArm64()
    iosSimulatorArm64()
    
    jvm()
    
    js {
        browser()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
    
    android {
       namespace = "ua.volodymyr142.cryptomessenger.core"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        // Crypto tests live in a shared physical directory and are wired into the
        // targets whose crypto actuals are implemented (JVM, Android). Move them back
        // to commonTest once the remaining targets (iOS/JS/wasmJs) have real actuals.
        getByName("jvmTest") {
            kotlin.srcDir("src/supportedCryptoTest/kotlin")
        }
        getByName("androidHostTest") {
            kotlin.srcDir("src/supportedCryptoTest/kotlin")
        }
    }
}
