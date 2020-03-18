package com.ittalent.testitandroid.di

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun itunesRepositoryProvider(remoteDataSource: RemoteDataSource) = ItunesRepository(remoteDataSource)

}