package cl.cat2814.a08mvvmexample2.data

import androidx.lifecycle.LiveData
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsDao
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsEntity
import cl.cat2814.a08mvvmexample2.data.remote.DogBreedsApi

class DogBreedsRepository(
    private val dogBreedsApi: DogBreedsApi,
    private val dogBreedsDao: DogBreedsDao
) {

    fun getDogBreedsFromDao(): LiveData<List<DogBreedsEntity>> = dogBreedsDao.getDogBreeds()

    suspend fun loadDogBreedsFromApiToDao() {
        val response = dogBreedsApi.getData()
        if (response.isSuccessful) {
            val responseDogBreed = response.body()!!.message
            val keys = responseDogBreed.keys
            keys.forEach {
                val dogBreedsEntity = DogBreedsEntity(it)
                dogBreedsDao.insertDogBreeds(dogBreedsEntity)
            }
        }
    }
}
