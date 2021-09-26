import deps.LocalDeps
import deps.TestDeps

plugins {
    `android-module`
}

dependencies {
    implementation(project(LocalDeps.libCoroutinesAndroid))
    implementation(project(LocalDeps.libCoroutinesTest))

    api(TestDeps.junit)
    api(TestDeps.googleTruth)
    api(TestDeps.mockk)
    api(TestDeps.coroutines)
    api(TestDeps.androidxCore)
}