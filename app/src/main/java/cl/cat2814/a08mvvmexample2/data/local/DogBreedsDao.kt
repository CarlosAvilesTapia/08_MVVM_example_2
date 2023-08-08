package cl.cat2814.a08mvvmexample2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DogBreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreed(dogBreedsEntity: DogBreedsEntity)

    @Query("SELECT * FROM dog_breeds_table ORDER BY breed DESC")
    fun getBreeds(): LiveData<DogBreedsEntity>
}