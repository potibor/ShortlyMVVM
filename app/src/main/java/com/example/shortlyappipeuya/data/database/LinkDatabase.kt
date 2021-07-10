package com.example.shortlyappipeuya.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shortlyappipeuya.data.database.dao.LinksDao
import com.example.shortlyappipeuya.data.database.entity.LinkEntity

@Database(
    entities = [LinkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LinkDatabase : RoomDatabase() {

    abstract fun linksDao(): LinksDao

}