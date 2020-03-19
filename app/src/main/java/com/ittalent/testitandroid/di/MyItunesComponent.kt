package com.ittalent.testitandroid.di

import android.app.Application
import com.ittalent.testitandroid.ui.detail.DetailActivityComponent
import com.ittalent.testitandroid.ui.detail.DetailActivityModule
import com.ittalent.testitandroid.ui.main.MainActivityComponent
import com.ittalent.testitandroid.ui.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyItunesComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): MyItunesComponent
    }

}