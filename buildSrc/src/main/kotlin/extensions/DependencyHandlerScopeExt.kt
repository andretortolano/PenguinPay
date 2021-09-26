@file:Suppress("RemoveRedundantBackticks")

package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.`implementation`(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandlerScope.projectImplementation(dependency: String): Dependency? =
    add("implementation", project(dependency))

fun DependencyHandlerScope.projectTestImplementation(dependency: String): Dependency? =
    add("testImplementation", project(dependency))