package co.ssup.task.di

import android.content.Context
import androidx.room.Room
import co.ssup.task.BuildConfig
import co.ssup.task.data.LocalDatabase
import co.ssup.task.data.dao.Dao
import co.ssup.task.data.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(logging).build()
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideService(
    retrofit: Retrofit
  ): Service =
    retrofit.create(Service::class.java)

  @Singleton
  @Provides
  fun provideDb(
    @ApplicationContext task: Context
  ): LocalDatabase = Room
    .databaseBuilder(
      task,
      LocalDatabase::class.java,
      LocalDatabase.DATABASE_NAME
    )
    .build()

  @Singleton
  @Provides
  fun provideDao(db: LocalDatabase): Dao =
    db.dao()
}