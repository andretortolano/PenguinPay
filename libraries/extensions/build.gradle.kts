import deps.TestDeps

plugins {
    `kotlin-module`
}

dependencies {
    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.googleTruth)
    testImplementation(TestDeps.mockk)
}