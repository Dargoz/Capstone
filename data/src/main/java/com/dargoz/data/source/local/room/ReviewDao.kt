package com.dargoz.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dargoz.data.source.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReviews(reviewEntities: List<ReviewEntity>)

    @Query("SELECT * FROM review WHERE animeId=:animeId")
    fun getReviewsByAnimeId(animeId: Long): Flow<List<ReviewEntity>>
}