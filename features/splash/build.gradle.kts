import deps.LocalDeps

plugins {
    `feature-module`
}

dependencies {
    implementation(project(LocalDeps.domainUser))

    implementation(project(LocalDeps.dataLocalUserPreferences))
}