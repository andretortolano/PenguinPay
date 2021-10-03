package plugins

import deps.LocalDeps
import deps.TestDeps
import extensions.projectImplementation
import extensions.testImplementation

apply(plugin = "com.android.library")
apply(plugin = "kotlin-android")
apply(from = "$rootDir/gradle/android.gradle")

dependencies {
    projectImplementation(LocalDeps.appDependencyInjection)

    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.googleTruth)
    testImplementation(TestDeps.mockk)
    testImplementation(TestDeps.coroutines)
}