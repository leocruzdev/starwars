package com.dacruz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dacruz.database.model.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao {

    @Query("SELECT * FROM favorite_characters ORDER BY name ASC")
    fun getFavoriteCharacters(): List<FavoriteCharacterEntity>

    @Query("SELECT * FROM favorite_characters ORDER BY name ASC LIMIT :limit OFFSET :offset")
    fun getFavoriteCharacters(limit: Int, offset: Int): List<FavoriteCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteCharacter(favoriteCharacter: FavoriteCharacterEntity): Long

    @Query("DELETE FROM favorite_characters WHERE url = :url")
    fun deleteFavoriteCharacterByUrl(url: String): Int

    @Query("SELECT EXISTS(SELECT * FROM favorite_characters WHERE url = :url)")
    fun isCharacterFavorite(url: String): Flow<Boolean>

    @Query("SELECT imageByteArray FROM favorite_characters WHERE url = :url")
    fun getCharacterImageByUrl(url: String): Flow<ByteArray?>
}