plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ktor) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    extensions.configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        filter {
            exclude { it.file.path.contains("${File.separator}build${File.separator}generated${File.separator}") }
        }
    }

    extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        buildUponDefaultConfig = true
        parallel = true
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        // Compose Multiplatform's generated resource accessors live under a "generated.resources"
        // package and don't follow our style rules.
        exclude("**/generated/resources/**")
    }

    // The detekt Gradle plugin generates one analysis task per Kotlin target/source set in a
    // multiplatform module, but doesn't wire them into the umbrella `detekt`/`check` tasks on
    // its own — do that explicitly so `./gradlew detekt` actually covers every target.
    afterEvaluate {
        tasks.matching { it.name == "detekt" }.configureEach {
            dependsOn(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().matching { it.name != "detekt" })
        }
    }
}