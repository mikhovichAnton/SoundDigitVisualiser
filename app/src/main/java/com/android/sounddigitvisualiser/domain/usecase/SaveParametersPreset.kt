package com.android.sounddigitvisualiser.domain.usecase

import com.android.sounddigitvisualiser.domain.model.AnimationImageModel
import com.android.sounddigitvisualiser.domain.repository.AnimationRepository

class SaveParametersPreset(private val animationRepository: AnimationRepository) {
    suspend fun execute(animationName: String, animationImageModel: AnimationImageModel){
        return animationRepository.saveParametersPreset(animationName,animationImageModel)
    }
}