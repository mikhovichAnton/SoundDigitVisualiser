package com.android.sounddigitvisualiser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimationImageModel(
    val id: Int,
    val parameterName: String,
    val fromX: Int,
    val toX: Int,
    val fromY: Int,
    val toY: Int,
    val scaleFactor: Double,
    val reactionTimer: Long,
    val formNumber : Int,
    val paramForColor : Int,
    val volume: Int
) : Parcelable
