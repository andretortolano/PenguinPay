package deps

object Deps {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    const val androidxCompat = "androidx.appcompat:appcompat:${Versions.androidxCompat}"

    val koin = arrayListOf(
        "io.insert-koin:koin-core:${Versions.koin}",
        "io.insert-koin:koin-android:${Versions.koin}"
    )
}