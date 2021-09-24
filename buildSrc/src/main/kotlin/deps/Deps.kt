package deps

object Deps {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val androidxCompat = "androidx.appcompat:appcompat:${Versions.androidxCompat}"

    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycle}"
}