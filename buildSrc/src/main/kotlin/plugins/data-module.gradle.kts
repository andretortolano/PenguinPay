import deps.LocalDeps
import extensions.*

apply(plugin = "com.android.library")
apply(plugin = "kotlin-android")
apply(from = "$rootDir/gradle/android.gradle")

dependencies {
    projectImplementation(LocalDeps.appDependencyInjection)

    projectTestImplementation(LocalDeps.libCoroutinesTest)
}