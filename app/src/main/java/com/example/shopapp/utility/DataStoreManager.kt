package com.example.shopapp.utility

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("shop_app_preferences")


class DataStoreManager(private val context: Context) {

    private val dataStore = context.dataStore

    companion object {

        val ACCESS_TOKEN = stringPreferencesKey(Constants.ACCESS_TOKEN)

    }

    val accessToken: Flow<String> = context.dataStore.data
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