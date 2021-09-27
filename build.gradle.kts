buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("org.jacoco:org.jacoco.core:0.8.7")
    }
}

/**
 * This lets us use gradle.kts files inside folded libraries like ":libraries:coroutines"
 */
subprojects { parent!!.path.takeIf { it != rootProject.path }?.let { evaluationDependsOn(it)  } }

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}