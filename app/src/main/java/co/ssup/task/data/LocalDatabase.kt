package co.ssup.task.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.ssup.task.data.models.Post

@Database(
  entities = [Post::class],
  version = 1,
)
abstract class LocalDatabase: RoomDatabase() {
  abstract fun dao(): co.ssup.task.data.dao.Dao

  companion object {
    const val DATABASE_NAME: String = "task.db"
  }
}