import deps.Deps

plugins {
    `android-module`
}

dependencies {
    api(Deps.materialDesign)
    api(Deps.androidxConstraintLayout)
    api(Deps.androidxCompat)
    api(Deps.navigationUi)
    api(Deps.navigationFragment)
}