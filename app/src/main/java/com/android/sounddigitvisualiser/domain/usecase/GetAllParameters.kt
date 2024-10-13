package com.android.sounddigitvisualiser.domain.usecase

import com.android.sounddigitvisualiser.domain.model.AnimationImageModel

import com.android.sounddigitvisualiser.domain.repository.AnimationRepository

class GetAllParameters(private val animationRepository: AnimationRepository) {
    suspend fun execute(): List<AnimationImageModel>{
        return animationRepository.loadAllPresets()
    }
}
