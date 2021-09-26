import deps.Deps

plugins {
    `data-module`
}

dependencies {
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)
    implementation(Deps.okHttp3LogInterceptor)
}