import deps.LocalDeps
import extensions.projectImplementation

apply(plugin = "com.android.library")
apply(plugin = "kotlin-android")
apply(from = "$rootDir/gradle/android.gradle")

dependencies {
    projectImplementation(LocalDeps.appDependencyInjection)
}