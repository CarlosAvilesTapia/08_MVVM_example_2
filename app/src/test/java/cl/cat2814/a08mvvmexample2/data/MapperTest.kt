package cl.cat2814.a08mvvmexample2.data

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
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
        //assertEquals(breedTest, result2.breed)

        // Then (probando Truth)
        assertThat(result2.breed).isEqualTo(breedTest)
    }
}
