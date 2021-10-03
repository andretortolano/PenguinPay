@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(
    ":libraries:coroutines",
    ":libraries:coroutines-test",
    ":libraries:coroutines-android",
    ":libraries:coroutines-android-test",
    ":libraries:extensions",
    ":libraries:extensions-android"
)

include(
    ":data:remote:exchange"
)

include(
    ":domain:transfer",
    ":domain:exchange"
)

include(
    ":features:splash",
    ":features:binaria",
    ":features:home"
)

include(
    ":appNavigation",
    ":appDependencyInjection",
    ":appDesignSystem",
    ":app"
)

rootProject.name = "PenguinPay"