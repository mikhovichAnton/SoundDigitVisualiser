package com.android.sounddigitvisualiser.domain.usecase

import com.android.sounddigitvisualiser.domain.repository.AnimationRepository

class LoadParametersPreset(private val animationRepository: AnimationRepository) {
    suspend fun execute(name: String){
        animationRepository.loadParametersPreset(name)
    }
}