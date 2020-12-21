package com.dargoz.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dargoz.data.source.local.entity.MangaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MangaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllManga(mangaList: List<MangaEntity>)

    @Query("SELECT * FROM manga WHERE type=:type")
    fun getAllManga(type: String): Flow<List<MangaEntity>>

}