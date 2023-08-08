package cl.cat2814.a08mvvmexample2.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface DogBreedsApi {

    @GET("breeds/list/all")
    suspend fun getData(): Response<DogBreeds>
}
