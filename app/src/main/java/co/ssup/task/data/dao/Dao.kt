package co.ssup.task.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.ssup.task.data.models.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsert(posts: List<Post>)

  @Query("SELECT * FROM post")
  fun getPosts(): Flow<List<Post>>
}