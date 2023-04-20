package com.dacruz.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_characters")
data class FavoriteCharacterEntity(
    @PrimaryKey
    val url: String,
    val name: String,
    val birthYear: String,
    val gender: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val imageByteArray: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FavoriteCharacterEntity

        if (url != other.url) return false
        if (name != other.name) return false
        if (birthYear != other.birthYear) return false
        if (gender != other.gender) return false
        if (height != other.height) return false
        if (mass != other.mass) return false
        if (hairColor != other.hairColor) return false
        if (skinColor != other.skinColor) return false
        if (eyeColor != other.eyeColor) return false
        if (imageByteArray != null) {
            if (other.imageByteArray == null) return false
            if (!imageByteArray.contentEquals(other.imageByteArray)) return false
        } else if (other.imageByteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + birthYear.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + mass.hashCode()
        result = 31 * result + hairColor.hashCode()
        result = 31 * result + skinColor.hashCode()
        result = 31 * result + eyeColor.hashCode()
        result = 31 * result + (imageByteArray?.contentHashCode() ?: 0)
        return result
    }

}