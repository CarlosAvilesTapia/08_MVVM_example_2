package cl.cat2814.a08mvvmexample2.data

import android.util.Log
import androidx.lifecycle.LiveData
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsDao
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsEntity
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsImagesEntity
import cl.cat2814.a08mvvmexample2.data.remote.DogBreedsApi

class DogBreedsRepository(
    private val dogBreedsApi: DogBreedsApi,
    private val dogBreedsDao: DogBreedsDao
) {

    // Funciones para el listado de razas
    suspend fun loadDogBreedsFromApiToDao() {
        try {
            val response = dogBreedsApi.getDataFromApi()
            if (response.isSuccessful) {
                val responseDogBreed = response.body()!!.message
                val keys = responseDogBreed.keys
                keys.forEach {
                    val dogBreedsEntity = DogBreedsEntity(it)
                    dogBreedsDao.insertDogBreeds(dogBreedsEntity)
                }
            } else {
                Log.e("Repository", response.errorBody().toString())
            }

        } catch (exception: Exception) {
            Log.e("Repository", "response.errorBody().toString()")
        }
    }

    fun getDogBreedsFromDao(): LiveData<List<DogBreedsEntity>> = dogBreedsDao.getDogBreeds()


    // Funciones para el listado de imágenes de cada raza.
    suspend fun loadDogBreedsImagesFromApiToDao(id: String) {
        try {
            val response = dogBreedsApi.getDogBreedImagesFromApi(id)
            if (response.isSuccessful) {
                response.body()!!.message.forEach {
                    val dogBreedImage = DogBreedsImagesEntity(id, it)
                    dogBreedsDao.insertDogBreedsImages(dogBreedImage)
                }
            } else {
                Log.e("Repository", response.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("Repository", "response.errorBody().toString()")
        }
    }

    fun getDogBreedsImagesFromDao(id: String): LiveData<List<DogBreedsImagesEntity>> =
        dogBreedsDao.getDogBreedsImages(id)
}
