rootProject.name = "PenguinPay"

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(
    ":appNavigation",
    ":appDependencyInjection",
    ":appDesignSystem",
    ":app"
)

include(
    ":libraries:coroutines-android",
    ":libraries:coroutines-android-test",
    ":libraries:extensions",
    ":libraries:extensions-android",
    ":libraries:sharedPrefs"
)

include(
    ":data:local:userPreferences",
    ":data:remote:exchange"
)

include(
    ":domain:transfer",
    ":domain:exchange",
    ":domain:user"
)

include(
    ":features:splash",
    ":features:binaria",
    ":features:home"
)