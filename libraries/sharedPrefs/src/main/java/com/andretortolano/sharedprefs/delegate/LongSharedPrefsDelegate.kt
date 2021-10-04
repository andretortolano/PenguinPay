package com.andretortolano.sharedprefs.delegate

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LongSharedPrefsDelegate(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val default: Long
) : ReadWriteProperty<Any?, Long> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return sharedPreferences.getLong(key, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }
}