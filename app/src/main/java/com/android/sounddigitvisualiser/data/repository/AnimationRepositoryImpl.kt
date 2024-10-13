package com.android.sounddigitvisualiser.data.repository



import com.android.sounddigitvisualiser.data.local.AnimationImageDao
import com.android.sounddigitvisualiser.data.model.AnimationImageEntity
import com.android.sounddigitvisualiser.domain.model.AnimationImageModel
import com.android.sounddigitvisualiser.domain.repository.AnimationRepository

class AnimationRepositoryImpl(
    private val animationImageDao: AnimationImageDao
) : AnimationRepository {

    override suspend fun loadAllPresets(): List<AnimationImageModel> =
        animationImageDao.getAllParameters().map { animationImageEntity ->
            AnimationImageModel(id = animationImageEntity.id,
                parameterName = animationImageEntity.parameterName,
                fromX = animationImageEntity.fromY,
                toX = animationImageEntity.toX,
                fromY = animationImageEntity.fromY,
                toY = animationImageEntity.toY,
                scaleFactor = animationImageEntity.scaleFactor,
                reactionTimer = animationImageEntity.reactionTimer,
                formNumber = animationImageEntity.formNumber,
                paramForColor = animationImageEntity.paramForColor,
                volume = animationImageEntity.volume)
        }


    override suspend fun loadParametersPreset(parameterName: String): List<AnimationImageModel> {
        return with(animationImageDao.getParameters(parameterName)){
            map { animationImageEntity ->
                AnimationImageModel(id = animationImageEntity.id,
                    parameterName = animationImageEntity.parameterName,
                    fromX = animationImageEntity.fromY,
                    toX = animationImageEntity.toX,
                    fromY = animationImageEntity.fromY,
                    toY = animationImageEntity.toY,
                    scaleFactor = animationImageEntity.scaleFactor,
                    reactionTimer = animationImageEntity.reactionTimer,
                    formNumber = animationImageEntity.formNumber,
                    paramForColor = animationImageEntity.paramForColor,
                    volume = animationImageEntity.volume)
            }
        }
    }


    override suspend fun saveParametersPreset(
        animationName: String,
        animationImageModel: AnimationImageModel
    ) {
        val anim = AnimationImageEntity(animationImageModel.id,
            parameterName = animationName,
            animationImageModel.fromX,
            animationImageModel.toX,
            animationImageModel.fromY,
            animationImageModel.toY,
            animationImageModel.scaleFactor,
            animationImageModel.reactionTimer,
            animationImageModel.formNumber,
            animationImageModel.paramForColor
            ,animationImageModel.volume)
        animationImageDao.insertParameter(anim)
    }

    override suspend fun deleteAllParameters() {
        animationImageDao.deleteAllParameters()
    }

    override suspend fun deleteParametersPreset(animationImageModel: AnimationImageModel) {
        val anim = AnimationImageEntity(animationImageModel.id,
            animationImageModel.parameterName,
            animationImageModel.fromX,
            animationImageModel.toX,
            animationImageModel.fromY,
            animationImageModel.toY,
            animationImageModel.scaleFactor,
            animationImageModel.reactionTimer,
            animationImageModel.formNumber,
            animationImageModel.paramForColor
            ,animationImageModel.volume)
        animationImageDao.deleteParameter(anim)
    }

}