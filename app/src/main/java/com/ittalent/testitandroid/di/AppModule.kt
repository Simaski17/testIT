package com.ittalent.testitandroid.di

import android.app.Application
import androidx.room.Room
import com.ittalent.data.source.LocalDataSource
import com.ittalent.data.source.RemoteDataSource
import com.ittalent.testitandroid.data.database.RoomDataSource
import com.ittalent.testitandroid.data.database.SongsDatabase
import com.ittalent.testitandroid.data.server.TheItunesDbDataSource
import com.ittalent.testitandroid.data.server.TheItunesDbService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        SongsDatabase::class.java,
        "songs-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: SongsDatabase): LocalDataSource = RoomDataSource(db)

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(provideInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitServiceApi(): TheItunesDbService {
        return provideRetrofit().create(TheItunesDbService::class.java)
    }

    @Provides
    fun remoteDataSourceProvider(theItunesDbService: TheItunesDbService): RemoteDataSource = TheItunesDbDataSource(theItunesDbService)

}