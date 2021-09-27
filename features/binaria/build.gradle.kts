import deps.Deps
import deps.LocalDeps

plugins {
    `feature-module`
}

apply(from = "$rootDir/gradle/jacoco.android.gradle")

dependencies {
    implementation(project(LocalDeps.domainExchange))
    implementation(project(LocalDeps.domainTransfer))

    implementation(project(LocalDeps.dataRemoteExchange))

    implementation(Deps.adapterDelegate)
    implementation(Deps.adapterDelegateViewBinding)
}