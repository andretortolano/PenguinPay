import deps.TestDeps

plugins {
    plugins.`kotlin-module`
}

dependencies {
    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.googleTruth)
    testImplementation(TestDeps.mockk)
}