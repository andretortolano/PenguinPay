package plugins

import deps.LocalDeps
import deps.TestDeps

plugins {
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

apply(plugin = "kotlin")

dependencies {
    implementation(project(LocalDeps.appDependencyInjection))
    implementation(project(LocalDeps.libExtensions))

    testImplementation(project(LocalDeps.libExtensions))

    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.googleTruth)
    testImplementation(TestDeps.mockk)
    testImplementation(TestDeps.coroutines)
}