import deps.LocalDeps

plugins {
    plugins.`app-module`
}

android {
    defaultConfig {
        applicationId = "com.penguinpay"
        versionCode = GradleConfig.versionCode
        versionName = GradleConfig.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(LocalDeps.appDependencyInjection))
    implementation(project(LocalDeps.appDesignSystem))
    implementation(project(LocalDeps.appNavigation))
    implementation(project(LocalDeps.libCoroutinesAndroid))

    implementation(project(LocalDeps.featureSplash))
    implementation(project(LocalDeps.featureHome))
    implementation(project(LocalDeps.featureBinaria))

    implementation(project(LocalDeps.dataRemoteExchange))
}