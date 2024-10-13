package com.android.sounddigitvisualiser.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sounddigitvisualiser.domain.model.AnimationCustomModel
import com.android.sounddigitvisualiser.domain.model.AnimationImageModel
import com.android.sounddigitvisualiser.domain.usecase.DeleteAllParameters
import com.android.sounddigitvisualiser.domain.usecase.DeleteParametersPreset
import com.android.sounddigitvisualiser.domain.usecase.GetAllParameters
import com.android.sounddigitvisualiser.domain.usecase.LoadParametersPreset
import com.android.sounddigitvisualiser.domain.usecase.SaveParametersPreset
import kotlinx.coroutines.launch

class AnimationViewModel(
    private val getAllParameters:GetAllParameters,
    private val saveParametersPreset: SaveParametersPreset,
    private val loadParametersPreset: LoadParametersPreset,
    private val deleteParametersPreset: DeleteParametersPreset,
    private val deleteAllParameters: DeleteAllParameters
    ) : ViewModel() {
    private val ini = MutableLiveData<AnimationCustomModel>()

    private val image = MutableLiveData<AnimationImageModel>()

    private val _animationImageModelList = MutableLiveData<List<AnimationImageModel>>()

    val animationImageModelInit: LiveData<List<AnimationImageModel>> get() = _animationImageModelList

    val initImage: LiveData<AnimationImageModel> get() = image

    val initialization: LiveData<AnimationCustomModel> get() = ini

    fun showAllAnimations(){
        viewModelScope.launch {
            _animationImageModelList.value = getAllParameters.execute()
        }
    }

    fun saveAnim(id:Int,name: String,fromX: Int,
                 toX: Int,
                 fromY: Int,
                 toY: Int,
                 formNumber: Int,
                 paramForColor: Int,
                 reactionTimer: Long,
                 scaleFactor: Double,
                 volume: Int){
        viewModelScope.launch {
            saveParametersPreset.execute(name,
                AnimationImageModel(
                    id,
                    name,
                    fromX,
                    toX,
                    fromY,
                    toY,
                    scaleFactor,
                    reactionTimer,
                    formNumber,
                    paramForColor,
                    volume))
        }
    }

    fun loadAnimation(name:String){
        viewModelScope.launch {
            loadParametersPreset.execute(name)
        }
    }

    fun deleteAllAnimations(){
        viewModelScope.launch {
            deleteAllParameters.execute()
        }
    }

    fun deleteAnimation(animationImageModel: AnimationImageModel){
        viewModelScope.launch {
            deleteParametersPreset.execute(animationImageModel)
        }
    }


    fun setLiveParams(
        volume: Int,
        px: Float,
        py: Float,
        mx1: Float,
        mx2: Float,
        my1: Float,
        my2: Float,
        amplitude: Int,
        reactionTimer: Long,
        scaleFactor: Double
    ) {
        ini.value = AnimationCustomModel(
            volume,
            px,
            py,
            mx1,
            my1,
            mx2,
            my2,
            amplitude,
            reactionTimer,
            scaleFactor
        )
    }

    fun setLiveParamsForImage(
        id: Int,
        name: String,
        fromX: Int,
        toX: Int,
        fromY: Int,
        toY: Int,
        formNumber: Int,
        paramForColor: Int,
        reactionTimer: Long,
        scaleFactor: Double,
        volume: Int
    ) {
        image.value = AnimationImageModel(
            id,
            name,
            fromX,
            toX,
            fromY,
            toY,
            scaleFactor,
            reactionTimer,
            formNumber,
            paramForColor,
            volume
        )
    }
}