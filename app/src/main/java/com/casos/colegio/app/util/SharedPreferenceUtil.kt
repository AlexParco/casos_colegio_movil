package com.casos.colegio.app.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.casos.colegio.app.data.models.User
import com.google.gson.Gson

class SharedPreferenceUtil {
    companion object {
        private const val SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY"
        private lateinit var sharedPreferences: SharedPreferences
        private const val USER = "USER_KEY"
    }

    fun setSharedPreference(context: Context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    fun saveUser(user: User) {
        val jsonUser = Gson().toJson(user)
        Log.e("SharedPreferenceUtil", "jsonUser: $jsonUser")

        sharedPreferences
            .edit()
            .putString(USER, jsonUser)
            .apply()
    }

    fun getUser(): User?{
        val jsonUser = sharedPreferences.getString(USER, "")
        return  Gson().fromJson(jsonUser, User::class.java)
    }

    fun removeUser() {
        sharedPreferences
            .edit()
            .remove(USER)
            .apply()
    }
}