package cl.cat2814.a08mvvmexample2.userInterface.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.cat2814.a08mvvmexample2.data.DogBreedsRepository
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsDatabase
import cl.cat2814.a08mvvmexample2.data.remote.DogBreedsRetrofit
import kotlinx.coroutines.launch

class DogBreedsViewModel(application: Application) : AndroidViewModel(application) {

     private val dogBreedsRepository: DogBreedsRepository

     // Funciones para el listado de razas.
     fun liveDataDogBreeds() = dogBreedsRepository.getDogBreedsFromDao()

    init {
        val dogBreedsApi = DogBreedsRetrofit.getRetrofitDogsBreed()
        val dogBreedsDatabase = DogBreedsDatabase.getDatabase(application).getDogBreedsDao()
        dogBreedsRepository = DogBreedsRepository(dogBreedsApi, dogBreedsDatabase)
    }

    fun getAllDogBreeds() = viewModelScope.launch {
        dogBreedsRepository.loadDogBreedsFromApiToDao()
    }

    // Funciones para el listado de imágenes de las razas.
    fun getDogBreedImages(id: String) = viewModelScope.launch {
        dogBreedsRepository.loadDogBreedsImagesFromApiToDao(id)
    }

    fun liveDataDogBreedsImages(id: String) = dogBreedsRepository.getDogBreedsImagesFromDao(id)
}
