package cl.cat2814.a08mvvmexample2.data

import cl.cat2814.a08mvvmexample2.data.local.DogBreedsEntity
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsImagesEntity

fun String.toBreedImageEntity(id: String): DogBreedsImagesEntity = DogBreedsImagesEntity(id, this)

fun String.toBreedEntity(): DogBreedsEntity = DogBreedsEntity(this)
