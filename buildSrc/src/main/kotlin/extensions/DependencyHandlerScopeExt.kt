package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

internal fun DependencyHandlerScope.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandlerScope.projectImplementation(dependency: String): Dependency? =
    add("implementation", project(dependency))

internal fun DependencyHandlerScope.projectTestImplementation(dependency: String): Dependency? =
    add("testImplementation", project(dependency))