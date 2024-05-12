package util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import presentation.component.Theme

interface AppPreferences {

    suspend fun getAppTheme(): Theme
    suspend fun changeAppTheme(theme: Theme): Preferences

    val onAppThemeChange: Flow<Theme>
}

internal class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    private companion object {
        private const val PREFS_TAG_KEY = "AppPreferences"
        private const val APP_THEME_PREFS = "appPrefsTheme"
    }

    private val appTheme = intPreferencesKey("$PREFS_TAG_KEY$APP_THEME_PREFS")


    override suspend fun getAppTheme(): Theme {
        val id = dataStore.data.map { preferences ->
            preferences[appTheme] ?: 0
        }.first()
        return Theme.fromId(id)
    }

    override suspend fun changeAppTheme(theme: Theme) = dataStore.edit { preferences ->
        preferences[appTheme] = theme.id
    }

    override val onAppThemeChange: Flow<Theme>
        get() = dataStore.data.map { preferences ->
            val id = preferences[appTheme] ?: 0
            Theme.fromId(id)
        }
}