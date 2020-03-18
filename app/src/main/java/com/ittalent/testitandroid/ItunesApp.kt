package com.ittalent.testitandroid

import android.app.Application
import com.ittalent.testitandroid.di.DaggerMyItunesComponent
import com.ittalent.testitandroid.di.MyItunesComponent

class ItunesApp: Application() {

    lateinit var component: MyItunesComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerMyItunesComponent.factory().create(this)

    }

}