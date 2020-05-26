package hr.ferit.bozidarkelava.birdsofprey

import android.app.Application
import android.content.Context

class BirdsOfPrey : Application() {

    companion object{
        lateinit var ApplicationContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}