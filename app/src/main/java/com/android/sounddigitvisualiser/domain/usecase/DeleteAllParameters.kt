package com.android.sounddigitvisualiser.domain.usecase

import com.android.sounddigitvisualiser.domain.repository.AnimationRepository

class DeleteAllParameters(private val animationRepository: AnimationRepository) {
    suspend fun execute(){
        animationRepository.deleteAllParameters()
    }
}