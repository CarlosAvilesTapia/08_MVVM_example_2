package cl.cat2814.a08mvvmexample2.data.local

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class DogBreedsDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var breedsDao: DogBreedsDao
    private lateinit var db: DogBreedsDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DogBreedsDatabase::class.java).build()
        breedsDao = db.getDogBreedsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertBreeds_empty() = runBlocking {
        // Given
        val breedList = listOf<DogBreedsEntity>()

        // When
        breedsDao.insertDogBreeds(breedList)

        // Then A
        val it = breedsDao.getDogBreeds().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        breedsDao.getDogBreeds().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertBreeds_happyCase_1element() = runBlocking {
        // Given
        val breedList = listOf(DogBreedsEntity("breed1"))

        // When
        breedsDao.insertDogBreeds(breedList)

        // Then
        breedsDao.getDogBreeds().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertBreeds_happyCase_3elements() = runBlocking {
        // Given
        val breedList = listOf(DogBreedsEntity("breed1"), DogBreedsEntity("breed2"), DogBreedsEntity("breed3"))

        // When
        breedsDao.insertDogBreeds(breedList)

        // Then
        breedsDao.getDogBreeds().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}
