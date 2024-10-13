package com.android.sounddigitvisualiser.domain.repository

import com.android.sounddigitvisualiser.domain.model.AnimationImageModel

interface AnimationRepository {
    suspend fun loadAllPresets(): List<AnimationImageModel>
    suspend fun loadParametersPreset(name: String): List<AnimationImageModel>
    suspend fun deleteParametersPreset(animationImageModel: AnimationImageModel)
    suspend fun saveParametersPreset(
        animationName: String,
        animationImageModel: AnimationImageModel
    )
    suspend fun deleteAllParameters()
}