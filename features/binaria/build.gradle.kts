import deps.Deps
import deps.LocalDeps

plugins {
    `feature-module`
}

dependencies {
    implementation(project(LocalDeps.domainExchange))
    implementation(project(LocalDeps.domainTransfer))

    implementation(project(LocalDeps.dataRemoteExchange))

    implementation(Deps.adapterDelegate)
    implementation(Deps.adapterDelegateViewBinding)
}