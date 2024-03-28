package com.mftjc.spesa.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mftjc.spesa.dao.ProductDao
import com.mftjc.spesa.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDb : RoomDatabase(){

    abstract fun productDao(): ProductDao

    companion object{
        @Volatile
        private var instance: ProductDb? = null

        fun getInstance(context: Context): ProductDb {
            if (instance == null) {
                synchronized(ProductDb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDb::class.java, "spesa"
                    ).build()
                }
            }
            return instance as ProductDb
        }
    }
}