package cl.cat2814.a08mvvmexample2.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogBreedsRetrofit {

    companion object {
        private const val URL_BASE = "https://dog.ceo/api/"

        fun getRetrofitDogsBreed(): DogBreedsApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(DogBreedsApi::class.java)
        }
    }
}