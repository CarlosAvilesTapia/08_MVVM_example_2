package cl.cat2814.a08mvvmexample2.data

import retrofit2.Response
import retrofit2.http.GET

interface DogsBreedApi {

    @GET("breeds/list/all")
    suspend fun getData(): Response<DogsBreed>
}
