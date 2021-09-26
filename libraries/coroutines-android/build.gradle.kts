import deps.Deps
import deps.LocalDeps

plugins {
    `android-module`
}

dependencies {
    api(project(LocalDeps.libCoroutines))

    api(Deps.kotlinLib)
    api(Deps.androidxCore)
    api(Deps.kotlinCoroutines)
    api(Deps.kotlinCoroutinesAndroid)
    api(Deps.lifecycleLiveData)
    api(Deps.lifecycleViewModel)
}