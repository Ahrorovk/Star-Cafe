package com.example.starcafe.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences_name")
        val RANDOM_NUMBER_KEY = intPreferencesKey("random_number")
        val STAR_BALANCE_KEY = intPreferencesKey("star_balance")
        val TOTAL_SPENT_KEY = intPreferencesKey("total_spent_stars")
        val TOKEN_STATE_KEY = stringPreferencesKey("token_state_key")
    }

    suspend fun getOrGenerateRandomNumber(): Int {
        val pref = context.dataStore.data.first()
        val existing = pref[RANDOM_NUMBER_KEY]

        return if (existing != null) {
            existing
        } else {
            val newRandom = (111111111..999999999).random()
            context.dataStore.edit { it[RANDOM_NUMBER_KEY] = newRandom }
            newRandom
        }
    }

    suspend fun updateTokenState(token: String) {
        context.dataStore.edit { preference ->
            preference[TOKEN_STATE_KEY] = token
        }
    }

    val getTokenState = context.dataStore.data.map {
        it[TOKEN_STATE_KEY] ?: ""
    }

    fun getTotalSpentStars(): Flow<Int> {
        return context.dataStore.data.map { prefs ->
            prefs[TOTAL_SPENT_KEY] ?: 0
        }
    }

    suspend fun increaseTotalSpentStars(amount: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[TOTAL_SPENT_KEY] ?: 0
            prefs[TOTAL_SPENT_KEY] = current + amount
        }
    }

    // Получить текущий баланс
    fun getStarBalance(): Flow<Int> {
        return context.dataStore.data.map { prefs ->
            prefs[STAR_BALANCE_KEY] ?: 0
        }
    }


    suspend fun addStars(amount: Int): Int {
        var newBalance = 0
        context.dataStore.edit { settings ->
            val current = settings[STAR_BALANCE_KEY] ?: 0
            newBalance = current + amount
            settings[STAR_BALANCE_KEY] = newBalance
        }
        return newBalance
    }

    // Уменьшить звёзды (например, при Redeem)
    suspend fun spendStars(amount: Int): Boolean {
        var success = false
        context.dataStore.edit { prefs ->
            val current = prefs[STAR_BALANCE_KEY] ?: 0
            if (current >= amount) {
                prefs[STAR_BALANCE_KEY] = current - amount
                success = true
            }
        }
        return success
    }
}

//    suspend fun setStarBalance(context: Context, value: Int) {
//        context.dataStore.edit { prefs ->
//            prefs[STAR_BALANCE_KEY] = value
//        }
//    }