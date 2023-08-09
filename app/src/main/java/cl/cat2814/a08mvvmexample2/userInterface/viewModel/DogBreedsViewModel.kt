package cl.cat2814.a08mvvmexample2.userInterface.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.cat2814.a08mvvmexample2.data.DogBreedsRepository
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsDatabase
import cl.cat2814.a08mvvmexample2.data.remote.DogBreedsRetrofit
import kotlinx.coroutines.launch
import java.util.Locale

class DogBreedsViewModel(application: Application) : AndroidViewModel(application) {

     private val dogBreedsRepository: DogBreedsRepository

     fun liveDataDogBreeds() = dogBreedsRepository.getDogBreedsFromDao()

    init {
        val dogBreedsApi = DogBreedsRetrofit.getRetrofitDogsBreed()
        val dogBreedsDatabase = DogBreedsDatabase.getDatabase(application).getDogBreedsDao()
        dogBreedsRepository = DogBreedsRepository(dogBreedsApi, dogBreedsDatabase)
    }

    fun getAllDogBreeds() = viewModelScope.launch {
        dogBreedsRepository.loadDogBreedsFromApiToDao()
    }
}
