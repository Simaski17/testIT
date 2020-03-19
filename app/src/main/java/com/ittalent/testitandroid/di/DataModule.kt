package com.ittalent.testitandroid.di

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.data.source.LocalDataSource
import com.ittalent.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun itunesRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) = ItunesRepository(localDataSource, remoteDataSource)

}