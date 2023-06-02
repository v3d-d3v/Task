package co.ssup.task.data.repository

import co.ssup.task.data.dao.Dao
import co.ssup.task.data.service.Service
import co.ssup.task.utils.parse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val service: Service, private val dao: Dao) {
  fun getPosts() = flow {
    service.getPosts().parse { posts ->
      dao.upsert(posts)
      emit(posts)
    }
  }.catch {
    //silent error -> in case of error just loading posts from db to show cached data
    dao.getPosts().collect { posts -> emit(posts) }
  }
}