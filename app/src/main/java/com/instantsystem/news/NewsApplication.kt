package com.instantsystem.news

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

/**
 * Classe de base de l'application
 */
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * Initialisation de Koin
         */
        startKoin {
            EmptyLogger()
            androidContext(this@NewsApplication)
            modules(appModule)
        }
    }
}