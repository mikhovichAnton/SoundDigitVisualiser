package com.android.sounddigitvisualiser.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.sounddigitvisualiser.data.model.AnimationImageEntity

@Dao
interface AnimationImageDao {

    @Query("SELECT DISTINCT * FROM parameters")
    suspend fun getAllParameters(): List<AnimationImageEntity>

    @Query("SELECT * FROM parameters WHERE animation_name = :parameterName")
    suspend fun getParameters(parameterName: String): List<AnimationImageEntity>

    @Insert
    suspend fun insertParameter(animationImageEntity: AnimationImageEntity)

    @Delete
    suspend fun deleteParameter(animationImageEntity:AnimationImageEntity)

    @Query("DELETE FROM parameters")
    suspend fun deleteAllParameters()

}