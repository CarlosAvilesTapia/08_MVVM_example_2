package cl.cat2814.a08mvvmexample2.data

import org.junit.Assert.*

import org.junit.Test

class MapperTest {

    @Test
    fun toBreedImageEntity() {

        // Given
        val urlTest = "http://example.com"
        val idTest = "123"

        // When
        val result = urlTest.toBreedImageEntity(idTest)

        // Then
        assertEquals(urlTest, result.imgUrl)
        assertEquals(idTest, result.breed)
    }

    @Test
    fun toBreedEntity() {

        // Given
        val breedTest = "Kiltro"

        // When
        val result2 = breedTest.toBreedEntity()

        // Then
        assertEquals(breedTest, result2.breed)
    }
}
