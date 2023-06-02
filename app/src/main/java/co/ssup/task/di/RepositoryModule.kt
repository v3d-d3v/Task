package co.ssup.task.di

import co.ssup.task.data.dao.Dao
import co.ssup.task.data.repository.Repository
import co.ssup.task.data.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideRepository(
    service: Service,
    dao: Dao
  ): Repository = Repository(service, dao)
}