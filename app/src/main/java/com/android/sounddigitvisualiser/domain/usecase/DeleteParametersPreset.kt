package com.android.sounddigitvisualiser.domain.usecase

import com.android.sounddigitvisualiser.domain.model.AnimationImageModel
import com.android.sounddigitvisualiser.domain.repository.AnimationRepository

class DeleteParametersPreset(private val animationRepository: AnimationRepository) {
    suspend fun execute(animationImageModel: AnimationImageModel){
        animationRepository.deleteParametersPreset(animationImageModel)
    }
}