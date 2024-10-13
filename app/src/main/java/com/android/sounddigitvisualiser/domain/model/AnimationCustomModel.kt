package com.android.sounddigitvisualiser.domain.model

data class AnimationCustomModel(

    var volume:Int = 2,

    var px:Float = 0f,

    var py:Float = 0f,

    var mx1:Float = 0f,

    var my1:Float = 0f,

    var mx2:Float = 0f,

    var my2:Float = 0f,

    val AMPLITUDE:Int = 0,

    var reactionTimer:Long = 0L,

    var scaleFactor:Double

)
