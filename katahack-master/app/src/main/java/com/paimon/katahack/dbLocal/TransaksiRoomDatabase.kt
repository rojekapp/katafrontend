package com.paimon.katahack.dbLocal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paimon.katahack.transaksiInvoice.TransaksiModel

//Database annotation to specify the entities and set version
@Database(entities = [TransaksiModel::class], version = 1, exportSchema = false)
abstract class TransaksiRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: TransaksiRoomDatabase? = null

        fun getDatabase(context: Context): TransaksiRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransaksiRoomDatabase::class.java,
                    "transaksi_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate table if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getNoteDao() : TransaksiDao
}
