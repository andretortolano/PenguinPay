import extensions.projectImplementation

plugins {
    `domain-module`
}

dependencies {
    projectImplementation(deps.LocalDeps.domainExchange)
}