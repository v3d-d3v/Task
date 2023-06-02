package co.ssup.task.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ssup.task.data.models.Post
import co.ssup.task.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
  val posts: LiveData<List<Post>> get() = _posts

  init {
    viewModelScope.launch {
      repository.getPosts().collect {
        _posts.postValue(it)
      }
    }
  }
}