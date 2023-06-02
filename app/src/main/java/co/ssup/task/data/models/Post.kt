package co.ssup.task.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Post(
  val userId: Int,
  @PrimaryKey
  val id: Int,
  val title: String,
  val body: String,
): Serializable
