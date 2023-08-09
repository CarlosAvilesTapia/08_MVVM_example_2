package cl.cat2814.a08mvvmexample2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_breeds_table")
data class DogBreedsEntity(
    @PrimaryKey val breed: String
)