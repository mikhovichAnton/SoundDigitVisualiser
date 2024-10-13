package com.android.sounddigitvisualiser.data.model



import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "parameters")
data class AnimationImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "animation_id")
    val id: Int = 0,
    @ColumnInfo(name = "animation_name")
    val parameterName: String,
    @ColumnInfo(name = "from_x")
    val fromX: Int,
    @ColumnInfo(name = "to_x")
    val toX: Int,
    @ColumnInfo(name = "from_y")
    val fromY: Int,
    @ColumnInfo(name = "to_y")
    val toY: Int,
    @ColumnInfo(name = "scale")
    val scaleFactor: Double,
    @ColumnInfo(name = "reaction_time")
    val reactionTimer: Long,
    @ColumnInfo(name = "form_number")
    val formNumber : Int,
    @ColumnInfo(name = "color_number")
    val paramForColor : Int,
    @ColumnInfo(name = "volume_number")
    val volume: Int
): Parcelable
