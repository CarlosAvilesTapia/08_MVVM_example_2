package cl.cat2814.a08mvvmexample2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface DogBreedsDao {

    // Inserción y consulta para listado de razas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreeds(dogBreedsEntity: DogBreedsEntity)

    @Query("SELECT * FROM dog_breeds_table ORDER BY breed ASC")
    fun getDogBreeds(): LiveData<List<DogBreedsEntity>>


    // Inserción y consulta para listado de imágenes de las razas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreedsImages(dogBreedsImagesEntity: DogBreedsImagesEntity)

    @Query("SELECT * FROM dog_breeds_images_table WHERE breed LIKE :id")
    fun getDogBreedsImages(id: String): LiveData<List<DogBreedsImagesEntity>>
}