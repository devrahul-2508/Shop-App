package com.example.shopapp.utility

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shopapp.featureModules.authModule.models.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class DataStoreManager(val context: Context) {

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore("shop_app_preferences")


    private val dataStore = context.userPreferencesDataStore

    companion object {
        val USER_ID = stringPreferencesKey(Constants.USER_ID)
        val USER_NAME = stringPreferencesKey(Constants.USER_NAME)
        val EMAIL = stringPreferencesKey(Constants.EMAIL)
        val IS_ADMIN = booleanPreferencesKey(Constants.IS_ADMIN)
        val ACCESS_TOKEN = stringPreferencesKey(Constants.ACCESS_TOKEN)

    }

    val user: Flow<UserModel> = dataStore.data
        .map { preferences ->
            UserModel(
                id = preferences[USER_ID]?:"",
                userName = preferences[USER_NAME]?:"",
                email = preferences[EMAIL]?:"",
                isAdmin = preferences[IS_ADMIN]?:false,
                accessToken = preferences[ACCESS_TOKEN] ?: "",
            )
        }

    suspend fun saveUser(user: UserModel){
        dataStore.edit { preferences->
            preferences[USER_ID] =user.id!!
            preferences[USER_NAME] =user.userName!!
            preferences[EMAIL] = user.email!!
            preferences[IS_ADMIN] = user.isAdmin!!
            preferences[ACCESS_TOKEN] = user.accessToken!!

        }
    }




}