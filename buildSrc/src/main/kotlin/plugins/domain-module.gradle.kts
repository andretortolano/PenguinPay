import deps.LocalDeps

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
    implementation(project(LocalDeps.libCoroutines))
    implementation(project(LocalDeps.libExtensions))

    testImplementation(project(LocalDeps.libCoroutinesTest))
    testImplementation(project(LocalDeps.libExtensions))
}