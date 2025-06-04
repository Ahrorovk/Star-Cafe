package com.example.starcafe.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starcafe.data.local.dao.TransactionDao
import com.example.starcafe.data.model.TransactionEntity


@Database(
    entities = [TransactionEntity::class],
    version = 5
)
abstract class StarsCafeDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
