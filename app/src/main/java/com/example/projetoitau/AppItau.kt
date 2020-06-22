package com.example.projetoitau

import androidx.multidex.MultiDexApplication
import com.example.projetoitau.di.AppComponent.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class AppItau : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        iniDI()
    }

    private fun iniDI() {
        startKoin {
            androidLogger()
            androidContext(this@AppItau)
            modules(getAllModules())
        }
    }


}