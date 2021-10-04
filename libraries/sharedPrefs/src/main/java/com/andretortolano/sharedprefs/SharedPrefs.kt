package com.andretortolano.sharedprefs

import android.content.Context
import com.andretortolano.sharedprefs.delegate.BooleanSharedPrefsDelegate
import com.andretortolano.sharedprefs.delegate.FloatSharedPrefsDelegate
import com.andretortolano.sharedprefs.delegate.IntSharedPrefsDelegate
import com.andretortolano.sharedprefs.delegate.LongSharedPrefsDelegate
import com.andretortolano.sharedprefs.delegate.StringSetSharedPrefsDelegate
import com.andretortolano.sharedprefs.delegate.StringSharedPrefsDelegate

abstract class SharedPrefs(context: Context, name: String) {

    private val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    protected fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    protected fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    protected fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    @Suppress("unused")
    fun SharedPrefs.booleanPref(key: String, default: Boolean) = BooleanSharedPrefsDelegate(
        sharedPreferences = sharedPreferences,
        key = key,
        default = default,
    )

    @Suppress("unused")
    fun SharedPrefs.stringPref(key: String, default: String) = StringSharedPrefsDelegate(
        sharedPreferences = sharedPreferences,
        key = key,
        default = default,
    )

    @Suppress("unused")
    fun SharedPrefs.intPref(key: String, default: Int) = IntSharedPrefsDelegate(
        sharedPreferences = sharedPreferences,
        key = key,
        default = default,
    )

    @Suppress("unused")
    fun SharedPrefs.floatPref(key: String, default: Float) = FloatSharedPrefsDelegate(
        sharedPreferences = sharedPreferences,
        key = key,
        default = default,
    )

    @Suppress("unused")
    fun SharedPrefs.longPref(key: String, default: Long) = LongSharedPrefsDelegate(
        sharedPreferences = sharedPreferences,
        key = key,
        default = default,
    )

    @Suppress("unused")
    fun SharedPrefs.stringSetPref(key: String, default: Set<String>) = StringSetSharedPrefsDelegate(
        sharedPreferences = sharedPreferences,
        key = key,
        default = default,
    )
}