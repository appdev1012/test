package com.test.at.di

import android.content.Context
import androidx.room.Room
import com.test.at.model.db.DB
import com.test.at.model.db.DataDao
import com.test.at.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DB {
        return Room.databaseBuilder(
            context,
            DB::class.java,
            "ATDatabase"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: DB): DataDao {
        return db.dataDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dataDao: DataDao): DataRepository {
        return DataRepository(dataDao)
    }
}