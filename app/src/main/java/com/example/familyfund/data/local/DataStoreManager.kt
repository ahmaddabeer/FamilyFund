package com.example.familyfund.data.local

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(

    name = "user_preference"
)


class UserPreferences(private val context: Context) {

    companion object {

        val USER_ID = stringPreferencesKey("user_id")
        val USER_LOGIN = booleanPreferencesKey("login")
        val USER_NAME = stringPreferencesKey("user_name")

    }

    // save UID and role

    suspend fun saveUserSession(id: String, login: Boolean, name: String) {

        context.dataStore.edit { preference ->

            preference[USER_ID] = id
            preference[USER_LOGIN] = login
            preference[USER_NAME] = name
        }
    }


    val userSession = context.dataStore.data.map { preference ->

        UserSession(
            id = preference[USER_ID],
            login = preference[USER_LOGIN] ?: false,
            name = preference[USER_NAME]
        )


    }

    //clear session

    suspend fun clearSession() {

        context.dataStore.edit { preference ->

            preference.clear()
        }
    }


}


data class UserSession(
    val id: String?,
    val login: Boolean,
    val name: String?
)

