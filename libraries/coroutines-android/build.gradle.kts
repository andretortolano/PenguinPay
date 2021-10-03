import deps.Deps

plugins {
    plugins.`android-module`
}

dependencies {
    api(Deps.kotlinLib)
    api(Deps.androidxCore)
    api(Deps.kotlinCoroutines)
    api(Deps.kotlinCoroutinesAndroid)
    api(Deps.lifecycleLiveData)
    api(Deps.lifecycleViewModel)
}