package com.example.shopapp.utility

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class DataStoreManager(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("shop_app_preferences")


    private val dataStore = context.dataStore

    companion object {

        val ACCESS_TOKEN = stringPreferencesKey(Constants.ACCESS_TOKEN)

    }

    val accessToken: Flow<String> = dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[ACCESS_TOKEN] ?: ""
        }

    suspend fun saveAccessToken(accessToken: String){
        dataStore.edit {
            it[ACCESS_TOKEN] = accessToken

        }
    }


}