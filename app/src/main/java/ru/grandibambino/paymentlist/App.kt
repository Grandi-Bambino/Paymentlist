package ru.grandibambino.paymentlist

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.grandibambino.paymentlist.di.repoModules
import ru.grandibambino.paymentlist.di.viewModelModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                repoModules, viewModelModules
            ))
        }
    }

}