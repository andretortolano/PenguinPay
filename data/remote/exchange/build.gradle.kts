import deps.Deps

plugins {
    plugins.`data-module`
}

dependencies {
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)
    implementation(Deps.okHttp3LogInterceptor)
}