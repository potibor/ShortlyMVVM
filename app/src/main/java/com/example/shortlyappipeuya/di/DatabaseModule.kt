package com.example.shortlyappipeuya.di

import android.content.Context
import androidx.room.Room
import com.example.shortlyappipeuya.application.MainApplication
import com.example.shortlyappipeuya.data.database.LinkDatabase
import com.example.shortlyappipeuya.data.database.dao.LinksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): LinkDatabase =
        Room.databaseBuilder(
            context,
            LinkDatabase::class.java,
            "notes.db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    fun provideLogDao(database: LinkDatabase): LinksDao {
        return database.linksDao()
    }
}