package ferit.bozidarkelava.myapplication2

import androidx.room.Room
import androidx.room.RoomDatabase
import ferit.bozidarkelava.myapplication2.interfaces.InspiringPersonDao

//prirucnik str 70
@androidx.room.Database(version = 1, entities = arrayOf(InspiringPerson::class))
abstract  class Database: RoomDatabase() {
    abstract fun inspiringPersonDao(): InspiringPersonDao
    companion object {
        private const val  NAME ="InspiringPersonsDatabase"
        private var INSTANCE: Database? = null
        fun getInstance(): Database {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(InspiringPersonApp.ApplicationContext, Database::class.java, NAME).allowMainThreadQueries().build()
            }
            return INSTANCE as Database
        }
    }

}