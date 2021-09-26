package deps

object Deps {
    const val kotlinLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val androidxCompat = "androidx.appcompat:appcompat:${Versions.androidxCompat}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"

    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycle}"

    const val navigationUi = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationFragment = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val adapterDelegate = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.adapterDelegate}"
    const val adapterDelegateViewBinding = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegate}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttp3LogInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}