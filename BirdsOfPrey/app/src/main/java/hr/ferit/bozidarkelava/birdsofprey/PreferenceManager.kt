package hr.ferit.bozidarkelava.birdsofprey

import android.content.Context

class PreferenceManager {
    companion object {
        const val PREFS_FILE = "MyPreferences"
        const val  PREFS_KEY_USERNAME = "Bozidar"
    }

    fun saveColor(color: String) {
        val sharedPreferences = BirdsOfPrey.ApplicationContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("color", color)
        editor.apply()
    }

    fun getColor(): String? {
        val sharedPreferences = BirdsOfPrey.ApplicationContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getString("color", "unknown")
    }

    fun saveCounter(counter: String) {
        val sharedPreferences = BirdsOfPrey.ApplicationContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("counter", counter)
        editor.apply()
    }

    fun  getCounter(): String? {
        val sharedPreferences = BirdsOfPrey.ApplicationContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getString("counter", "0")
    }

}