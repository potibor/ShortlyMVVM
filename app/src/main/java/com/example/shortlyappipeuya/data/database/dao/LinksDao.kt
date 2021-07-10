package com.example.shortlyappipeuya.data.database.dao

import androidx.room.*
import com.example.shortlyappipeuya.data.database.entity.LinkEntity

@Dao
interface LinksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLink(link: LinkEntity)

    @Delete
    suspend fun removeLink(link: LinkEntity)

    @Query("SELECT * FROM link")
    suspend fun getLinks(): List<LinkEntity>

}