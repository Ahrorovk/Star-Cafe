package com.example.starcafe.di

import android.content.Context
import androidx.room.Room
import com.example.starcafe.data.local.dao.TransactionDao
import com.example.starcafe.data.local.dataStore.DataStoreManager
import com.example.starcafe.data.local.db.StarsCafeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StarsCafeDatabase {
        return Room.databaseBuilder(
            context,
            StarsCafeDatabase::class.java,
            "stars_cafe_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(appDatabase: StarsCafeDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }
}