package com.andretortolano.sharedprefs.delegate

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FloatSharedPrefsDelegate(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val default: Float
) : ReadWriteProperty<Any?, Float> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Float {
        return sharedPreferences.getFloat(key, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }
}