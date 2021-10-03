import deps.Deps

plugins {
    plugins.`android-module`
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    implementation(Deps.kotlinLib)
    implementation(Deps.kotlinCoroutines)
    implementation(Deps.kotlinCoroutinesAndroid)
}