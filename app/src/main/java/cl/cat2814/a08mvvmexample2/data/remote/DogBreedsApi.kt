package cl.cat2814.a08mvvmexample2.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedsApi {

    @GET("breeds/list/all")
    suspend fun getDataFromApi(): Response<DogBreeds>

    @GET("breed/{id}/images")
    suspend fun getDogBreedImagesFromApi(@Path("id") id: String): Response<DogBreedsImages>
}
