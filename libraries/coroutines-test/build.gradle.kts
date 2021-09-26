import deps.LocalDeps
import deps.TestDeps

plugins {
    `kotlin-module`
}

dependencies {
    implementation(project(LocalDeps.libCoroutines))

    api(TestDeps.junit)
    api(TestDeps.googleTruth)
    api(TestDeps.mockk)
    api(TestDeps.coroutines)
}