package com.andretortolano.sharedprefs.delegate

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringSetSharedPrefsDelegate(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val default: Set<String>
) : ReadWriteProperty<Any?, Set<String>> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Set<String> {
        return sharedPreferences.getStringSet(key, default) ?: default
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) {
        sharedPreferences.edit().putStringSet(key, value).apply()
    }
}