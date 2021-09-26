import deps.LocalDeps
import extensions.projectImplementation
import extensions.projectTestImplementation

apply(plugin = "com.android.library")
apply(plugin = "kotlin-android")
apply(from = "$rootDir/gradle/android.feature.gradle")

dependencies {
    projectImplementation(LocalDeps.appDependencyInjection)
    projectImplementation(LocalDeps.appDesignSystem)
    projectImplementation(LocalDeps.appNavigation)
    projectImplementation(LocalDeps.libCoroutinesAndroid)
    projectImplementation(LocalDeps.libExtensionsAndroid)

    projectTestImplementation(LocalDeps.libCoroutinesTest)
    projectTestImplementation(LocalDeps.libCoroutinesAndroidTest)
}