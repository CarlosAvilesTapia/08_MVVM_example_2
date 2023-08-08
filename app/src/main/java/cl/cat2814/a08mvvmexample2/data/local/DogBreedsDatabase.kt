package cl.cat2814.a08mvvmexample2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DogBreedsEntity::class], version = 1)
abstract class DogBreedsDatabase: RoomDatabase() {

    abstract fun getDogBreedsDao(): DogBreedsDao

    companion object {
        @Volatile
        private var INSTANCE: DogBreedsDatabase? = null

        fun getDatabase(context: Context): DogBreedsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogBreedsDatabase::class.java,
                    "dog_breeds_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}