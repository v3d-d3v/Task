package co.ssup.task.data.service

import co.ssup.task.data.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface Service {
  @GET("posts")
  suspend fun getPosts(): Response<List<Post>>
}