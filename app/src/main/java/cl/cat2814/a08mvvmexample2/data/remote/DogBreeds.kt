package cl.cat2814.a08mvvmexample2.data.remote

data class DogBreeds(
    val status: String,
    val message: Map<String, List<String>>
    )

data class DogBreedsImages(
    val status: String,
    val message: List<String>
)
